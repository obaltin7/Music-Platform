package com.vektorel.music.controller;

import com.vektorel.music.data.dto.ArtistDto;
import com.vektorel.music.data.dto.request.ArtistReqDto;
import com.vektorel.music.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/artists")
@RestController
public class ArtistController {
	private final ArtistService service;
	@PostMapping("/add")
	public ArtistDto create(@RequestBody ArtistReqDto dto) {
		return service.create(dto);
	}

	@GetMapping("/{id}")
	public ArtistDto getById(@PathVariable Long id) {
		return service.getById(id);
	}

	@GetMapping
	public List<ArtistDto> getAll() {
		return service.getAll();
	}

	@PutMapping("/update")
	public ArtistDto update(@RequestBody ArtistReqDto dto) {
		return service.update(dto);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

}
