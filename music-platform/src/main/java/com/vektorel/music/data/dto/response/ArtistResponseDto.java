package com.vektorel.music.data.dto.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtistResponseDto {
	private Long id;
	private String name;
	private LocalDate birthdate;
	private String description;
	private String country;
}
