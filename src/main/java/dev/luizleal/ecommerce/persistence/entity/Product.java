package dev.luizleal.ecommerce.persistence.entity;

import dev.luizleal.ecommerce.persistence.common.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "tb_products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "quantity")
    private int quantity = 0;

    @Column(name = "created_at")
    @CurrentTimestamp
    private Instant createdAt;

    @Column(name = "status")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

}
