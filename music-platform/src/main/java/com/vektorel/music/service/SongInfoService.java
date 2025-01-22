package com.vektorel.music.service;

import org.springframework.stereotype.Service;

import com.vektorel.music.data.dto.SongInfoDto;
import com.vektorel.music.data.dto.request.SongInfoReqDto;
import com.vektorel.music.data.model.Song;
import com.vektorel.music.data.model.SongInfo;
import com.vektorel.music.repository.SongInfoRepository;
import com.vektorel.music.repository.SongRepository;
import com.vektorel.music.utils.OnurMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SongInfoService {
	private final SongInfoRepository repository;
	private final SongRepository songRepository;
	
	
	public SongInfoDto create(SongInfoReqDto dto) {		
		SongInfo infoEntity=new SongInfo();
		infoEntity.setLikes(dto.getLikes());
		infoEntity.setViews(dto.getViews());
		Song song=songRepository.findById(dto.getSongId()).orElse(null);
		infoEntity.setSong(song);
		SongInfo infoSaved=repository.save(infoEntity);
		SongInfoDto respDto=OnurMapper.convertToDto(infoSaved, SongInfoDto.class);
		return respDto;
		
	}


	public SongInfo findById(Long id) {
	    return repository.findById(id).orElse(null); 
	}
	
	  public void delete(Long id) {
	        if (repository.existsById(id)) {
	            repository.deleteById(id);
	        } else {
	            throw new RuntimeException("SongInfo not found");
	        }
	    }

}
