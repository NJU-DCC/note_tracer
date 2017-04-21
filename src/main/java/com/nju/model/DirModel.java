package com.nju.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by raychen on 2017/4/19.
 */
@Entity
@Table(name = "tb_dir")
public class DirModel {
    @Id
    @GeneratedValue
    private int id;
    private int userId;
    private String title;
    private String description;
    private Date createAt;

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public int getId() {

        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreateAt() {
        return createAt;
    }
}
