package com.vektorel.music.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongInfoReqDto {
	private Long songId;
	private Integer likes;
	private Integer views;
}
