package edu.rims.craft_verse.entity;

import java.util.List;

import edu.rims.craft_verse.constant.UserStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", nullable = false, length = 255)
    private String userId;

    @Column(name = "user_name", nullable = false, length = 255)
    private String userName;

    @Column(name = "user_email", nullable = false, unique = true, length = 255)
    private String userEmail;

    @Column(name = "user_password", nullable = false, length = 255)
    private String userPassword;

    @Column(name = "user_phone_number", length = 20)
    private String userPhoneNumber;

    @Column(name = "user_address", columnDefinition = "TEXT")
    private String userAddress;

    @Column(name = "user_profile_picture", length = 255)
    private String userProfilePicture;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status", columnDefinition = "ENUM('ACTIVE', 'INACTIVE','BANNED') DEFAULT 'ACTIVE'")
    private UserStatus userStatus = UserStatus.ACTIVE;

    @OneToMany(mappedBy = "user")
    private List<Payment> payments;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    @OneToMany(mappedBy = "user")
    private List<Wishlist> wishlists;

    @OneToMany(mappedBy = "user")
    private List<ProductOrder> productOrders;
}
