package edu.rims.craft_verse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import edu.rims.craft_verse.constant.ProductOrderStatus;

@Entity
@Table(name = "product_order")
@Setter
@Getter
public class ProductOrder extends Auditable {

    @Id
    @Column(name = "product_order_id", length = 255, nullable = false)
    private String productOrderId;

    @Column(name = "product_order_total_price", nullable = false)
    private double productOrderTotalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_order_status")
    private ProductOrderStatus productOrderStatus = ProductOrderStatus.PENDING;

    @Column(name = "product_order_tracking_id", length = 50)
    private String productOrderTrackingId;

    @Column(name = "product_order_shipping_address", nullable = false, columnDefinition = "TEXT")
    private String productOrderShippingAddress;

    @ManyToOne
    @JoinColumn(name = "buyer_user_id")
    private User user;

    @OneToMany(mappedBy = "productOrder")
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "productOrder")
    private List<Payment> payments;
}