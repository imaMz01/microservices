package com.Peaqock.order_service.FeignClient;

import com.Peaqock.order_service.Dto.InventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@FeignClient(name = "inventory-service")
public interface InventoryFeign {
    @GetMapping("/api/inventory")
     List<InventoryResponse> isInStock(@RequestParam("skuCode") List<String> skuCode);
}
