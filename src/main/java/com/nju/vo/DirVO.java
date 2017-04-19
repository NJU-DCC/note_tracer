package com.nju.vo;

import com.nju.model.DirModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by disinuo on 17/4/18.
 */
public class DirVO {
    int id;
    int userId;
    String name;
    String description;
    Date createTime;
    List<NoteInfoVO> notes;


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

    public int getId() {
        return id;
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
    }
}
