package com.kroger.repository;


import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.kroger.model.User;
@RepositoryRestResource(collectionResourceRel = "Customers", path = "Customers")
public interface KrogerUserRepo extends CrudRepository<User ,String>{
	
	
	@Query("update user set type=:type where user_id=:user_id")
	public User updateUserTypeById(String type,String user_id);
	
	

}
