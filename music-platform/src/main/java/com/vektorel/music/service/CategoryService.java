package com.vektorel.music.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vektorel.music.data.dto.CategoryDto;
import com.vektorel.music.data.dto.response.CategoryResponseDto;
import com.vektorel.music.data.model.Category;
import com.vektorel.music.repository.CategoryRepository;
import com.vektorel.music.utils.OnurMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class CategoryService {
	private final CategoryRepository repository;
	
	public List<CategoryDto> getFindByNameAll(String name){
		List<Category> cats=repository.findByNameLike(name);			
		return cats.stream().map(c->OnurMapper.convertToDto(c,CategoryDto.class)).toList();
	}
	
	
	public CategoryResponseDto getByName(String name) {
		return OnurMapper.convertToDto(repository.findByName(name), CategoryResponseDto.class);
	}
	
	public List<CategoryDto> getAll() {
	    List<Category> cats = repository.findAll();
	    return cats.stream()
	            .map(c -> OnurMapper.convertToDto(c, CategoryDto.class))
	            .toList();
	}

	

	public CategoryResponseDto create(CategoryDto dto) {	
//dto yu entity kayıt entityi dto 
		return OnurMapper.convertToDto(repository.save(OnurMapper.convertToEntity(dto, Category.class)), CategoryResponseDto.class);
	}

	public CategoryResponseDto update(CategoryDto dto) {
		/*
		 * Dto entitye dönüşerek update yapılacak update ile dönen entity dto ya
		 * dönüşerek geri dönülecek
		 */
		return OnurMapper.convertToDto(repository.save(OnurMapper.convertToEntity(dto, Category.class)), CategoryResponseDto.class);
	}

	public String delete(Long id) {

		if (repository.findById(id).orElse(null) == null) {
			return id + " ID'li kayıt bulunamadı";
		} else {
			repository.delete(repository.findById(id).orElse(null));
			return "Silme işlemi başarılı";
		}
	}

	public boolean findByIdCategory(Long id) {
		return repository.existsById(id);
	}
	
	public Category findById(Long id) {
	    return repository.findById(id).orElse(null); 
	}

}
