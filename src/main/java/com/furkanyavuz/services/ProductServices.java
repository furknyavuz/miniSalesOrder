package com.furkanyavuz.services;

import java.util.List;

import com.furkanyavuz.model.Product;

public interface ProductServices {
	public boolean addProduct(Product product) throws Exception;

	public Product	 getProductByCode(String code) throws Exception;
	
	public List<Product> getProducts() throws Exception;

	public boolean deleteProduct(String code) throws Exception;
}
