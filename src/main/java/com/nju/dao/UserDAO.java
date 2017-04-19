package com.nju.dao;

import com.nju.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by raychen on 2017/4/19.
 */
public interface UserDAO extends JpaRepository<UserModel, Integer> {
    UserModel findByUserName(String username);
}
