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
import com.kroger.response.UserResponse;
import com.kroger.util.CassandraConfiguration;

@Repository
public class KrogerUserRepo  {
	
	@Autowired
	Environment environment;
	  
	@Autowired
	User user;
	
	@Autowired
	UserResponse userResponse;
	
	@Autowired
	CassandraConfiguration cassandraConfiguration;
	
	public UserResponse getUserDetails(String userId) {
		 
	try {
		 Cluster cluster =cassandraConfiguration.clusters();
		 Session session = cluster.connect("krogerks_ecom"); 
		 
		 RegularStatement toPrepare = (RegularStatement) new SimpleStatement("select * from user where userid=?").setConsistencyLevel(ConsistencyLevel.ONE);
		 PreparedStatement prepared = session.prepare(toPrepare);
		 ResultSet res=session.execute(prepared.bind(userId));
		
		 Row row=res.one();
		 user.setUserId(row.getString(0));
		 user.setFirstName(row.getString(1));
		 user.setLastName(row.getString(2));
		 user.setType(row.getString(3));
		 userResponse.setStatusCode(0);
		 userResponse.setStatusMsg("Fetch is Successful");
		 userResponse.setResponse(user);
		}catch(Exception e) {
			userResponse.setStatusCode(404);
			userResponse.setStatusMsg("Exception Occcured");
		}
		
		 return userResponse;
	 }
	
	public UserResponse deleteUserById(String userId) {
	try {
		Cluster cluster =cassandraConfiguration.clusters();
		Session session = cluster.connect("krogerks_ecom"); 
		 
		 RegularStatement toPrepare = (RegularStatement) new SimpleStatement("delete from user where userid=?").setConsistencyLevel(ConsistencyLevel.ONE);
		 PreparedStatement prepared = session.prepare(toPrepare);
		 ResultSet res =session.execute(prepared.bind(userId));
		 if(res.wasApplied()) {
			 userResponse.setStatusCode(0);
			 userResponse.setStatusMsg("Deletion is Successful");
			 userResponse.setResponse("Deleted userd id :"+userId);
		 }else {
			 userResponse.setStatusCode(404);
		 	 userResponse.setStatusMsg("Deleteion in Failed");
		 }
		}catch(Exception e) {
			userResponse.setStatusCode(404);
			userResponse.setStatusMsg("Exception Occcured");
		}
		return userResponse;

	 }
	
	public UserResponse insertUser(User user) {
	try {
		Cluster cluster =cassandraConfiguration.clusters();
		Session session = cluster.connect("krogerks_ecom"); 
		 
		 RegularStatement toPrepare = (RegularStatement) new SimpleStatement("insert into user(userid,first_name,last_name,type) values(?,?,?,?)").setConsistencyLevel(ConsistencyLevel.ONE);
		 PreparedStatement prepared = session.prepare(toPrepare);
		 ResultSet res =session.execute(prepared.bind(user.getUserId(),user.getFirstName(),user.getLastName(),user.getType()));
		 if(res.wasApplied()) {
			 userResponse.setStatusCode(0);
			 userResponse.setStatusMsg("Insertion is Successful");
		 }else {
			 userResponse.setStatusCode(404);
		 	 userResponse.setStatusMsg("Insertion Failed");
		 }
		}catch(Exception e) {
		userResponse.setStatusCode(404);
		userResponse.setStatusMsg("Exception Occcured");
		}
		return userResponse;
	 }
	
	public UserResponse updateUser(String userId,String type ) {
		 
		try {
		Cluster cluster =cassandraConfiguration.clusters(); 
		Session session = cluster.connect("krogerks_ecom"); 
		 
		 RegularStatement toPrepare = (RegularStatement) new SimpleStatement("update user set type=? where userid=?").setConsistencyLevel(ConsistencyLevel.ONE);
		 PreparedStatement prepared = session.prepare(toPrepare);
		 ResultSet res =session.execute(prepared.bind(type,userId));
		 if(res.wasApplied()) {
			 userResponse.setStatusCode(0);
			 userResponse.setStatusMsg("Update is Successful");
		 }else {
			 userResponse.setStatusCode(404);
		 	 userResponse.setStatusMsg("Update is UnSuccessful");
		 }
		 }catch(Exception e) {
			 userResponse.setStatusCode(404);
				userResponse.setStatusMsg("Exception Occcured");
		}
		return userResponse;
	 }
	
	 public User getCustomerDetails(String userId) { 
		
	try {
		Cluster cluster =cassandraConfiguration.clusters(); 
		Session session = cluster.connect(); 
		 
		 Statement statement=QueryBuilder.select("userid","first_name","last_name","type").from
		 ("krogerks_ecom", "user") .where(QueryBuilder.eq("user_id",userId)); 
		 
		 ResultSet rs = session.execute(statement); 
		 Row row = rs.one();
		 
		 user.setUserId(row.getString("userid"));
		 user.setFirstName(row.getString("first_name"));
		 user.setLastName(row.getString("last_name"));
		 user.setType(row.getString("type"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		 return user;
	  } 
	  
	


}
