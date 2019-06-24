package com.kroger.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kroger.model.Prod_By_Cat;


public interface KrogerCatRepo extends CrudRepository<Prod_By_Cat,Serializable> {
	
	public List<Prod_By_Cat> findByCatname(String catname);
	
	public Prod_By_Cat findByCatnameAndCatid(String catname,String catid);
	

}
