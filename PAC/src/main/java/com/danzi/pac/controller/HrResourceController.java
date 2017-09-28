package com.danzi.pac.controller;

import com.danzi.pac.domain.HrResource;
import com.danzi.pac.service.HrResourceServiceI;
import com.danzi.pac.utils.CommonUtils;
import com.danzi.pac.utils.JsonResult;
import com.danzi.pac.utils.URL;
import com.danzi.pac.utils.Write;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Describe ：
 * <p>
 * Author   ：Lily
 * <p>
 * Date     ：2017/8/13.
 */
@Controller
@RequestMapping("HrResourceController")
public class HrResourceController {
    @Autowired
    private HrResourceServiceI hrResourceServiceI;

    /**
     * 管理员登录
     *
     * @param hrResource
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "login")
    private
    @ResponseBody
    JsonResult login(HrResource hrResource, HttpServletRequest request, HttpSession session) {
        hrResource.setLoginip(CommonUtils.getIp(request));
        hrResource = hrResourceServiceI.getHrResource(hrResource);
        session.setAttribute("user", hrResource);
        session.setAttribute("username", hrResource.getUser());
        session.setAttribute("power", hrResource.getUsername());
        return new JsonResult(hrResource);
    }

    /**
     * 添加管理员信息
     *
     * @param hrResource
     * @return
     */
    @RequestMapping(value = "addManager")
    private
    @ResponseBody
    JsonResult addManager(HrResource hrResource, HttpServletRequest request) {
        JsonResult jsonResult;
        hrResource.setHrresourcecol2(request.getSession().getAttribute("username").toString());
        Write.println(request.getSession().getAttribute("username").toString()+"hhdfahdkfhkadfhhkfhka");
        try {
            if(request.getSession().getAttribute("power").toString().substring(0,5).equals("admin"))
            {
                jsonResult = new JsonResult(hrResourceServiceI.addHrResource(hrResource));
            }else{
                jsonResult = new JsonResult(-1);
            }
        }catch (Exception e){
            jsonResult = new JsonResult(-1);
        }

        return jsonResult;
    }

    /**
     * 显示所有用户信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "showAllManager")
    private String showAllManager(HttpServletRequest request) {
        HrResource hrResource = new HrResource();
        hrResource.setUsername(request.getSession().getAttribute("power").toString());
        List<HrResource> ltHrResource = new ArrayList<HrResource>();
        ltHrResource = hrResourceServiceI.showAllHrResource(hrResource);
        request.setAttribute("mltResource", ltHrResource);
        return URL.MANAGER_INFO;
    }

    /**
     * 修改用户信息
     *
     * @param hrResource
     * @return 1: 修改成功 -1:新旧密码一样 -2:权限不足
     */
    @RequestMapping(value = "updateManagerMessage")
    private
    @ResponseBody
    JsonResult updateManagerMessage(HrResource hrResource, HttpServletRequest request) {
        int n;
        String mess;
        HrResource mHrResource = (HrResource) request.getSession().getAttribute("user");
        if (mHrResource.getUsername().equals("admin") || hrResource.getUsername().equals(mHrResource.getUsername())) {
            if (!hrResource.getNewpassword().equals("")) {
                n = hrResourceServiceI.updateHrResource(hrResource, 1);
            } else {
                n = hrResourceServiceI.updateHrResource(hrResource, 0);
            }
        } else {
            n = -2;
        }
        return new JsonResult(n);
    }

    /**
     * 删除用户信息
     *
     * @param requset
     * @return 0：删除失败  >0：删除成功
     */
    @RequestMapping(value = "delManager")
    private
    @ResponseBody
    JsonResult delManager(HttpServletRequest requset) {
        HrResource hrResource = (HrResource) requset.getSession().getAttribute("user");
        if (hrResource.getUsername().equals("admin")) {
            String rqListHrRource = requset.getParameter("managerList");
            Write.println("-----------------" + rqListHrRource);
            return new JsonResult(hrResourceServiceI.delManager(rqListHrRource, requset));
        } else {
            return new JsonResult(-1);
        }
    }

    /**
     * 退出登录
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "outLogin")
    private @ResponseBody JsonResult outLogin(HttpServletRequest request,HttpSession session){
        HrResource hrResource = new HrResource();
        hrResource = (HrResource) request.getSession().getAttribute("user");
        hrResource.setOutTimeStr(CommonUtils.showTime());
        hrResource.setOutTime(new Date());
        int i = hrResourceServiceI.updateHrResource(hrResource,0);
        session.invalidate();
        return new JsonResult(i);
    }

}
