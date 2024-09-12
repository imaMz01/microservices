package com.Peaqock.inventory_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.Peaqock.inventory_service.Model.Inventory;
import com.Peaqock.inventory_service.Repository.InventoryRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
		
		
	}

//	@Bean
//	public CommandLineRunner loodData(InventoryRepository inventoryRepository) {
//		return args->{
//			Inventory inventory = new Inventory();
//			inventory.setSkuCode("usb_code");
//			inventory.setQuantity(12);
//			
//			Inventory inventory1 = new Inventory();
//			inventory1.setSkuCode("mouse_code");
//			inventory1.setQuantity(10);
//			
//			inventoryRepository.save(inventory);
//			inventoryRepository.save(inventory1);
//		};
//	}
}
