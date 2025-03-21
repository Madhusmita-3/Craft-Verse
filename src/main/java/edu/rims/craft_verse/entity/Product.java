package edu.rims.craft_verse.entity;

import java.util.ArrayList;
import java.util.List;

import edu.rims.craft_verse.constant.ProductStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product")
@Setter
@Getter
public class Product extends Auditable {

    @Id
    @Column(name = "product_id", nullable = false, length = 255)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String productId;

    @Column(name = "product_title", nullable = false, length = 255)
    private String productTitle;

    @Column(name = "product_description", columnDefinition = "TEXT")
    private String productDescription;

    @Column(name = "product_price", nullable = false)
    private double productPrice;

    @Column(name = "product_stock_quantity", columnDefinition = "INT DEFAULT 0")
    private int productStockQuantity = 0;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = true)
    private Category category;

    @Column(name = "product_tags", columnDefinition = "TEXT")
    private String productTags;

    @Column(name = "product_image_url", columnDefinition = "TEXT")
    private String productImageUrl;

    @Column(name = "product_views", columnDefinition = "INT DEFAULT 0")
    private int productViews = 0;

    @Column(name = "product_likes", columnDefinition = "INT DEFAULT 0")
    private int productLikes = 0;

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "product")
    private List<Review> reviews;

    @OneToMany(mappedBy = "product")
    private List<Wishlist> wishlists;

    private String productStatus = ProductStatus.AVAILABLE.toString();

    @ManyToMany(mappedBy = "products")
    private List<Widget> widgets;

    public void addWidget(Widget widget) {
        if (widgets == null)
            widgets = new ArrayList<>();

        widgets.add(widget);
    }

    public void removeWidget(Widget widget) {
        widgets.remove(widget);
    }

}
