package com.kroger.repository;

import java.io.Serializable;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import com.kroger.model.Prod_By_Cat;

public interface KrogerCatRepo extends CassandraRepository<Prod_By_Cat,Serializable> {
	
	@Query("select * from prod_by_cat where cat_name=:cname")
	public Prod_By_Cat findByName(String cname);
	
	@Query("select * from prod_by_cat where cat_name=:cname and cat_id=:cid")
	public Prod_By_Cat findProd_By_Cats(String cname,String cid);
	

}
