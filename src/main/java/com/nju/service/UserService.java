package com.nju.service;

/**
 * Created by raychen on 2017/4/19.
 */
public interface UserService {
    int register(String username, String password, String email);
    int login(String username, String password);
}
