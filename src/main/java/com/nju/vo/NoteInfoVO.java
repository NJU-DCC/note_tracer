package com.nju.vo;

import com.nju.model.DirModel;
import com.nju.model.NoteModel;

import java.util.Date;

/**
 * Created by disinuo on 17/4/18.
 */
public class NoteInfoVO {
    private int id;
    private int userId;
    private String name;
    private Date createTime;
    private Date updateTime;
    private int dirId;
    private String dirName;

    public NoteInfoVO(int id, int userId,String name, Date createTime, Date updateTime, int dirId, String dirName) {
        this.id = id;
        this.userId=userId;
        this.name = name;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.dirId = dirId;
        this.dirName = dirName;
    }
    public NoteInfoVO(NoteModel noteModel, DirModel dirModel){
        this.id=noteModel.getId();
        this.userId=noteModel.getUserId();
        this.name=noteModel.getTitle();
        this.createTime=noteModel.getCreateAt();
        this.updateTime=noteModel.getUpdateAt();
        this.dirId=noteModel.getDirId();
        this.dirName=dirModel.getTitle();
    }
    public int getUserId() {
        return userId;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public int getDirId() {
        return dirId;
    }

    public String getDirName() {
        return dirName;
    }
}
