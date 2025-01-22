package com.vektorel.music.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongInfoDto {
	private Long id;
	private SongDto song;
	private Integer likes;
	private Integer views;
}
