package com.github.mageddo.customer.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "CustomerEntity")
@Table(name = "CUSTOMER")
public class CustomerEntity {

	private Long id;
	private String name;
	private Integer age;
	private BigDecimal balance;

	@Id
	@GeneratedValue
	@Column(name = "IDT_CUSTOMER", length = 20, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "NAM_CUSTOMER", length = 255, nullable = false, unique = true)
	public String getName() {
		return name;
	}

	public CustomerEntity setName(String name) {
		this.name = name;
		return this;
	}

	@Column(name = "NUM_AGE", length = 3, nullable = false)
	public Integer getAge() {
		return age;
	}

	public CustomerEntity setAge(Integer age) {
		this.age = age;
		return this;
	}

	@Column(name = "NUM_BALANCE", length = 23, nullable = false)
	public BigDecimal getBalance() {
		return balance;
	}

	public CustomerEntity setBalance(BigDecimal balance) {
		this.balance = balance;
		return this;
	}

}
