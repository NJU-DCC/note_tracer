package com.nju.service;

import com.nju.model.DirModel;
import com.nju.model.NoteModel;

import java.util.List;

/**
 * Created by raychen on 2017/4/19.
 */
public interface NoteService {
    NoteModel getNote(Integer nid);
    List<NoteModel> search(String keyword);
    List<NoteModel> getNotesByDir(Integer did);
    DirModel getDir(Integer did);
    List<NoteModel> getNotesByUser(Integer uid);
    List<DirModel> getDirs(Integer uid);

    String transNote(String filename);

    int updateNote(NoteModel note);
    int deleteNote(Integer nid);
    int addNote(NoteModel note);
    int addDir(DirModel dir);
    int updateDir(DirModel dir);
    int deleteDir(Integer did);
}
