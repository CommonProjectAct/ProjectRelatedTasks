package com.kroger.model;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;

@Data
@Table(value="prod_by_cat")
public class ProdByCat implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id
	@Column("catname")
	private String catName;
	
	@Column("catid")
	private String catId;
	
	@Column("prod_list")
	private Map<String,String> prodItems;

}
