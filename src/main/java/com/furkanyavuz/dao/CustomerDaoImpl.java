package com.furkanyavuz.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.furkanyavuz.model.Customer;

public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addCustomer(Customer customer) throws Exception {

		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.saveOrUpdate(customer);
		tx.commit();
		session.close();

		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Customer getCustomerByCode(String code) throws Exception {
		session = sessionFactory.openSession();

		Query query = session.createQuery("from Customer where code = :code ");
		query.setParameter("code", code);
		List<Customer> list = query.list();

		Customer customer = null;
		if (list != null && list.size() > 0) {
			customer = (Customer) list.get(0);
		}	
		return customer;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getCustomers() throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<Customer> customers = session.createCriteria(Customer.class).list();
		tx.commit();
		session.close();
		return customers;
	}

	@Override
	public boolean deleteCustomer(String code) throws Exception {
		session = sessionFactory.openSession();

		Query query = session.createQuery("delete Customer where code = :code ");
		query.setParameter("code", code);
		int result = query.executeUpdate();

		if (result == -1 || result == 0) {
			return false;
		} else {
			return true;
		}
	}
}
