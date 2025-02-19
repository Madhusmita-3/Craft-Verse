package edu.rims.craft_verse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import edu.rims.craft_verse.constant.ProductOrderStatus;

@Entity
@Table(name = "product_order")
@Setter
@Getter
public class ProductOrder extends Auditable {
    
    @Id
    @Column(name = "product_order_id", length = 255, nullable = false)
    private String productOrderId;
    
    @Column(name = "buyer_user_id", nullable = false)
    private Integer buyerUserId;
    
    @Column(name = "product_order_total_price", nullable = false)
    private Double productOrderTotalPrice;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "product_order_status", columnDefinition = "ENUM('PENDING', 'SHIPPED', 'DELIVERED', 'CANCELLED', 'CART') DEFAULT 'PENDING'")
    private ProductOrderStatus productOrderStatus = ProductOrderStatus.PENDING;
    
    @Column(name = "product_order_tracking_id", length = 50)
    private String productOrderTrackingId;
    
    @Column(name = "product_order_shipping_address", nullable = false, columnDefinition = "TEXT")
    private String productOrderShippingAddress;
    
    @ManyToOne
    @JoinColumn(name = "buyer_user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User user;
}
