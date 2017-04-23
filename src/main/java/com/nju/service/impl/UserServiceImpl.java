package com.nju.service.impl;

import com.nju.dao.UserDAO;
import com.nju.model.UserModel;
import com.nju.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;

/**
 * Created by raychen on 2017/4/19.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    @Override
    public int register(String username, String password, String email) {
        UserModel oldUser = userDAO.findByUserName(username);
        if (oldUser != null) return 0;
        UserModel user = new UserModel();
        user.setUserName(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setCreateAt(new Date(Calendar.getInstance().getTimeInMillis()));
        userDAO.saveAndFlush(user);
        return 1;
    }

    @Override
    public int login(String username, String password) {
        UserModel user = userDAO.findByUserName(username);
        if (user == null) return -1;
        if (!user.getPassword().equals(password)) return 0;
        return user.getId();
    }
}
