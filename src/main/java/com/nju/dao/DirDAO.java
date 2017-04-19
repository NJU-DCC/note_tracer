package com.nju.dao;

import com.nju.model.DirModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by raychen on 2017/4/19.
 */
public interface DirDAO extends JpaRepository<DirModel, Integer> {

    List<DirModel> findByUserId(Integer uid);

}
