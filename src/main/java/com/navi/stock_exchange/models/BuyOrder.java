package com.navi.stock_exchange.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Abhinav Tripathi 13/03/21
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BuyOrder extends Order {
    public BuyOrder(String orderId, int quantity, double price, String createdAt, String stockCode) {
        super(orderId, quantity, price, createdAt, stockCode);
    }
}
