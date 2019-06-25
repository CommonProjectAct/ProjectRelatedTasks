package com.kroger.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kroger.model.Products;


public interface KrogerProdRepo extends CrudRepository<Products, Serializable> {
	
	public List<Products> findByProdName(String prodName);
	
	@Query("select * from products where prod_desc=:prod_desc")
	public Products findByProdDesc(String prodDesc);
}
