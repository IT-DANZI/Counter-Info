package com.danzi.pac.dao;


/**
 * Created by lilingyong on 2017/8/12.
 */

import com.danzi.pac.domain.SysMessage;

import java.util.List;

public interface SysMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysMessage record);

    int insertSelective(SysMessage record);

    SysMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysMessage record);

    int updateByPrimaryKey(SysMessage record);

    List<SysMessage> selectByMainKey(SysMessage sysMessage);
}