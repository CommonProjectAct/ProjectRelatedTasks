package com.kroger.model;

import java.io.Serializable;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@Table
public class User implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	@Column("userid")
	private String userId;
	
	@Column("first_name")
	private String firstName;
	
	@Column("last_name")
	private String lastName;
	
	@Column("type")
	private String type;
	
}
