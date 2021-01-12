package com.ecommerce.orders.domain;

import java.util.List;

public class UpdateStatusByID {
	private List<String> orderIDs;
    private OrderStatus status;

    public List<String> getOrderIDs() {
        return orderIDs;
    }

    public void setOrderIDs(List<String> orderIDs) {
        this.orderIDs = orderIDs;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Orders ID:{" + orderIDs.toString() + " Update Status to :{" + status.toString() + "}";
    }

}
