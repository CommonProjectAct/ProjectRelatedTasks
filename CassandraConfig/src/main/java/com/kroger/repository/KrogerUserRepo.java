package com.kroger.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.RegularStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SimpleStatement;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.kroger.model.User;

@Repository
public class KrogerUserRepo  {
	
	@Autowired
	Environment environment;
	  
	@Autowired
	User user;
	
	public User getUserDetails(String user_id) {
		 
		 Cluster cluster =Cluster.builder().withoutJMXReporting().addContactPoints(environment.getProperty("cassandra.contactpoints")).build();
		 Session session = cluster.connect("krogerks_ecom"); 
		 
		 RegularStatement toPrepare = (RegularStatement) new SimpleStatement("select * from user where user_id=?").setConsistencyLevel(ConsistencyLevel.QUORUM);
		 PreparedStatement prepared = session.prepare(toPrepare);
		 ResultSet res=session.execute(prepared.bind(user_id));
		
		 Row row=res.one();
		 user.setUser_id(row.getString(0));
		 user.setFirst_name(row.getString(1));
		 user.setLast_name(row.getString(2));
		 user.setType(row.getString(3));
		 
		 return user;
	 }
	
	public void deleteUserById(String user_id) {
		 
		 Cluster cluster =Cluster.builder().withoutJMXReporting().addContactPoints(environment.getProperty("cassandra.contactpoints")).build();
		 Session session = cluster.connect("krogerks_ecom"); 
		 
		 RegularStatement toPrepare = (RegularStatement) new SimpleStatement("delete from user where user_id=?").setConsistencyLevel(ConsistencyLevel.QUORUM);
		 PreparedStatement prepared = session.prepare(toPrepare);
		 session.execute(prepared.bind(user_id));
		 

	 }
	
	public void insertUser(User user) {
		 
		 Cluster cluster =Cluster.builder().withoutJMXReporting().addContactPoints(environment.getProperty("cassandra.contactpoints")).build();
		 Session session = cluster.connect("krogerks_ecom"); 
		 
		 RegularStatement toPrepare = (RegularStatement) new SimpleStatement("insert into user(user_id,first_name,last_name,type) values(?,?,?,?)").setConsistencyLevel(ConsistencyLevel.QUORUM);
		 PreparedStatement prepared = session.prepare(toPrepare);
		 session.execute(prepared.bind(user.getUser_id(),user.getFirst_name(),user.getLast_name(),user.getType()));

	 }
	
	public void updateUser(String userid,String type ) {
		 
		 Cluster cluster =Cluster.builder().withoutJMXReporting().addContactPoints(environment.getProperty("cassandra.contactpoints")).build();
		 Session session = cluster.connect("krogerks_ecom"); 
		 
		 RegularStatement toPrepare = (RegularStatement) new SimpleStatement("update user set type=? where user_id=?").setConsistencyLevel(ConsistencyLevel.QUORUM);
		 PreparedStatement prepared = session.prepare(toPrepare);
		 session.execute(prepared.bind(type,userid));

	 }
	
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
	  
	


}
