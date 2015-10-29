package com.furkanyavuz.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.furkanyavuz.model.Customer;
import com.furkanyavuz.services.CustomerServices;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerServices customerServices;

	static final Logger logger = Logger.getLogger(CustomerController.class);

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean addCustomer(@RequestBody Customer customer) {
		try {
			customerServices.addCustomer(customer);
			return true;
		} catch (Exception e) {
			logger.error(e.getStackTrace().toString());
			return false;
		}
	}

	@RequestMapping(value = "/{code}", method = RequestMethod.GET)
	public @ResponseBody Customer getCustomer(@PathVariable("code") String code) {
		Customer customer = null;
		try {
			customer = customerServices.getCustomerByCode(code);

		} catch (Exception e) {
			logger.error(e.getStackTrace().toString());
		}
		return customer;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List<Customer> getCustomers() {

		List<Customer> customers = null;
		try {
			customers = customerServices.getCustomers();

		} catch (Exception e) {
			logger.error(e.getStackTrace().toString());
		}

		return customers;
	}

	@RequestMapping(value = "delete/{code}", method = RequestMethod.GET)
	public @ResponseBody boolean deleteCustomer(@PathVariable("code") String code) {

		try {
			return customerServices.deleteCustomer(code);
		} catch (Exception e) {
			logger.error(e.getStackTrace().toString());
			return false;	
		}
	}
}
