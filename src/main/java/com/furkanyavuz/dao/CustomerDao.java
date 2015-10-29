package com.furkanyavuz.dao;

import java.util.List;

import com.furkanyavuz.model.Customer;

public interface CustomerDao {

	public boolean addCustomer(Customer customer) throws Exception;

	public Customer getCustomerByCode(String code) throws Exception;

	public List<Customer> getCustomers() throws Exception;

	public boolean deleteCustomer(String code) throws Exception;
}
