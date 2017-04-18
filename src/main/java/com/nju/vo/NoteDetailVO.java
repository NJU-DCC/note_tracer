package com.nju.vo;

import java.util.Date;

/**
 * Created by disinuo on 17/4/18.
 */
public class NoteDetailVO {
    private int id;
    private String name;
    private Date createTime;
    private Date updateTime;
    private int dirId;
    private String dirName;
    private String content;

    public NoteDetailVO(int id, String name, Date createTime, Date updateTime, int dirId, String dirName, String content) {
        this.id = id;
        this.name = name;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.dirId = dirId;
        this.dirName = dirName;
        this.content = content;
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

    public String getContent() {
        return content;
    }
}
