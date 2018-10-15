package com.java.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.java.dto.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	@Autowired SessionFactory factory;
	
	
	/* (non-Javadoc)
	 * @see com.java.dao.ProductRepo#getProducts()
	 */
	@Override
	public List<Product> getProducts(){
		Session sf= factory.openSession();
		List<Product> list= sf.createCriteria(Product.class).list();
		sf.close();
		return list;
	}
	
	
	/* (non-Javadoc)
	 * @see com.java.dao.ProductRepo#saveProduct(com.java.dto.Product)
	 */
	@Override
	public int saveProduct(Product product){
		Session s= factory.openSession();
		s.beginTransaction();
		int id=(int) s.save(product);
		s.getTransaction().commit();
		s.close();
		return id;
	}


	/* (non-Javadoc)
	 * @see com.java.dao.ProductRepo#deleteProduct(int)
	 */
	@Override
	public void deleteProduct(int id) {
		Session s= factory.openSession();
		s.beginTransaction();
		s.remove(s.load(Product.class, id));
		s.getTransaction().commit();
		s.close();
		
	}


	/* (non-Javadoc)
	 * @see com.java.dao.ProductRepo#updateProduct(com.java.dto.Product)
	 */
	@Override
	public void updateProduct(Product product) {
		Session s= factory.openSession();
		s.beginTransaction();
		 s.update(product);
		s.getTransaction().commit();
		s.close();
		
	}
}
