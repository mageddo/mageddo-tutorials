package com.github.mageddo.customer.dao;

import com.github.mageddo.customer.entity.CustomerEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CustomerDAOHsqldb implements CustomerDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public CustomerEntity merge(CustomerEntity customerEntity) {
		return entityManager.merge(customerEntity);
	}

	@Override
	public CustomerEntity find(Long customerId) {
		return entityManager.find(CustomerEntity.class, customerId);
	}

	@Override
	public List<CustomerEntity> findAll() {
		return entityManager
				.createQuery("FROM CustomerEntity")
				.getResultList();
	}

	@Override
	public void delete(Long customerId) {
		entityManager
				.createQuery("DELETE FROM CustomerEntity WHERE id = :customerId")
				.setParameter("customerId", customerId)
				.executeUpdate();
	}

	@Override
	public CustomerEntity find(String name) {
		return (CustomerEntity) entityManager
				.createQuery("FROM CustomerEntity WHERE name = :name")
				.setParameter("name", name)
				.getSingleResult();
	}
}
