/**
 * 
 */
package com.furkanyavuz.test;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.furkanyavuz.model.Customer;
import com.furkanyavuz.model.OrderLine;
import com.furkanyavuz.model.Product;
import com.furkanyavuz.model.SalesOrder;

/**
 * @author furkan
 * 
 * Test sets for crud operations
 *
 */
@SuppressWarnings("deprecation")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CrudTests {

	private static final String TEST_CODE = "TEST_CODE";
	private static final String TEST_ORDER_NUMBER = "TEST_ORDER_NUMBER";
		
    private SessionFactory sessionFactory;
    private Session session = null;
    
    @Before
    public void before() {
     // setup the session factory
     AnnotationConfiguration configuration = new AnnotationConfiguration();
     configuration.addAnnotatedClass(Customer.class).addAnnotatedClass(OrderLine.class).addAnnotatedClass(Product.class).addAnnotatedClass(SalesOrder.class);
     configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
     configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
     configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/order_management_db");
     configuration.setProperty("hibernate.connection.username", "root");
     sessionFactory = configuration.buildSessionFactory();
     session = sessionFactory.openSession();
    }

	@SuppressWarnings("unchecked")
	@Test
	public void testCustomer1Add() {
		boolean itemAdded = false;
		Customer customer = new Customer();
		customer.setCode(TEST_CODE);
		customer.setCreditLimit("0");
		customer.setCurrentCredit("0");
		customer.setName("Test");
		customer.setPhone("+905301234578");
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(customer);
		tx.commit();
		
		Query query = session.createQuery("from Customer where code = :code ");
		query.setParameter("code", TEST_CODE);
		List<Customer> list = query.list();

		if (list != null && list.size() > 0) {
			itemAdded = true;
		}
		
		assertEquals(true, itemAdded);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCustomer2GetByCode() {
		boolean itemFound = false;
		
		Query query = session.createQuery("from Customer where code = :code ");
		query.setParameter("code", TEST_CODE);
		List<Customer> list = query.list();

		if (list != null && list.size() > 0) {
			itemFound = true;
		}
		
		assertEquals(true, itemFound);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCustomer3GetAll() {
		boolean itemsFound = false;
		
		Transaction tx = session.beginTransaction();
		List<Customer> customers = session.createCriteria(Customer.class).list();
		tx.commit();

		if (customers != null && customers.size() > 0) {
			itemsFound = true;
		}
		
		assertEquals(true, itemsFound);
	}
	
	@Test
	public void testCustomer4Delete() {
		boolean itemDeleted = false;
		
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("delete Customer where code = :code ");
		query.setParameter("code", TEST_CODE);
		int result = query.executeUpdate();
		tx.commit();

		if (result == -1 || result == 0) {
			itemDeleted = false;
		} else {
			itemDeleted = true;
		}
		
		assertEquals(true, itemDeleted);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testProduct1Add() {
		boolean itemAdded = false;
		Product product = new Product();
		product.setCode(TEST_CODE);
		product.setDescription("Test");
		product.setPrice("0");
		product.setQuantity("0");
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(product);
		tx.commit();
		
		Query query = session.createQuery("from Product where code = :code ");
		query.setParameter("code", TEST_CODE);
		List<Product> list = query.list();

		if (list != null && list.size() > 0) {
			itemAdded = true;
		}
		
		assertEquals(true, itemAdded);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testProduct2GetByCode() {
		boolean itemFound = false;
		
		Query query = session.createQuery("from Product where code = :code ");
		query.setParameter("code", TEST_CODE);
		List<Product> list = query.list();

		if (list != null && list.size() > 0) {
			itemFound = true;
		}
		
		assertEquals(true, itemFound);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testProduct3GetAll() {
		boolean itemsFound = false;
		
		Transaction tx = session.beginTransaction();
		List<Product> products = session.createCriteria(Product.class).list();
		tx.commit();

		if (products != null && products.size() > 0) {
			itemsFound = true;
		}
		
		assertEquals(true, itemsFound);
	}
	
	@Test
	public void testProduct4Delete() {
		boolean itemDeleted = false;
		
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("delete Product where code = :code ");
		query.setParameter("code", TEST_CODE);
		int result = query.executeUpdate();
		tx.commit();

		if (result == -1 || result == 0) {
			itemDeleted = false;
		} else {
			itemDeleted = true;
		}
		
		assertEquals(true, itemDeleted);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testSalesOrder1Add() {
		boolean itemAdded = false;
		SalesOrder salesOrder = new SalesOrder();
		salesOrder.setCustomer(TEST_CODE);
		salesOrder.setOrderNumber(TEST_ORDER_NUMBER);
		salesOrder.setTotalPrice("0");
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(salesOrder);
		tx.commit();
		
		Query query = session.createQuery("from SalesOrder where order_number = :orderNumber");
		query.setParameter("orderNumber", TEST_ORDER_NUMBER);
		List<SalesOrder> list = query.list();

		if (list != null && list.size() > 0) {
			itemAdded = true;
		}
		
		assertEquals(true, itemAdded);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testSalesOrder2GetByCode() {
		boolean itemFound = false;
		
		Query query = session.createQuery("from SalesOrder where order_number = :orderNumber ");
		query.setParameter("orderNumber", TEST_ORDER_NUMBER);
		List<SalesOrder> list = query.list();

		if (list != null && list.size() > 0) {
			itemFound = true;
		}
		
		assertEquals(true, itemFound);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testSalesOrder3GetAll() {
		boolean itemsFound = false;
		
		Transaction tx = session.beginTransaction();
		List<SalesOrder> salesOrders = session.createCriteria(SalesOrder.class).list();
		tx.commit();

		if (salesOrders != null && salesOrders.size() > 0) {
			itemsFound = true;
		}
		
		assertEquals(true, itemsFound);
	}
	
	@Test
	public void testSalesOrder4Delete() {
		boolean itemDeleted = false;
		
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("delete SalesOrder where order_number = :orderNumber ");
		query.setParameter("orderNumber", TEST_ORDER_NUMBER);
		int result = query.executeUpdate();
		tx.commit();

		if (result == -1 || result == 0) {
			itemDeleted = false;
		} else {
			itemDeleted = true;
		}
		
		assertEquals(true, itemDeleted);
	}
	
    @After
    public void after() {
		session.close();
	    sessionFactory.close();
    }
}
