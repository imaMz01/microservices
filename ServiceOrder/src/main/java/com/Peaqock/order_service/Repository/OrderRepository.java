package com.Peaqock.order_service.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Peaqock.order_service.Model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long>{

}
