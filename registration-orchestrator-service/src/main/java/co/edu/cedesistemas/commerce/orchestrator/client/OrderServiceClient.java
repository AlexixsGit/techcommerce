package co.edu.cedesistemas.commerce.orchestrator.client;

import co.edu.cedesistemas.common.model.Status;
import lombok.Builder;
import lombok.Data;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "commerce-service", path = "/api/v1")
public interface OrderServiceClient {
    @PostMapping("/orders")
    ResponseEntity<String> createOrder(@RequestBody Order order);

    @DeleteMapping("/orders/{id}")
    ResponseEntity<Status<?>> deleteOrder(@PathVariable String id);

    @Data
    @Builder
    class Order {
        private String id;
        private String userId;
        private String storeId;
    }
}
