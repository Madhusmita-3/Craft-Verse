package edu.rims.craft_verse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.rims.craft_verse.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,String> {  
}
