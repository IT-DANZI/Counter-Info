package com.danzi.pac.dao;


/**
 * Created by lilingyong on 2017/8/12.
 */

import com.danzi.pac.domain.HrResource;

import java.util.List;

public interface HrResourceMapper {
    int deleteByPrimaryKey(String username);

    int insert(HrResource record);

    int insertSelective(HrResource record);

    HrResource selectByPrimaryKey(String username);

    int updateByPrimaryKeySelective(HrResource record);

    int updateByPrimaryKey(HrResource record);

    HrResource selectIsEmplyHrResource(HrResource record);

    List<HrResource> getAllHrResource();

}