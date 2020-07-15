package co.edu.cedesistemas.commerce.model;

import lombok.Data;

@Data
public class OrderItem {
    private String productId;
    private Float finalPrice;
    private Integer quantity;
}
