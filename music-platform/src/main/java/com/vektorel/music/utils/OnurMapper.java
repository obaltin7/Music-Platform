package com.vektorel.music.utils;

import java.lang.reflect.Field;
import java.lang.reflect.RecordComponent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OnurMapper {
	
	  public static <D, E> D convertToDto(E entity, Class<D> dtoClass) {
	        try {
	            D dto = dtoClass.getDeclaredConstructor().newInstance();
	            copyProperties(entity, dto);
	            return dto;
	        } catch (Exception e) {
	            throw new RuntimeException("Conversion failed from entity to DTO", e);
	        }
	    }

	    public static <D, E> E convertToEntity(D dto, Class<E> entityClass) {
	        try {
	            E entity = entityClass.getDeclaredConstructor().newInstance();
	            copyProperties(dto, entity);
	            return entity;
	        } catch (Exception e) {
	            throw new RuntimeException("Conversion failed from DTO to entity", e);
	        }
	    }
	    
	    public static <D, E> void convertToEntity(D dto, E entity) {
	        try {
	            copyProperties(dto, entity);
	        } catch (Exception e) {
	            throw new RuntimeException("Conversion failed from DTO to entity", e);
	        }
	    }


	    public static <D, E> List<D> convertToDtoList(List<E> entityList, Class<D> dtoClass) {
	        List<D> dtoList = new ArrayList<>();
	        for (E entity : entityList) {
	            dtoList.add(convertToDto(entity, dtoClass));
	        }
	        return dtoList;
	    }

	    public static <D, E> List<E> convertToEntityList(List<D> dtoList, Class<E> entityClass) {
	        List<E> entityList = new ArrayList<>();
	        for (D dto : dtoList) {
	            entityList.add(convertToEntity(dto, entityClass));
	        }
	        return entityList;
	    }

	    private static void copyProperties(Object source, Object target) {
	        //reflection
	        Field[] sourceFields = source.getClass().getDeclaredFields();
	        Field[] targetFields = target.getClass().getDeclaredFields();
	        for (Field sourceField : sourceFields) {
	            try {
	                Field targetField = findMatchingField(targetFields, sourceField.getName());
	                if (targetField != null) {
	                    sourceField.setAccessible(true);
	                    targetField.setAccessible(true);
	                    Object value = sourceField.get(source);
	                    targetField.set(target, value);
	                }
	            } catch (Exception e) {
	                continue;
	            }
	        }
	    }

	    public static <D extends Record, E> D convertClassToRecord(E classes, Class<D> recordClass) {
	        try {
	            Field[] classFields = classes.getClass().getDeclaredFields();
	            Object[] recordFields = new Object[classFields.length];
	            RecordComponent[] components = recordClass.getRecordComponents();
	            for (int i = 0; i < components.length; i++) {
	                String fieldName = components[i].getName();
	                Field field = findField(classFields, fieldName);
	                if (field != null) {
	                    field.setAccessible(true);
	                    recordFields[i] = field.get(classes);
	                }
	            }
	            Class<?>[] recordComponents = Arrays.stream(components).map(RecordComponent::getType).toArray(Class<?>[]::new);
	            return recordClass.getConstructor(recordComponents).newInstance(recordFields);

	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }

	    public static <R extends Record, E> E convertRecordToClass(R record, Class<E> entityClass) {
	        try {
	            System.out.println(record);
	            E entity = entityClass.getDeclaredConstructor().newInstance();
	            Field[] entityFields = entityClass.getDeclaredFields();
	            RecordComponent[] components = record.getClass().getRecordComponents();
	            for (RecordComponent recordComponent : components) {
	                Field field = findField(entityFields, recordComponent.getName());
	                if (field != null) {
	                    field.setAccessible(true);
	                    Object value = recordComponent.getAccessor().invoke(record);
	                    field.set(entity, value);
	                }
	            }
	            return entity;

	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }


	    public static <R extends Record, E> List<E> convertRecordToClassList(List<R> records, Class<E> entityClass) {

	        return records.stream().map(rec -> convertRecordToClass(rec, entityClass)).collect(Collectors.toList());

	    }

	    public static <R extends Record, E> List<R> converClassToRecordList(List<E> entities, Class<R> recordClass) {

	        return entities.stream().map(entity -> convertClassToRecord(entity, recordClass)).collect(Collectors.toList());

	    }

	    private static Field findField(Field[] fields, String fieldName) {
	        return Arrays.stream(fields).filter(field -> field.getName().equals(fieldName)).findFirst().orElse(null);
	    }


	    private static Field findMatchingField(Field[] fields, String fieldName) {
	        for (Field field : fields) {
	            if (field.getName().equals(fieldName)) {
	                return field;
	            }
	        }
	        return null;
	    }

}
