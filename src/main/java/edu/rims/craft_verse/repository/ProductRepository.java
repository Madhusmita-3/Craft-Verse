package edu.rims.craft_verse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.rims.craft_verse.constant.ProductStatus;
import edu.rims.craft_verse.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByProductTitleContainingIgnoreCase(String productTitle);

    List<Product> findByProductTitleContainingIgnoreCaseAndProductStatus(String search, ProductStatus available);

}