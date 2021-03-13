package com.crm.springdemo.service;

import java.util.List;

import com.crm.springdemo.entity.Customer;

public interface CustomerService {
	List<Customer> getCustomers();
	
	void saveCustomer(Customer customer);

	Customer getCustomer(int id);

	void deleteCustomer(int id);

	List<Customer> searchCustomers(String searchName);

	List<Customer> getCustomers(int sortField);
}
