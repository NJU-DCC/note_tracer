package com.nju.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by raychen on 2017/4/19.
 */
@Entity
@Table(name = "tb_note")
public class NoteModel {
    @Id
    @GeneratedValue
    private int id;
    private int userId;
    private int dirId;
    private String title;
    private String content;
    private String pic;
    private Date createAt;
    private Date updateAt;

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setDirId(int dirId) {
        this.dirId = dirId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public int getId() {

        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getDirId() {
        return dirId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }
}
