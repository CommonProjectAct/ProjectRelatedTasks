package com.kroger.repository;


import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Consistency;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.datastax.driver.core.ConsistencyLevel;
import com.kroger.model.User;

@RepositoryRestResource
@EnableCassandraRepositories
public interface KrogerUserRepo extends CassandraRepository<User ,String>{
	
	@Consistency(ConsistencyLevel.ONE)
	@Query("update user set type=:type where userid=:userId")
	public User updateUserTypeById(String type,String userId);
	
	public List<User> findUserByType(String type);
	
	

}
