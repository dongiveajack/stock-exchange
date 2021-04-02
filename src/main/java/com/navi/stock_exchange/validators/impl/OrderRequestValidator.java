package com.navi.stock_exchange.validators.impl;

import com.navi.stock_exchange.models.Order;
import com.navi.stock_exchange.validators.Validator;

import java.util.Objects;

/**
 * @author Abhinav Tripathi 13/03/21
 */
public class OrderRequestValidator implements Validator<Order> {
    @Override
    public boolean validate(Order order) {
        if (Objects.isNull(order.getOrderId())) {
            System.out.println("Validation Failed!! OrderId can't be empty");
            return false;
        }
        if (Objects.isNull(order.getStockCode())) {
            System.out.println("Validation Failed!! Order Request must contain Stock Code");
            return false;
        }
        if (Objects.isNull(order.getPrice())) {
            System.out.println("Validation Failed!! Price can't be empty");
            return false;
        }
        if (Objects.isNull(order.getQuantity())) {
            System.out.println("Validation Failed!! Quantity can't be empty");
            return false;
        }
        if (Objects.isNull(order.getCreatedAt())) {
            System.out.println("Validation Failed!! Order created date and time can't be empty");
            return false;
        }
        return true;
    }
}
