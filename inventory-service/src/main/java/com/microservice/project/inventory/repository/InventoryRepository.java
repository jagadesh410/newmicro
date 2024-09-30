package com.microservice.project.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.project.inventory.model.InventoryProduct;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryProduct, Long>{

}
