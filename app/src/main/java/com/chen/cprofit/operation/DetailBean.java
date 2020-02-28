package com.chen.cprofit.operation;

import com.chen.cprofit.stock.StockBean;

public class DetailBean {
    private OrderBean order;
    private StockBean stock;

    public StockBean getStock() {
        return stock;
    }

    public void setStock(StockBean stock) {
        this.stock = stock;
    }

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }
}
