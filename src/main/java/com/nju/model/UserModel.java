package com.nju.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by raychen on 2017/4/19.
 */
@Entity
@Table(name = "tb_user")
public class UserModel {
    @Id
    @GeneratedValue
    private int Id;
    private String userName;
    private String userAvatar;
    private String password;
    private String name;
    private String email;
    private Date createAt;

    public void setId(int id) {
        Id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public int getId() {

        return Id;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Date getCreateAt() {
        return createAt;
    }
}
