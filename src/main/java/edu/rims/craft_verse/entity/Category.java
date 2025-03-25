package edu.rims.craft_verse.entity;

import java.util.ArrayList;
import java.util.List;

import edu.rims.craft_verse.constant.CategoryStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "category")
@Setter
@Getter

public class Category extends Auditable {

    @Id
    @Column(name = "category_id", nullable = false, length = 255)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String categoryId;

    @Column(name = "category_title", nullable = false, length = 100)
    private String categoryTitle;

    @Column(name = "category_description", columnDefinition = "TEXT")
    private String categoryDescription;

    @Column(name = "category_image_url", length = 255)
    private String categoryImageUrl;

    @Column(name = "category_hover_image_url", length = 255)
    private String categoryHoverImageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "category_status", columnDefinition = "ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE'")
    private CategoryStatus categoryStatus = CategoryStatus.ACTIVE;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public void addProduct(Product product){
        if(products == null){
            products = new ArrayList<>();
        }
        product.setCategory(this);
        products.add(product);
    }
}