package com.crm.springdemo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.crm.springdemo.dao.CustomerDAO;
import com.crm.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	private final CustomerDAO customerDAO;

	public CustomerServiceImpl(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	@Override
	@Transactional
	public List<Customer> getCustomers() {
		return customerDAO.getCustomers();
	}

	@Override
	@Transactional
	public void saveCustomer(Customer customer) {
		customerDAO.saveCustomer(customer);
	}

	@Override
	@Transactional
	public Customer getCustomer(int id) {
		return customerDAO.getCustomer(id);
	}

	@Override
	@Transactional
	public void deleteCustomer(int id) {
		customerDAO.deleteCustomer(id);
	}

	@Override
	@Transactional
	public List<Customer> searchCustomers(String searchName) {
		return customerDAO.searchCustomers(searchName);
	}

	@Override
	@Transactional
	public List<Customer> getCustomers(int sortField) {
		return customerDAO.getCustomers(sortField);
	}

}
