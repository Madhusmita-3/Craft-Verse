package edu.rims.craft_verse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



import edu.rims.craft_verse.constant.ProductStatus;

@Entity
@Table(name = "order_item")
@Setter
@Getter
public class OrderItem extends Auditable {
    
    @Id
    @Column(name = "order_item_id", length = 255, nullable = false)
    private String orderItemId;
    
    @ManyToOne
    @JoinColumn(name = "product_order_id", referencedColumnName = "product_order_id", nullable = false)
    private ProductOrder productOrder;
    
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
    private Product product;
    
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    
    @Column(name = "price", nullable = false)
    private Double price;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "order_item_status", columnDefinition = "ENUM('REMOVED, ADDED') DEFAULT 'ADDED'")
    private ProductStatus productStatus = ProductStatus.OUT_OF_STOCK;
}