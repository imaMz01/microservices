package com.Peaqock.order_service.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.Peaqock.order_service.Dto.InventoryResponse;
import com.Peaqock.order_service.Events.OrderPlacedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Peaqock.order_service.Dto.OrderLineItemsDto;
import com.Peaqock.order_service.Dto.OrderRequest;
import com.Peaqock.order_service.Model.Order;
import com.Peaqock.order_service.Model.OrderLineItems;
import com.Peaqock.order_service.Repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

	private final WebClient.Builder webClientBuilder;
	private final OrderRepository orderRepository;
	private final KafkaTemplate<String,OrderPlacedEvent> kafkaTemplate;

	public String placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString()); 
		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDto().stream()
		.map(this::mapToDto)
		.toList();
		order.setOrderLineItems(orderLineItems);
		List<String> skuCodes = order.getOrderLineItems().stream().map(OrderLineItems::getSkuCode).toList();
		//call Inventory service, and place order if product is in stock
		InventoryResponse [] inventoryResponses = webClientBuilder.build().get()
				.uri("http://inventory-service/api/inventory",uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
						.retrieve()
								.bodyToMono(InventoryResponse[].class)
										.block();
		for(InventoryResponse inventoryResponse:inventoryResponses){
			log.info("test");

			System.out.println("is in stock "+inventoryResponse.isInStock());
			System.out.println(inventoryResponse.getSkuCode());
		}
		boolean isInStock = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);

		if(isInStock){
			orderRepository.save(order);
			kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumber()));
			return "Order placed successfully";
		}else{
			throw new IllegalArgumentException("Product is not in stock, please try again later");
		}

	}
	
	private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
		OrderLineItems orderLineItems =new OrderLineItems();
		orderLineItems.setId(orderLineItemsDto.getId());
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
		return orderLineItems;
	}
	
}
