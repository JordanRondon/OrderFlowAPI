package com.OrderFlowAPI.OrderFlowAPI.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "order")
public class Order {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User employee;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private OrderStatus status;

    @Column(name = "client_name", nullable = false, length = 30)
    private String clientName;

    @Column(name = "table_number", nullable = true)
    private int tableNumber;

    @Column(name = "total_amount", nullable = false)
    private double totalAmount;

    @Column(name = "registration_date", nullable = false)
    private LocalDateTime date;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> products;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderCombo> combos;

    public Order() {
    }

    public Order(int orderId, User employee, OrderStatus status, String clientName, int tableNumber, double totalAmount,
            LocalDateTime date, List<OrderProduct> products, List<OrderCombo> combos) {
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

    public List<OrderProduct> getProducts() {
        return this.products;
    }

    public void setProducts(List<OrderProduct> products) {
        this.products = products;
    }

    public void addProduct(OrderProduct orderPorduct) {
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
