package com.java.dao;

import java.util.List;

import com.java.dto.Product;

public interface ProductRepository {

	List<Product> getProducts();

	int saveProduct(Product product);

	void deleteProduct(int id);

	void updateProduct(Product product);

}