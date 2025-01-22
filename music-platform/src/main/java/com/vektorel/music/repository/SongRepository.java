package com.vektorel.music.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vektorel.music.data.model.Artist;
import com.vektorel.music.data.model.Category;
import com.vektorel.music.data.model.Song;
@Repository
public interface SongRepository extends JpaRepository<Song, Long>{
	
	List<Song> findByCategory(Category category);
	List<Song> findByNameContaining(String name);
	List<Song> findByArtist(Artist artist);
}
