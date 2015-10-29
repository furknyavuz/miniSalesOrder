package com.furkanyavuz.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.furkanyavuz.dao.SalesOrderDao;
import com.furkanyavuz.model.SalesOrder;

public class SalesOrderServicesImpl implements SalesOrderServices {

	@Autowired
	SalesOrderDao salesOrderDao;

	@Override
	public boolean addSalesOrder(SalesOrder salesOrder) throws Exception {
		return salesOrderDao.addSalesOrder(salesOrder);
	}

	@Override
	public SalesOrder getSalesOrderByOrderNumber(String orderNumber) throws Exception {
		return salesOrderDao.getSalesOrderByOrderNumber(orderNumber);
	}

	@Override
	public List<SalesOrder> getSalesOrders() throws Exception {
		return salesOrderDao.getSalesOrders();
	}

	@Override
	public boolean deleteSalesOrder(String orderNumber) throws Exception {
		return salesOrderDao.deleteSalesOrder(orderNumber);
	}

}
