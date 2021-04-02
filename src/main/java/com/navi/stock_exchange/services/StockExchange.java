package com.navi.stock_exchange.services;

import com.navi.stock_exchange.models.Order;
import com.navi.stock_exchange.models.Stock;
import com.navi.stock_exchange.repositories.StockRepository;
import com.navi.stock_exchange.validators.impl.OrderRequestValidator;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * @author Abhinav Tripathi 13/03/21
 */
@Data
public class StockExchange implements Observer {
    private List<String> orderLog;
    private Map<String, StockManager> stockManagerMap;
    private OrderRequestValidator orderRequestValidator;

    private static StockExchange instance;

    private StockExchange() {
        stockManagerMap = new HashMap<>();
        orderLog = new ArrayList<>();
        orderRequestValidator = new OrderRequestValidator();
    }

    public static StockExchange getInstance() {
        if (instance == null)
            instance = new StockExchange();
        return instance;
    }

    public static void registerStock(Stock stock) {
        StockRepository.addStock(stock);
        getInstance().getStockManagerMap().put(stock.getCode(), new StockManager(getInstance()));
    }

    public static void placeOrder(Order order) {
        getInstance().submitOrder(order);
    }

    private void submitOrder(Order order) {
        boolean validationPassed = orderRequestValidator.validate(order);
        if (!validationPassed)
            return;
        StockManager stockManager = stockManagerMap.get(order.getStockCode());
        if (stockManager == null) {
            Stock stock = new Stock(order.getStockCode(), order.getStockCode());
            registerStock(stock);
            //throw new RuntimeException(String.format("Stock %s is not Registered in StockExchange", order.getStockCode()));
        }
        stockManager = stockManagerMap.get(order.getStockCode());
        stockManager.submitOrder(order);
    }


    @Override
    public void update(Observable o, Object orderPart) {
        orderLog.add((String) orderPart);
        System.out.println(orderPart.toString());
    }
}
