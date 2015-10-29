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

import com.furkanyavuz.model.Product;
import com.furkanyavuz.services.ProductServices;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductServices productServices;

	static final Logger logger = Logger.getLogger(ProductController.class);

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean addProduct(@RequestBody Product product) {
		try {
			productServices.addProduct(product);
			return true;
		} catch (Exception e) {
			logger.error(e.getStackTrace().toString());
			return false;
		}
	}

	@RequestMapping(value = "/{code}", method = RequestMethod.GET)
	public @ResponseBody Product getProduct(@PathVariable("code") String code) {
		Product product = null;
		try {
			product = productServices.getProductByCode(code);

		} catch (Exception e) {
			logger.error(e.getStackTrace().toString());
		}
		return product;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List<Product> getProducts() {

		List<Product> products = null;
		try {
			products = productServices.getProducts();

		} catch (Exception e) {
			logger.error(e.getStackTrace().toString());
		}

		return products;
	}

	@RequestMapping(value = "delete/{code}", method = RequestMethod.GET)
	public @ResponseBody boolean deleteProduct(@PathVariable("code") String code) {

		try {
			return productServices.deleteProduct(code);
		} catch (Exception e) {
			logger.error(e.getStackTrace().toString());
			return false;
		}
	}
}
