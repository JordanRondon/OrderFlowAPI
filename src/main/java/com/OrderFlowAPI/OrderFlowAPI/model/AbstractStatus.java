package com.OrderFlowAPI.OrderFlowAPI.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractStatus {
    @Column(name = "status_name", nullable = false, length = 20)
    protected String name;

    protected AbstractStatus(String name) {
        this.name = name;
    }
}
