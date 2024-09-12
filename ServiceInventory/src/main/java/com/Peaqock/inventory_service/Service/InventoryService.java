package com.Peaqock.inventory_service.Service;

import com.Peaqock.inventory_service.Dto.InventoryResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Peaqock.inventory_service.Repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor 
public class InventoryService {

	private final InventoryRepository inventoryRepository;
	
	@Transactional(readOnly = true)
	public List<InventoryResponse> isInStock(List<String> skuCode) {
		return inventoryRepository.findInventoryBySkuCodeIn(skuCode).stream()
				.map( entity -> InventoryResponse.builder()
						.skuCode(entity.getSkuCode())
						.isInStock(entity.getQuantity() > 0)
						.build())
				.toList();
	}
	
}
