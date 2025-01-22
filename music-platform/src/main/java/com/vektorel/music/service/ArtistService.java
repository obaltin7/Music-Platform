package com.vektorel.music.service;

import com.vektorel.music.utils.OnurMapper;
import com.vektorel.music.utils.ResourceNotFoundException;
import org.springframework.stereotype.Service;


import com.vektorel.music.data.dto.ArtistDto;
import com.vektorel.music.data.dto.request.ArtistReqDto;
import com.vektorel.music.data.model.Artist;
import com.vektorel.music.repository.ArtistRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArtistService {
	private final ArtistRepository repository;
	
	
	public ArtistDto create(ArtistReqDto dto) {
	    Artist artist = OnurMapper.convertToEntity(dto, Artist.class);

	    Artist savedArtist = repository.save(artist);

	    return OnurMapper.convertToDto(savedArtist, ArtistDto.class);
	}


	public ArtistDto getById(Long id) {
		Artist artist = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Artist not found"));
		return OnurMapper.convertToDto(artist, ArtistDto.class);
	}

	public List<ArtistDto> getAll() {
		return repository.findAll().stream()
				.map(artist -> OnurMapper.convertToDto(artist, ArtistDto.class))
				.collect(Collectors.toList());
	}

	public ArtistDto update(ArtistReqDto dto) {
		Artist existingArtist = repository.findById(dto.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Artist not found"));

		OnurMapper.convertToEntity(dto, existingArtist);
		Artist updatedArtist = repository.save(existingArtist);
		return OnurMapper.convertToDto(updatedArtist, ArtistDto.class);
	}

	public void delete(Long id) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("Artist not found");
		}
		repository.deleteById(id);
	}
	
	public boolean findByIdArtist(Long id) {
		return repository.existsById(id);
	}
	
	public Artist findById(Long id) {
	    return repository.findById(id).orElse(null); 
	}
}
