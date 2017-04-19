package com.nju.dao;

import com.nju.model.NoteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by raychen on 2017/4/19.
 */
public interface NoteDAO extends JpaRepository<NoteModel, Integer> {

    List<NoteModel> findByDirId(Integer did);
    List<NoteModel> findByUserId(Integer uid);

}
