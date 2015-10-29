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
import com.furkanyavuz.model.OrderLine;
import com.furkanyavuz.model.Product;
import com.furkanyavuz.model.SalesOrder;
import com.furkanyavuz.services.CustomerServices;
import com.furkanyavuz.services.ProductServices;
import com.furkanyavuz.services.SalesOrderServices;

@Controller
@RequestMapping("/sales-order")
public class SalesOrderController {

	@Autowired
	SalesOrderServices salesOrderServices;

	@Autowired
	ProductServices productServices;

	@Autowired
	CustomerServices customerServices;

	static final Logger logger = Logger.getLogger(SalesOrderController.class);

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean addSalesOrder(@RequestBody SalesOrder salesOrder) {
		try {
			if (!checkProductQuantities(salesOrder) || insufficientBalance(salesOrder)) {
				return false;
			}
			
			updateProductQuantities(salesOrder);

			salesOrderServices.addSalesOrder(salesOrder);
			return true;
		} catch (Exception e) {
			logger.error(e.getStackTrace().toString());
			return false;
		}
	}

	private boolean insufficientBalance(SalesOrder salesOrder) throws Exception {
		Customer customer = customerServices.getCustomerByCode(salesOrder.getCustomer());
		if(customer == null) {
			return true;
		}
		
		Float creditLimit = Float.valueOf(customer.getCreditLimit());
		Float currentCredit = Float.valueOf(customer.getCurrentCredit());
		Float totalPrice = Float.valueOf(salesOrder.getTotalPrice());
		if(creditLimit - currentCredit < totalPrice) {
			return true;
		}
		customer.setCurrentCredit(String.valueOf(currentCredit + totalPrice));
		customerServices.addCustomer(customer);
		
		return false;
	}

	private boolean checkProductQuantities(SalesOrder salesOrder) throws Exception {
		for (OrderLine orderLine : salesOrder.getOrderLines()) {
			Product product = productServices.getProductByCode(orderLine.getProduct());
			if (Integer.valueOf(product.getQuantity()) < orderLine.getQuantity()) {
				// Not enough products left
				return false;
			}
		}
		return true;
	}

	private void updateProductQuantities(SalesOrder salesOrder) throws Exception {
		for (OrderLine orderLine : salesOrder.getOrderLines()) {
			Product product = productServices.getProductByCode(orderLine.getProduct());
			product.setQuantity(String.valueOf(Integer.valueOf(product.getQuantity()) - orderLine.getQuantity()));
			productServices.addProduct(product);
		}
	}

	@RequestMapping(value = "/{orderNumber}", method = RequestMethod.GET)
	public @ResponseBody SalesOrder getSalesOrder(@PathVariable("orderNumber") String orderNumber) {
		SalesOrder salesOrder = null;
		try {
			salesOrder = salesOrderServices.getSalesOrderByOrderNumber(orderNumber);

		} catch (Exception e) {
			logger.error(e.getStackTrace().toString());
		}
		return salesOrder;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List<SalesOrder> getSalesOrders() {

		List<SalesOrder> salesOrders = null;
		try {
			salesOrders = salesOrderServices.getSalesOrders();

		} catch (Exception e) {
			logger.error(e.getStackTrace().toString());
		}

		return salesOrders;
	}

	@RequestMapping(value = "delete/{orderNumber}", method = RequestMethod.GET)
	public @ResponseBody boolean deleteSalesOrder(@PathVariable("orderNumber") String orderNumber) {

		try {
			return salesOrderServices.deleteSalesOrder(orderNumber);
		} catch (Exception e) {
			logger.error(e.getStackTrace().toString());
			return false;
		}
	}
}
