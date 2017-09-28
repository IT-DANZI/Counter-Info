package com.danzi.pac.dao;


/**
 * Created by lilingyong on 2017/8/12.
 */

import com.danzi.pac.domain.ProRecordMain;

public interface ProRecordMainMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProRecordMain record);

    int insertSelective(ProRecordMain record);

    ProRecordMain selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProRecordMain record);

    int updateByPrimaryKey(ProRecordMain record);
}