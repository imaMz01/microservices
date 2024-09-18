package com.Peaqock.order_service.Controller;

import com.Peaqock.order_service.Dto.InventoryResponse;
import com.Peaqock.order_service.FeignClient.InventoryFeign;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.Peaqock.order_service.Dto.OrderRequest;
import com.Peaqock.order_service.Service.OrderService;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;


	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
	//@TimeLimiter(name = "inventory")
	public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest) {
		return CompletableFuture.supplyAsync(() -> orderService.placeOrder(orderRequest));
	}

	public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException){
		return CompletableFuture.supplyAsync(() -> "Oops !! Something went wrong, please order after some time");
	}
}
