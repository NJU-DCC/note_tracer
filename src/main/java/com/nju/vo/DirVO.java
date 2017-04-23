package com.nju.vo;

import com.nju.model.DirModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by disinuo on 17/4/18.
 */
public class DirVO {
    private int id;
    private int userId;
    private String name;
    private String description;
    private Date createTime;
    private List<NoteInfoVO> notes;
    private int numOfNotes;

    public DirVO(){

    }

    public DirVO(DirModel dirModel){
        this.id=dirModel.getId();
        this.name=dirModel.getTitle();
        this.userId=dirModel.getUserId();
        this.description=dirModel.getDescription();
        this.createTime=dirModel.getCreateAt();
    }
    public static List<DirVO> entityToVO(List<DirModel> models){
        List<DirVO> vos=new ArrayList<DirVO>(models.size());
        for(DirModel model:models){
            vos.add(new DirVO(model));
        }
        return vos;
    }
    public static DirModel voToEntity(DirVO vo){
        DirModel dirModel=new DirModel();
        dirModel.setCreateAt(vo.getCreateTime());
        dirModel.setTitle(vo.getName());
        dirModel.setUserId(vo.getUserId());
        dirModel.setId(vo.getId());
        dirModel.setDescription(vo.getDescription());
        return dirModel;
    }

    public int getId() {
        return id;
    }
    public int getNumOfNotes() {
        return numOfNotes;
    }

    public int getUserId() {
        return userId;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getName() {
        return name;
    }

    public List<NoteInfoVO> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteInfoVO> notes) {
        this.notes = notes;
        this.numOfNotes=notes.size();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setNumOfNotes(int numOfNotes) {
        this.numOfNotes = numOfNotes;
    }
}
