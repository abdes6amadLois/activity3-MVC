package org.lois.demoapp31.repository;

import org.lois.demoapp31.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
