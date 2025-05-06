package dev.luizleal.ecommerce.persistence.repository;

import dev.luizleal.ecommerce.persistence.entity.Product;
import dev.luizleal.ecommerce.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findBySeller(User seller);
}
