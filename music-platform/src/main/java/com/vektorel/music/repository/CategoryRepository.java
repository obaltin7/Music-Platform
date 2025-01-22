package com.vektorel.music.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.vektorel.music.data.model.Category;
import java.util.List;

@EnableJpaRepositories
public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	public Category findByName(String name);
	
	@Query("SELECT c FROM Category c WHERE c.name LIKE %:name%")
	public List<Category> findByNameLike(@Param("name") String name);

}
