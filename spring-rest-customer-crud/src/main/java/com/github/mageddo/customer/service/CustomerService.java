package com.github.mageddo.customer.service;

import com.github.mageddo.customer.dao.CustomerDAO;
import com.github.mageddo.customer.entity.CustomerEntity;
import com.github.mageddo.customer.exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {

	@Autowired
	private CustomerDAO customerDAO;

	@Transactional
	public void create(CustomerEntity customerEntity) {
		customerDAO.merge(customerEntity);
	}

	@Transactional
	public void update(CustomerEntity customerEntity) {
		validateCustomerExistence(customerEntity.getId());
		customerDAO.merge(customerEntity);
	}

	@Transactional
	public void delete(Long customerId) {
		validateCustomerExistence(customerId);
		customerDAO.delete(customerId);
	}

	public List<CustomerEntity> findAll() {
		return customerDAO.findAll();
	}

	public CustomerEntity find(Long customerId) {
		return customerDAO.find(customerId);
	}

	public boolean exists(Long customerId){
		return customerDAO.find(customerId) != null;
	}

	void validateCustomerExistence(Long customerId) {
		if(!exists(customerId)){
			throw new CustomerNotFoundException("Customer not found: " + customerId);
		}
	}
}
