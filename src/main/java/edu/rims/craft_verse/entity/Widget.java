package edu.rims.craft_verse.entity;

import java.util.List;

import edu.rims.craft_verse.constant.WidgetStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "widget")
@Setter
@Getter
public class Widget extends Auditable {
    @Id
    @Column(name = "widget_id" ,nullable = false, length = 255)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String widgetId;

    @Column(name = "widget_name" ,nullable = false, length = 100)
    private String widgetName;

    @Enumerated(EnumType.STRING)
    private  WidgetStatus widgetstatus =WidgetStatus.ACTIVE;

    @ManyToMany
    @JoinTable(name = "widget_product", joinColumns = @JoinColumn(name="widget_id"), inverseJoinColumns= @JoinColumn(name = "product_id"))
    private List<Product> products;

    
}
