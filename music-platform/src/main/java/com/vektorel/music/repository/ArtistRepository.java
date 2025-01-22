package com.vektorel.music.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.vektorel.music.data.model.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Long>{

}
