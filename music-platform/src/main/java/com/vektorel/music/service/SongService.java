package com.vektorel.music.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.vektorel.music.utils.OnurMapper;
import com.vektorel.music.utils.ResourceNotFoundException;

import org.springframework.stereotype.Service;

import com.vektorel.music.data.dto.SongDto;
import com.vektorel.music.data.dto.ArtistDto;
import com.vektorel.music.data.dto.request.SongReqDto;
import com.vektorel.music.data.dto.response.SongResponseDto;
import com.vektorel.music.data.model.Category;
import com.vektorel.music.data.model.Song;
import com.vektorel.music.data.model.SongInfo;
import com.vektorel.music.data.model.Artist;
import com.vektorel.music.repository.SongRepository;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SongService {
	private final SongRepository songRepository;
	private final CategoryService catService;
	private final ArtistService artistService;
	private final SongInfoService infoService;

	
	@Transactional
	public SongResponseDto create(SongReqDto dto) {
	    Artist art = new Artist();
	    art.setId(dto.getArtistId());
	    
	    Category cat = new Category();
	    cat.setId(dto.getCategoryId());

	    Song song = new Song();
	    song.setArtist(art);
	    song.setCategory(cat);
	    song.setCreateDate(dto.getCreateDate());
	    song.setDuration(dto.getDuration());
	    song.setName(dto.getName());

	    if (dto.getInfo() != null) {
	        SongInfo info = new SongInfo();
	        info.setLikes(dto.getInfo().getLikes());
	        info.setViews(dto.getInfo().getViews());

	        info.setSong(song);
	        song.setInfo(info);
	    }

	    Song savedSong = songRepository.save(song);

	    return OnurMapper.convertToDto(savedSong, SongResponseDto.class);
	}

	public SongResponseDto update(SongReqDto dto) {
		Song existingSong = songRepository.findById(dto.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Song not found"));

		OnurMapper.convertToEntity(dto, existingSong);
		Song updatedSong = songRepository.save(existingSong);
		return OnurMapper.convertToDto(updatedSong,SongResponseDto.class);
	}

	public List<SongResponseDto> search(String keyword) {
		List<Song> songs = songRepository.findByNameContaining(keyword);
		return OnurMapper.convertToDtoList(songs, SongResponseDto.class);
	}

	public SongResponseDto getById(Long id) {
		Song song = songRepository.findById(id).orElse(null);
		return OnurMapper.convertToDto(song, SongResponseDto.class);
	}

	public List<SongResponseDto> getByAll() {
		List<Song> songs = songRepository.findAll();
		List<SongResponseDto> dtos = new ArrayList<>();
		for (Song song : songs) {
			dtos.add(OnurMapper.convertToDto(song, SongResponseDto.class));
		}
		return dtos;
	}


	@Transactional
	public String delete(Long id) {
	    if (songRepository.existsById(id)) {
	        Song song = songRepository.findById(id).orElseThrow(() -> new RuntimeException("Song not found"));

	        // İlişkili SongInfo'yu sil
	        if (song.getInfo() != null) {
	            infoService.delete(song.getInfo().getId());
	        }

	        // Şarkının kategori ve sanatçı ilişkilerini null yap
	        song.setCategory(null);
	        song.setArtist(null);

	        // Şarkıyı sil
	        songRepository.delete(song);

	        return "Silme işlemi başarılı";
	    } else {
	        return "Kayıt silinemedi.";
	    }
	}




	public List<SongDto> findByCategory(Long categoryId) {
		Category category = catService.findById(categoryId);
		return songRepository.findByCategory(category).stream().map(song -> OnurMapper.convertToDto(song, SongDto.class))
				.collect(Collectors.toList());
	}

		public List<SongDto> findByArtist(Long artistId) {
		ArtistDto artDto = artistService.getById(artistId);
		Artist artist = OnurMapper.convertToEntity(artDto, Artist.class);
		return songRepository.findByArtist(artist).stream().map(song -> OnurMapper.convertToDto(song, SongDto.class))
				.collect(Collectors.toList());
	}

}
