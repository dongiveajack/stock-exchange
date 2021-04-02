package com.navi.stock_exchange.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Abhinav Tripathi 13/03/21
 */
@Data
@NoArgsConstructor
public abstract class Order {
    private String orderId;
    private String stockCode;
    private int quantity;
    private double price;
    private DateTime createdAt;
    private Long userId;
    private List<OrderPart> orderParts;

    public void addOrderPart(OrderPart orderPart) {
        this.orderParts.add(orderPart);
    }

    public Order(String orderId, int quantity, double price, String createdAt, String stockCode) {
        this.orderId = orderId;
        this.quantity = quantity;
        this.price = price;
        String[] hourMinute = createdAt.split(":");
        DateTime date = new DateTime();
        date.withHourOfDay(Integer.valueOf(hourMinute[0]));
        date.withMinuteOfHour(Integer.valueOf(hourMinute[1]));
        this.createdAt = date;
        this.orderParts = new ArrayList<>();
        this.stockCode = stockCode;
    }
}
