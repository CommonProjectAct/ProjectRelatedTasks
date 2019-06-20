package com.kroger.repository;

import java.util.List;

import com.kroger.model.User;


public interface UserDetailRepo {
 List<User> getCustomerDetails(String user_id);
}
