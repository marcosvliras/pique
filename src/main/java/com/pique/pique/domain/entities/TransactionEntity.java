package com.pique.pique.domain.entities;

import jakarta.persistence.*;
import org.apache.catalina.User;

import java.time.LocalDateTime;

@Entity(name = "transactions")
@Table(name = "transactions")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double value;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private UserEntity sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private UserEntity receiver;

    private LocalDateTime timestamp;

    public TransactionEntity(UserEntity sender, UserEntity receiver, Double value) {
        this.sender = sender;
        this.receiver = receiver;
        this.value = value;
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public UserEntity getSender() {
        return sender;
    }

    public void setSender(UserEntity sender) {
        this.sender = sender;
    }

    public UserEntity getReceiver() {
        return receiver;
    }

    public void setReceiver(UserEntity receiver) {
        this.receiver = receiver;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
