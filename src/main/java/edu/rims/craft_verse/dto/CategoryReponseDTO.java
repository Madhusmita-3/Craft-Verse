package edu.rims.craft_verse.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryReponseDTO {
    private String categoryId;
    private String categoryTitle;
    private String categoryDescription;
    private String categoryImageUrl;
    private String categoryHoverImageUrl;
    private String categoryStatus;

    
}