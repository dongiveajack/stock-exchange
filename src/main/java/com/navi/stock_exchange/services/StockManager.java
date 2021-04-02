package com.navi.stock_exchange.services;

import com.navi.stock_exchange.BuyOrderComparator;
import com.navi.stock_exchange.SellOrderComparator;
import com.navi.stock_exchange.models.BuyOrder;
import com.navi.stock_exchange.models.Order;
import com.navi.stock_exchange.models.OrderPart;
import com.navi.stock_exchange.models.SellOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Observable;
import java.util.Observer;
import java.util.PriorityQueue;

/**
 * @author Abhinav Tripathi 13/03/21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class StockManager extends Observable {
    private PriorityQueue<BuyOrder> maxBuyOrderHeap;
    private PriorityQueue<SellOrder> minSellOrderHeap;

    public StockManager(StockExchange stockExchange) {
        this.maxBuyOrderHeap = new PriorityQueue<>(new BuyOrderComparator());
        this.minSellOrderHeap = new PriorityQueue<>(new SellOrderComparator());
        this.subscribe(stockExchange);
    }

    public <T extends Observer> void subscribe(T object) {
        this.addObserver(object);
    }

    public void submitOrder(Order order) {
        if (order instanceof BuyOrder)
            maxBuyOrderHeap.add((BuyOrder) order);
        else if (order instanceof SellOrder)
            minSellOrderHeap.add((SellOrder) order);
        else
            throw new RuntimeException("Invalid order type");

        matchOrders();
    }

    public void matchOrders() {
        while (!maxBuyOrderHeap.isEmpty() && !minSellOrderHeap.isEmpty()
                && maxBuyOrderHeap.peek().getPrice() >= minSellOrderHeap.peek().getPrice()) {
            BuyOrder buyOrder = maxBuyOrderHeap.poll();
            SellOrder sellOrder = minSellOrderHeap.poll();

            int buyOrderQuantity = buyOrder.getQuantity() - sellOrder.getQuantity() > 0 ? sellOrder.getQuantity() : buyOrder.getQuantity();
            int sellOrderQuantity = sellOrder.getQuantity() - buyOrder.getQuantity() > 0 ? buyOrder.getQuantity() : sellOrder.getQuantity();

            OrderPart buyOrderPart = new OrderPart();
            buyOrderPart.setOrderId(buyOrder.getOrderId());
            buyOrderPart.setOtherOrderId(sellOrder.getOrderId());
            buyOrderPart.setQuantity(buyOrderQuantity);
            buyOrderPart.setPrice(sellOrder.getPrice());
            buyOrder.addOrderPart(buyOrderPart);

            int prevBuyQuantity = buyOrder.getQuantity();
            if (buyOrder.getQuantity() > sellOrder.getQuantity()) {
                buyOrder.setQuantity(buyOrder.getQuantity() - sellOrder.getQuantity());
                maxBuyOrderHeap.add(buyOrder);
            }

            OrderPart sellOrderPart = new OrderPart();
            sellOrderPart.setOrderId(sellOrder.getOrderId());
            sellOrderPart.setOtherOrderId(buyOrder.getOrderId());
            sellOrderPart.setQuantity(sellOrderQuantity);
            sellOrderPart.setPrice(sellOrder.getPrice());
            sellOrder.addOrderPart(sellOrderPart);

            if (sellOrder.getQuantity() - buyOrderQuantity > 0) {
                sellOrder.setQuantity(sellOrder.getQuantity() - prevBuyQuantity);
                minSellOrderHeap.add(sellOrder);
            }

            String log = String.format("%s %.2f %s %s", buyOrderPart.getOrderId(), buyOrderPart.getPrice(), buyOrderPart.getQuantity(), buyOrderPart.getOtherOrderId());
            setChanged();
            notifyObservers(log);
        }
    }
}
