package edu.rims.craft_verse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.rims.craft_verse.entity.Product;

public interface ProductRepository  extends JpaRepository<Product, String>{

    

    
}
