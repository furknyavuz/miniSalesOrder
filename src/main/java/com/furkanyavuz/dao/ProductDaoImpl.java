package com.furkanyavuz.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.furkanyavuz.model.Product;

public class ProductDaoImpl implements ProductDao {

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addProduct(Product product) throws Exception {

		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.saveOrUpdate(product);
		tx.commit();
		session.close();

		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Product getProductByCode(String code) throws Exception {
		session = sessionFactory.openSession();

		Query query = session.createQuery("from Product where code = :code ");
		query.setParameter("code", code);
		List<Product> list = query.list();

		Product product = null;
		if (list != null && list.size() > 0) {
			product = (Product) list.get(0);
		}
		return product;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProducts() throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<Product> products = session.createCriteria(Product.class).list();
		tx.commit();
		session.close();
		return products;
	}

	@Override
	public boolean deleteProduct(String code) throws Exception {
		session = sessionFactory.openSession();

		Query query = session.createQuery("delete Product where code = :code ");
		query.setParameter("code", code);
		int result = query.executeUpdate();

		if (result == -1 || result == 0) {
			return false;
		} else {
			return true;
		}
	}
}
