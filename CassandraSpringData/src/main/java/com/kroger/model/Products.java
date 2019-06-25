package com.kroger.model;

import java.io.Serializable;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@Table
public class Products implements Serializable{

private static final long serialVersionUID = 1L;
	
	
	@PrimaryKeyColumn(name="prod_name",ordinal = 0,type = PrimaryKeyType.PARTITIONED)
	@Column("prod_name")
	private String prodName;

	@PrimaryKeyColumn(name = "prod_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
	@Column("prod_id")
	private String prodId;

	@Column("prod_desc")
	private String prodDesc;
	
	@Column("prod_price")
	private int prodPrice;
}
