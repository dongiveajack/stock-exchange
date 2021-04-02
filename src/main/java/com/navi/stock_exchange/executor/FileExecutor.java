package com.navi.stock_exchange.executor;

import com.navi.stock_exchange.models.BuyOrder;
import com.navi.stock_exchange.models.Order;
import com.navi.stock_exchange.models.SellOrder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Abhinav Tripathi 13/03/21
 */
public class FileExecutor {
    private static final Integer ORDER_ID = 0, CREATED_AT = 1, STOCK_CODE = 2, ORDER_TYPE = 3, PRICE = 4, QUANTITY = 5;

    public static List<Order> fetchOrdersFromFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        List<String> lines = Files.readAllLines(path);
        List<Order> ordersForStockExchange = new ArrayList<>();
        for (String input : lines) {
            String[] params = input.replace("  ", " ").split(" ");
            Order order;
            if (params[ORDER_TYPE].equalsIgnoreCase("sell")) {
                order = new SellOrder(params[ORDER_ID], Integer.valueOf(params[QUANTITY]), Double.valueOf(params[PRICE]), params[CREATED_AT], params[STOCK_CODE]);
            } else {
                order = new BuyOrder(params[0], Integer.valueOf(params[5]), Double.valueOf(params[4]), params[1], params[2]);
            }
            ordersForStockExchange.add(order);
        }
        return ordersForStockExchange;
    }
}
