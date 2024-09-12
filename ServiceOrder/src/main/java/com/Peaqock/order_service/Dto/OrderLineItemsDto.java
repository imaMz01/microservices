package com.Peaqock.order_service.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor 
@NoArgsConstructor
public class OrderLineItemsDto {

	private Long id;
	private String skuCode;
	private double price;
	private Integer quantity;
}
