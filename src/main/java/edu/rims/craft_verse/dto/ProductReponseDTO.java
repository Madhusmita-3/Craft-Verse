package edu.rims.craft_verse.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductReponseDTO {
    private String productId;
    private String productTitle;
    private String productDescription;
    private double productPrice;
    private int productStockQuantity;
    private String productStatus;
    private String productImageUrl;
    private CategoryResponse category;
    @Setter
    @Getter
    public class CategoryResponse {
        private String categoryId;
        private String categoryTitle;
    }
}
