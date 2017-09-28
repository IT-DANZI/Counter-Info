package com.danzi.pac.dao;


/**
 * Created by lilingyong on 2017/8/12.
 */

import com.danzi.pac.domain.GroundInfoMessage;

import java.util.List;

public interface GroundInfoMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GroundInfoMessage record);

    int insertSelective(GroundInfoMessage record);

    GroundInfoMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroundInfoMessage record);

    int updateByPrimaryKey(GroundInfoMessage record);

    List<GroundInfoMessage> selectByMainKey(GroundInfoMessage groundInfoMessage);
}