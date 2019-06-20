package com.kroger.repository;


import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.kroger.model.User;


public interface KrogerUserRepo extends CrudRepository<User ,Serializable>{

}
