package org.yearup.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private int orderId;
    private int userId;
    private BigDecimal shippingAmount;
    private LocalDateTime date;
    private String address;
    private String city;
    private String state;

    public Order(int orderId, int userId, BigDecimal shippingAmount, LocalDateTime date, String address, String city, String state) {
        this.orderId = orderId;
        this.userId = userId;
        this.shippingAmount = shippingAmount;
        this.date = date;
        this.address = address;
        this.city = city;
        this.state = state;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getUserId() {
        return userId;
    }

    public BigDecimal getShippingAmount() {
        return shippingAmount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }
}
