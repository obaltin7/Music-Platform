package com.vektorel.music.data.dto.response;

import java.time.LocalDate;
import com.vektorel.music.data.dto.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongResponseDto {
	private Long id;
	private String name;
	private String duration;
	private LocalDate createDate;
	private CategoryDto categoryDto; 
	private ArtistResponseDto artistDto;
	private SongInfoResponseDto infoDto;
	
}
