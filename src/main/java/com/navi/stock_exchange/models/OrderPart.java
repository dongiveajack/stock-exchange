package com.navi.stock_exchange.models;

import lombok.Data;

/**
 * @author Abhinav Tripathi 13/03/21
 */
@Data
public class OrderPart {
    private String orderId;
    private String stockCode;
    private Integer quantity;
    private double price;
    private String otherOrderId;
}
