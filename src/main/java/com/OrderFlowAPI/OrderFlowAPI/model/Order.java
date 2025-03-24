package com.OrderFlowAPI.OrderFlowAPI.model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private int orderId;
    private User employee;
    private OrderStatus status;
    private String clientName;
    private int tableNumber;
    private double totalAmount;
    private LocalDateTime date;
    private List<OrderPorduct> products;
    private List<OrderCombo> combos;

    public Order() {
    }

    public Order(int orderId, User employee, OrderStatus status, String clientName, int tableNumber, double totalAmount,
            LocalDateTime date, List<OrderPorduct> products, List<OrderCombo> combos) {
        this.orderId = orderId;
        this.employee = employee;
        this.status = status;
        this.clientName = clientName;
        this.tableNumber = tableNumber;
        this.totalAmount = totalAmount;
        this.date = date;
        this.products = products;
        this.combos = combos;
    }

    public int getOrderId() {
        return this.orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public User getEmployee() {
        return this.employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    public OrderStatus getStatus() {
        return this.status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getClientName() {
        return this.clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getTableNumber() {
        return this.tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public double getTotalAmount() {
        return this.totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<OrderPorduct> getProducts() {
        return this.products;
    }

    public void setProducts(List<OrderPorduct> products) {
        this.products = products;
    }

    public void addProduct(OrderPorduct orderPorduct) {
        this.products.add(orderPorduct);
    }

    public void removeProduct(int productId) {
        this.products.remove(productId);
    }

    public List<OrderCombo> getCombos() {
        return this.combos;
    }

    public void setCombos(List<OrderCombo> combos) {
        this.combos = combos;
    }

    public void addCombo(OrderCombo orderCombo) {
        this.combos.add(orderCombo);
    }

    public void removeCombo(int comboId) {
        this.combos.remove(comboId);
    }
}
