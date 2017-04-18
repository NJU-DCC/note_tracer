package com.nju.dao;

import com.nju.model.DirModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by raychen on 2017/4/19.
 */
public interface DirDAO extends JpaRepository<DirModel, Integer> {
}
