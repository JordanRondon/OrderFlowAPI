package com.OrderFlowAPI.OrderFlowAPI.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "orders_id")
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
    private Integer tableNumber;

    @Column(name = "total_amount", nullable = false)
    private double totalAmount;

    @Column(name = "registration_date", nullable = false)
    private LocalDateTime date;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> products = new ArrayList<OrderProduct>();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderCombo> combos = new ArrayList<OrderCombo>();

    public Order(User employee, OrderStatus status, String clientName, Integer tableNumber,
            double totalAmount, LocalDateTime date) {
        this.employee = employee;
        this.status = status;
        this.clientName = clientName;
        this.tableNumber = tableNumber;
        this.totalAmount = totalAmount;
        this.date = date;
    }
}