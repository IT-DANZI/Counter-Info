package com.danzi.pac.dao;

/**
 * Created by lilingyong on 2017/8/12.
 */

import com.danzi.pac.domain.AlterInfoMessage;

import java.util.List;

public interface AlterInfoMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AlterInfoMessage record);

    int insertSelective(AlterInfoMessage record);

    AlterInfoMessage selectByPrimaryKey(Integer id);

    List<AlterInfoMessage> selectByMainKey(AlterInfoMessage alterInfoMessage);

    int updateByPrimaryKeySelective(AlterInfoMessage record);

    int updateByPrimaryKey(AlterInfoMessage record);
}