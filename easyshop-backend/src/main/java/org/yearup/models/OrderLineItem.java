package org.yearup.models;

import java.math.BigDecimal;

public class OrderLineItem {
    private int orderLineItemId;
    private int orderId;
    private int productId;
    private BigDecimal salesPrice;
    private int quantity;
    private BigDecimal discount;

    public OrderLineItem(int orderLineItemId, int orderId, int productId, BigDecimal salesPrice, int quantity, BigDecimal discount) {
        this.orderLineItemId = orderLineItemId;
        this.orderId = orderId;
        this.productId = productId;
        this.salesPrice = salesPrice;
        this.quantity = quantity;
        this.discount = discount;
    }

    public int getOrderLineItemId() {
        return orderLineItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getProductId() {
        return productId;
    }

    public BigDecimal getSalesPrice() {
        return salesPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getDiscount() {
        return discount;
    }
}
