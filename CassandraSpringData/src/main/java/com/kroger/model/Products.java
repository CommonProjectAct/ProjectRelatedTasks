package com.kroger.model;

import java.io.Serializable;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Indexed;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.stereotype.Component;
@Component
@Table
public class Products implements Serializable{

private static final long serialVersionUID = 1L;
	
	
	@PrimaryKeyColumn(name="prod_name",ordinal = 0,type = PrimaryKeyType.PARTITIONED)
	@Column("prod_name")
	private String prodname;

	@PrimaryKeyColumn(name = "prod_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
	@Column("prod_id")
	private String prod_id;

	@Column("prod_desc")
	private String prod_desc;

	public String getProdname() {
		return prodname;
	}

	public void setProdname(String prodname) {
		this.prodname = prodname;
	}

	public String getProd_id() {
		return prod_id;
	}

	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}

	public String getProd_desc() {
		return prod_desc;
	}

	public void setProd_desc(String prod_desc) {
		this.prod_desc = prod_desc;
	}

	public int getProd_price() {
		return prod_price;
	}

	public void setProd_price(int prod_price) {
		this.prod_price = prod_price;
	}

	@Indexed("prod_price")
	private int prod_price;

}
