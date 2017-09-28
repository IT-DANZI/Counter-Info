package com.danzi.pac.dao;

/**
 * Created by lilingyong on 2017/8/12.
 */

import com.danzi.pac.domain.AlterInfoMessage;

public interface AlterInfoMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AlterInfoMessage record);

    int insertSelective(AlterInfoMessage record);

    AlterInfoMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AlterInfoMessage record);

    int updateByPrimaryKey(AlterInfoMessage record);
}