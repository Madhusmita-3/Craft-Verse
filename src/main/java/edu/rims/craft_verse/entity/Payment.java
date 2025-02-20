package edu.rims.craft_verse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import edu.rims.craft_verse.constant.PaymentMethod;
import edu.rims.craft_verse.constant.PaymentStatus;

@Entity
@Table(name = "payment")
@Setter
@Getter
public class Payment extends Auditable {

    @Id
    @Column(name = "payment_id", nullable = false, unique = true)
    private String paymentId;

    @ManyToOne
    @JoinColumn(name = "product_order_id", nullable = false)
    private ProductOrder productOrder;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    @Column(name = "transaction_id", unique = true)
    private String transactionId;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "currency", nullable = false, length = 10)
    private String currency = "USD";

    @Column(name = "payment_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime paymentDate;
}
