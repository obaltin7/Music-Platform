package com.vektorel.music.data.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongDto {
	private Long id;
	private String name;
	private String duration;
	private LocalDate createDate;
	private ArtistDto artistDto;
	private CategoryDto categoryDto;
	@JsonManagedReference
	private SongInfoDto info;

}
