package com.vektorel.music.data.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtistDto {
	private Long id;
	private String name;
	private List<SongDto> songs;
	private LocalDate birthdate;
	private String description;
	private String country;
}
