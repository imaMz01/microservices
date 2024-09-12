package com.Peaqock.product_service.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Peaqock.product_service.Dto.ProductRequest;
import com.Peaqock.product_service.Dto.ProductResponse;
import com.Peaqock.product_service.Model.Product;
import com.Peaqock.product_service.Repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

	private final ProductRepository productRepository;
	
	public void createProduct(ProductRequest productRequest) {
		Product product = Product.builder()
				.name(productRequest.getName())
				.description(productRequest.getDescription())
				.price(productRequest.getPrice())
				.build();
		productRepository.save(product);
		log.info("Product with id {} is saved",product.getId());
		
	}
	
	public List<ProductResponse> getAllProduct(){
		List<Product> products = (List<Product>) productRepository.findAll();
//		return products.stream().map(product -> mapToProducResponse(product)).toList();
		return products.stream().map(this::mapToProducResponse).toList();
	}
	
	private ProductResponse mapToProducResponse(Product product) {
		return ProductResponse.builder()
				.id(product.getId())
				.description(product.getDescription())
				.price(product.getPrice())
				.name(product.getName())
				.build();
	}
}
