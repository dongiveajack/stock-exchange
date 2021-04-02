package com.navi.stock_exchange;

import com.navi.stock_exchange.executor.FileExecutor;
import com.navi.stock_exchange.models.Order;
import com.navi.stock_exchange.services.StockExchange;

import java.io.IOException;
import java.util.List;

public class StockExchangeApplication {
    public static void main(String[] args) throws IOException {
        List<Order> orders = FileExecutor.fetchOrdersFromFile(args[0]);
        //List<Order> orders = FileExecutor.fetchOrdersFromFile("/Users/abhinav/Desktop/stockTest.txt");
        orders.forEach(StockExchange::placeOrder);
    }
}
