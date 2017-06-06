package com.epam.mentoring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "BALANCE")
    private Double balance;

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public Double getBalance() {
	return balance;
    }

    public void setBalance(Double balance) {
	this.balance = balance;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    @Override
    public String toString() {
	return "Account [id=" + id + ", user=" + user + ", balance=" + balance + "]";
    }
    
}
