package com.nju.dao;

import com.nju.model.NoteModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by raychen on 2017/4/19.
 */
public interface NoteDAO extends JpaRepository<NoteModel, Integer> {

}
