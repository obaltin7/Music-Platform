package com.vektorel.music.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vektorel.music.data.dto.CategoryDto;
import com.vektorel.music.data.dto.response.CategoryResponseDto;
import com.vektorel.music.data.model.Category;
import com.vektorel.music.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")

public class CategoryController {
private final CategoryService service;
	
	@PostMapping("/add")	
	public CategoryResponseDto create(@RequestBody CategoryDto dto) {
		return service.create(dto);
	}
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id) {
		return service.delete(id);
	}
	@GetMapping("/{id}")
	public Category findById(@PathVariable Long id) {
		return service.findById(id);
	}
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping
	public List<CategoryDto> getAll(){
		return service.getAll();
	}
	@PutMapping("/update")
	public CategoryResponseDto update(@RequestBody CategoryDto dto) {
		return service.update(dto);
	}
	@GetMapping("/search/{name}")
	public CategoryResponseDto getByName(@PathVariable String name) {
		return service.getByName(name);
	}
	
	@GetMapping("/search")
	public List<CategoryDto> getByNameLike(@RequestParam("name") String name) {
		return service.getFindByNameAll(name);
	}


}
