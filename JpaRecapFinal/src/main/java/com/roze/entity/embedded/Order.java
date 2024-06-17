package com.roze.entity.embedded;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Order {
    @EmbeddedId
    private OrderId orderId;
    private String orderInfo;
    private String orderDetails;
}
