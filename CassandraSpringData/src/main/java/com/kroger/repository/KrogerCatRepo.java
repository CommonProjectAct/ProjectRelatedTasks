package com.kroger.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.kroger.model.ProdByCat;

@RepositoryRestResource
public interface KrogerCatRepo extends CrudRepository<ProdByCat,Serializable> {
	
	public List<ProdByCat> findByCatName(String catName);
	
	public ProdByCat findByCatNameAndCatId(String catName,String catId);
	
	public void deleteByCatName(String catName);
	
	public <S> S save(ProdByCat prodByCat);
	

}
