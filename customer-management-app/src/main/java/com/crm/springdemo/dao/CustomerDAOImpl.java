package com.crm.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.crm.springdemo.entity.Customer;
import com.crm.springdemo.util.SortUtils;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	private final SessionFactory factory;

	public CustomerDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	public List<Customer> getCustomers() {
		
		Session session = factory.getCurrentSession();
		
		Query<Customer> query = session.createQuery("from Customer order by lastName", Customer.class);
		
		return query.getResultList();
	}

	@Override
	public void saveCustomer(Customer customer) {
		Session session = factory.getCurrentSession();
		
		session.saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomer(int id) {
		Session session = factory.getCurrentSession();

		return session.get(Customer.class, id);
	}

	@Override
	public void deleteCustomer(int id) {
		Session session = factory.getCurrentSession();
		
		Query query = session.createQuery("delete from Customer where id=:customerId");
		
		query.setParameter("customerId", id);
		
		query.executeUpdate();
	}

	@Override
	public List<Customer> searchCustomers(String searchName) {
		Session session = factory.getCurrentSession();
		
		Query query;
		
		if (searchName != null && searchName.trim().length() > 0) {
			query = session.createQuery("from Customer where lower(firstName) like :name or lower(lastName) like :name", Customer.class);
			query.setParameter("name", "%" + searchName.toLowerCase() + "%");
		} else {
			query = session.createQuery("from Customer", Customer.class);
		}
		
		return query.getResultList();
	}

	@Override
	public List<Customer> getCustomers(int sortField) {
		Session session = factory.getCurrentSession();
		
		String fieldName = null;
		
		switch(sortField) {
			case SortUtils.FIRST_NAME:
				fieldName = "firstName";
				break;
			case SortUtils.LAST_NAME:
				fieldName = "lastName";
				break;
			case SortUtils.EMAIL:
				fieldName = "email";
				break;
		}
		
		String queryString = "from Customer order by " + fieldName;
		
		Query<Customer> query = session.createQuery(queryString, Customer.class);
		
		return query.getResultList();
	}

}
