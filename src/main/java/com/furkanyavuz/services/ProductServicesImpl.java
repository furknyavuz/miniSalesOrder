package com.furkanyavuz.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.furkanyavuz.dao.ProductDao;
import com.furkanyavuz.model.Product;

public class ProductServicesImpl implements ProductServices {

	@Autowired
	ProductDao productDao;

	@Override
	public boolean addProduct(Product product) throws Exception {
		return productDao.addProduct(product);
	}

	@Override
	public Product getProductByCode(String code) throws Exception {
		return productDao.getProductByCode(code);
	}

	@Override
	public List<Product> getProducts() throws Exception {
		return productDao.getProducts();
	}

	@Override
	public boolean deleteProduct(String code) throws Exception {
		return productDao.deleteProduct(code);
	}

}
