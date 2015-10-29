package com.furkanyavuz.dao;

import java.util.List;

import com.furkanyavuz.model.SalesOrder;

public interface SalesOrderDao {

	public boolean addSalesOrder(SalesOrder salesOrder) throws Exception;

	public SalesOrder getSalesOrderByOrderNumber(String orderNumber) throws Exception;

	public List<SalesOrder> getSalesOrders() throws Exception;

	public boolean deleteSalesOrder(String orderNumber) throws Exception;
}
