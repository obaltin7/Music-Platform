package com.vektorel.music.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongInfoResponseDto {
	private Long id;
	private Integer likes;
	private Integer views;
}
