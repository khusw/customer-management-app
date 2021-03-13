package com.crm.springdemo.dao;

import java.util.List;

import com.crm.springdemo.entity.Customer;

public interface CustomerDAO {
	List<Customer> getCustomers();

	void saveCustomer(Customer customer);

	Customer getCustomer(int id);

	void deleteCustomer(int id);

	List<Customer> searchCustomers(String searchName);

	List<Customer> getCustomers(int sortField);
}
