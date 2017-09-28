package com.danzi.pac.dao;


/**
 * Created by lilingyong on 2017/8/12.
 *
 * 测试动态建表
 */

import com.danzi.pac.domain.HrResource;
import com.danzi.pac.domain.Utils;

public interface UtilsMapper {
    int deleteByPrimaryKey(String username);

    int insert(Utils record);

    int insertSelective(HrResource record);

    HrResource selectByPrimaryKey(String username);

    int updateByPrimaryKeySelective(HrResource record);

    int updateByPrimaryKey(HrResource record);

    HrResource selectIsEmplyHrResource(HrResource record);

}