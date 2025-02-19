package edu.rims.craft_verse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



import edu.rims.craft_verse.constant.ReviewStatus;

@Entity
@Table(name = "review")
@Setter
@Getter
public class Review extends Auditable {
    
    @Id
    @Column(name = "review_id", length = 255, nullable = false)
    private String reviewId;
    
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
    private Product product;
    
    @Column(name = "rating", precision = 3, scale = 2, nullable = false)
    private Double rating;
    
    @Column(name = "review_description", columnDefinition = "TEXT")
    private String reviewDescription;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "review_status", nullable = false)
    private ReviewStatus reviewStatus = ReviewStatus.PENDING;
}
