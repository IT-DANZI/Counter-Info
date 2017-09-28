package com.danzi.pac.service;

import com.danzi.pac.domain.HrResource;
import com.danzi.pac.domain.Utils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Describe ：
 * <p>
 * Author   ：Lily
 * <p>
 * Date     ：2017/8/12.
 */
public interface HrResourceServiceI {
    boolean addHrResource(HrResource hrResource);

    HrResource getHrResource(HrResource hrResource);

    int updateHrResource(HrResource hrResource,int flag);

    HrResource showDetailHrResource(HrResource hrResource);

    List<HrResource> showAllHrResource(HrResource hrResource);
    //boolean insert(Utils utils);
    int delManager(String ltOBJStr, HttpServletRequest request);
}
