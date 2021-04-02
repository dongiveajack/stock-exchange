package com.navi.stock_exchange;

import com.navi.stock_exchange.models.SellOrder;

import java.util.Comparator;

/**
 * @author Abhinav Tripathi 13/03/21
 */
public class SellOrderComparator implements Comparator<SellOrder> {
    @Override
    public int compare(SellOrder first, SellOrder second) {
        if (first.getPrice() < second.getPrice())
            return -1;
        else if (first.getPrice() == second.getPrice()) {
            return first.getCreatedAt().compareTo(second.getCreatedAt());
        } else {
            return 1;
        }
    }
}
