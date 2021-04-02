package com.navi.stock_exchange;

import com.navi.stock_exchange.models.BuyOrder;

import java.util.Comparator;

/**
 * @author Abhinav Tripathi 13/03/21
 */
public class BuyOrderComparator implements Comparator<BuyOrder> {
    @Override
    public int compare(BuyOrder first, BuyOrder second) {
        if (first.getPrice() > second.getPrice())
            return -1;
        else if (first.getPrice() == second.getPrice()) {
            if (first.getCreatedAt().isBefore(second.getCreatedAt()))
                return -1;
            else
                return 1;
        } else {
            return 1;
        }
    }
}
