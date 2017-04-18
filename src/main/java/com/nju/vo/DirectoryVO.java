package com.nju.vo;

import java.util.List;

/**
 * Created by disinuo on 17/4/18.
 */
public class DirectoryVO {
    int id;
    String name;
    List<NoteInfoVO> notes;

    public DirectoryVO(int id, String name, List<NoteInfoVO> notes) {
        this.id = id;
        this.name = name;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<NoteInfoVO> getNotes() {
        return notes;
    }
}
