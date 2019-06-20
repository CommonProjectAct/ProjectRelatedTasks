package com.kroger.model;

import java.io.Serializable;
import java.util.Map;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;


@Table
public class Prod_By_Cat implements Serializable {

private static final long serialVersionUID = 1L;
	
	@PrimaryKeyColumn(name="cat_name",ordinal = 0,type = PrimaryKeyType.PARTITIONED)
	@Column("cat_name")
	private String cat_name;
	
	@PrimaryKeyColumn(name = "cat_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
	@Column("cat_id")
	private String cat_id;
	@Column("prod_list")
	private Map<String,String> prod_list;
	public String getCat_name() {
		return cat_name;
	}
	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}
	public String getCat_id() {
		return cat_id;
	}
	public void setCat_id(String cat_id) {
		this.cat_id = cat_id;
	}
	public Map<String, String> getProd_list() {
		return prod_list;
	}
	public void setProd_list(Map<String, String> prod_list) {
		this.prod_list = prod_list;
	}

	
}
