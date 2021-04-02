package com.navi.stock_exchange.repositories;

import com.navi.stock_exchange.models.Stock;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Abhinav Tripathi 13/03/21
 */
@Data
public class StockRepository {
    private static Map<String, Stock> stockMap = new HashMap<>();

    public static Stock addStock(Stock stock) {
        return stockMap.put(stock.getCode(), stock);
    }

    public static Set<String> getAllStockCodes() {
        return stockMap.keySet();
    }
}
