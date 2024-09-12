package com.Peaqock.product_service.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Peaqock.product_service.Model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{

}
