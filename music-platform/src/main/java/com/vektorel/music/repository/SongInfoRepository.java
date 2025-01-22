package com.vektorel.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vektorel.music.data.model.SongInfo;

public interface SongInfoRepository extends JpaRepository<SongInfo, Long> {

}
