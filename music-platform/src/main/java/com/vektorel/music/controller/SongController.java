package com.vektorel.music.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.vektorel.music.data.dto.SongDto;
import com.vektorel.music.data.dto.request.SongReqDto;
import com.vektorel.music.data.dto.response.SongResponseDto;
import com.vektorel.music.service.SongService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/songs")
@CrossOrigin
public class SongController {
	private final SongService service;
	
	@PostMapping("/add")
	public SongResponseDto create(@Valid @RequestBody SongReqDto dto) {
		return service.create(dto);
	}
	
	@GetMapping("/{id}")
	public SongResponseDto getById(@PathVariable Long id) {
		return service.getById(id);
	}
	@GetMapping
	public List<SongResponseDto> getAll() {
		return service.getByAll();
	}
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id) {
		return service.delete(id);
	}
	
	@GetMapping("/category/{categoryId}")
	public List<SongDto> getByCategory(@PathVariable Long categoryId) {
	    return service.findByCategory(categoryId);
	}

	@GetMapping("/artist/{artistId}")
	public List<SongDto> getByArtist(@PathVariable Long artistId) {
	    return service.findByArtist(artistId);
	}


	@PutMapping("/update")
	public SongResponseDto update(@RequestBody SongReqDto dto) {
		return service.update(dto);
	}

	@GetMapping("/search")
	public List<SongResponseDto> search(@RequestParam String keyword) {
		return service.search(keyword);
	}
}
