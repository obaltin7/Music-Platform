package com.vektorel.music.data.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongReqDto {
    private Long id;
    private String name;
    private String duration;
    private LocalDate createDate;
    @NotNull(message = "Kategori Boş Geçilemez")
    private Long categoryId;
    @NotNull(message = "Sanatçı Boş Geçilemez")
    private Long artistId;
    private SongInfoReqDto info;


}
