package com.nju.service.impl;

import com.nju.dao.DirDAO;
import com.nju.dao.NoteDAO;
import com.nju.model.DirModel;
import com.nju.model.NoteModel;
import com.nju.service.NoteService;
import com.nju.util.ApiUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raychen on 2017/4/19.
 */
@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    NoteDAO noteDAO;
    @Autowired
    DirDAO dirDAO;

    @Override
    public NoteModel getNote(Integer nid) {
        return noteDAO.findOne(nid);
    }

    @Override
    public List<NoteModel> search(String keyword, Integer uid) {
        List<NoteModel> allNotes = getNotesByUser(uid);
        List<NoteModel> ret = new ArrayList<>();
        for (NoteModel note: allNotes) {
            if (note.getTitle().contains(keyword) || note.getContent().contains(keyword)){
                ret.add(note);
            }
        }
        return ret;
    }

    @Override
    public List<NoteModel> getNotesByDir(Integer did) {
        return noteDAO.findByDirId(did);
    }

    @Override
    public DirModel getDir(Integer did) {
        return dirDAO.findOne(did);
    }

    @Override
    public List<NoteModel> getNotesByUser(Integer uid) {
        return noteDAO.findByUserId(uid);
    }

    @Override
    public List<DirModel> getDirs(Integer uid) {
        return dirDAO.findByUserId(uid);
    }

    @Override
    public String transNote(String filename) {
        String response = ApiUtil.postFile(filename, "chs");
        JSONObject res = new JSONObject(response);
        return ApiUtil.parseText(res);
    }

    @Override
    public int updateNote(NoteModel note) {
        noteDAO.saveAndFlush(note);
        return 1;
    }

    @Override
    public int deleteNote(Integer nid) {
        noteDAO.delete(nid);
        return 1;
    }

    @Override
    public int addNote(NoteModel note) {
        noteDAO.saveAndFlush(note);
        return 1;
    }

    @Override
    public int addDir(DirModel dir) {
        dirDAO.saveAndFlush(dir);
        return 1;
    }

    @Override
    public int updateDir(DirModel dir) {
        dirDAO.saveAndFlush(dir);
        return 1;
    }

    @Override
    public int deleteDir(Integer did) {
        dirDAO.delete(did);
        return 1;
    }
}
