package com.kroger.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.kroger.model.User;

@Repository
public class UserDetailRepoImpl  {
	
	@Autowired
	Environment environment;
	  
	@Autowired
	User user;
	 
	//public Cluster cluster =Cluster.builder().withoutJMXReporting().addContactPoints(environment.getProperty("cassandra.contactpoints")).build();
	//public Session session = cluster.connect(); 
	
	 public User getCustomerDetails(String user_id) { 
		 
		Cluster cluster =Cluster.builder().withoutJMXReporting().addContactPoints(environment.getProperty("cassandra.contactpoints")).build();
		Session session = cluster.connect(); 
		 
		 Statement statement=QueryBuilder.select("user_id","first_name","last_name","type").from
		 ("krogerks_ecom", "user") .where(QueryBuilder.eq("user_id",user_id)); 
		 
		 ResultSet rs = session.execute(statement); 
		 Row row = rs.one();
		 
		 user.setUser_id(row.getString("user_id"));
		 user.setFirst_name(row.getString("first_name"));
		 user.setLast_name(row.getString("last_name"));
		 user.setType(row.getString("type"));
		 
		 return user;
	  } 
	  
	 public User getUserDetails() {
		 
		 Cluster cluster =Cluster.builder().withoutJMXReporting().addContactPoints(environment.getProperty("cassandra.contactpoints")).build();
		 Session session = cluster.connect(); 
		 
		 ResultSet res=session.execute("select first_name,last_name from krogerks_ecom.user where user_id='U2'");
		 Row row = res.one();
		 
		 user.setFirst_name(row.getString("first_name"));
		 user.setLast_name(row.getString("last_name"));
		 
		 return user;
	 }


}
