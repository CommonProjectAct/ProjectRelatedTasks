package com.kroger.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kroger.model.Products;

public interface KrogerProdRepo extends JpaRepository<Products, Serializable> {
	
	@Query("select * from products where prod_name=:pname")
	public List<Products> searchByName(String pname);
	
	@Query("select * from products where prod_desc=:prod_desc")
	public List<Products> findByProd_desc(String prod_desc);
}
