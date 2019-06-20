package com.kroger.repository;

import java.io.Serializable;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.kroger.model.User;

@Repository
public interface UserRepository extends CassandraRepository<User,Serializable> {
    //
}