package com.vektorel.music.validation;

import com.vektorel.music.data.dto.request.ArtistReqDto;
import com.vektorel.music.utils.ValidationException;

public class ArtistValidator {
	public static void validate(ArtistReqDto dto) {
		if (dto.getName() != null && dto.getName().matches(".*\\d.*")) {
			throw new ValidationException("Name cannot contain a number");
		}

		if (dto.getCountry() != null && dto.getCountry().matches(".*\\d.*")) {
			throw new ValidationException("Country name cannot contain a number");
		}

	}
}