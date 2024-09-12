package com.Peaqock.inventory_service.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Peaqock.inventory_service.Model.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	Optional<Inventory> findBySkuCode(String skuCode);

	// @Query("""
	// select i from Inventory i where i.skuCode in :skuCode
	// """)
	List<Inventory> findInventoryBySkuCodeIn(List<String> skuCode);
}
