package com.salesmanagement.system.repositories;

import com.salesmanagement.system.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
