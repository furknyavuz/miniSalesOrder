package com.furkanyavuz.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.furkanyavuz.dao.CustomerDao;
import com.furkanyavuz.model.Customer;

public class CustomerServicesImpl implements CustomerServices {

	@Autowired
	CustomerDao customerDao;

	@Override
	public boolean addCustomer(Customer customer) throws Exception {
		return customerDao.addCustomer(customer);
	}

	@Override
	public Customer getCustomerByCode(String code) throws Exception {
		return customerDao.getCustomerByCode(code);
	}

	@Override
	public List<Customer> getCustomers() throws Exception {
		return customerDao.getCustomers();
	}

	@Override
	public boolean deleteCustomer(String code) throws Exception {
		return customerDao.deleteCustomer(code);
	}

}
