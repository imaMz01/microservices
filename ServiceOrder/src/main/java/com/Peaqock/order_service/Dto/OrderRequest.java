package com.Peaqock.order_service.Dto;

import java.util.List;

import com.Peaqock.order_service.Model.OrderLineItems;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

	private List<OrderLineItemsDto> orderLineItemsDto;
}
