package com.github.mageddo.customer.dao;

import com.github.mageddo.customer.entity.CustomerEntity;

import java.util.List;

public interface CustomerDAO {

	CustomerEntity merge(CustomerEntity customerEntity);

	CustomerEntity find(Long customerId);

	List<CustomerEntity> findAll();

	void delete(Long customerId);

	CustomerEntity find(String name);
}
