package com.furkanyavuz.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.furkanyavuz.model.OrderLine;
import com.furkanyavuz.model.SalesOrder;

public class SalesOrderDaoImpl implements SalesOrderDao {

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addSalesOrder(SalesOrder salesOrder) throws Exception {

		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.saveOrUpdate(salesOrder);
		for (OrderLine orderLine : salesOrder.getOrderLines()) {
			session.save(orderLine);
		}
		tx.commit();
		session.close();

		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public SalesOrder getSalesOrderByOrderNumber(String orderNumber) throws Exception {
		session = sessionFactory.openSession();

		Query query = session.createQuery("from SalesOrder where order_number = :orderNumber ");
		query.setParameter("orderNumber", orderNumber);
		List<SalesOrder> list = query.list();

		SalesOrder salesOrder = null;
		if (list != null && list.size() > 0) {
			salesOrder = (SalesOrder) list.get(0);
		}
		return salesOrder;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SalesOrder> getSalesOrders() throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<SalesOrder> salesOrders = session.createCriteria(SalesOrder.class).list();
		tx.commit();
		session.close();
		return salesOrders;
	}

	@Override
	public boolean deleteSalesOrder(String orderNumber) throws Exception {
		session = sessionFactory.openSession();

		Query query = session.createQuery("delete SalesOrder where order_number = :orderNumber ");
		query.setParameter("orderNumber", orderNumber);
		int result = query.executeUpdate();

		if (result == -1 || result == 0) {
			return false;
		} else {
			return true;
		}
	}
}
