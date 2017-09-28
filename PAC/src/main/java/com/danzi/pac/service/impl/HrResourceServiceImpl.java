package com.danzi.pac.service.impl;

import com.danzi.pac.dao.HrResourceMapper;
import com.danzi.pac.dao.UtilsMapper;
import com.danzi.pac.domain.HrResource;
import com.danzi.pac.domain.Utils;
import com.danzi.pac.service.HrResourceServiceI;
import com.danzi.pac.utils.CommonUtils;
import com.danzi.pac.utils.Mess;
import com.danzi.pac.utils.Write;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Describe ：用户信息处理逻辑层
 * <p>
 * Author   ：Lily
 * <p>
 * Date     ：2017/8/12.
 */

@Service("hrResourceBean")
public class HrResourceServiceImpl implements HrResourceServiceI {

    @Autowired
    private HrResourceMapper hrResourceMapper;
    @Autowired
    private UtilsMapper utilsMapper;
    private int n;
    private int j;

    public HrResource getHrResource() {
        return mhrResource;
    }

    public void setHrResource(HrResource hrResource) {
        this.mhrResource = hrResource;
    }

    private HrResource mhrResource;

    /**
     * 添加用户信息
     * @param hrResource
     * @return 添加成功返回true 失败返回false
     */
    @Override
    public boolean addHrResource(HrResource hrResource) {
        try {
            hrResource.setNewpassword(CommonUtils.getEncode("MD5",hrResource.getNewpassword()));
            hrResource.setCreateTime(new Date());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String rightDate = sdf.format(new Date());
            hrResource.setCreateTimeStr(rightDate);
            hrResourceMapper.selectByPrimaryKey(hrResource.getUsername());
            if (hrResourceMapper.selectByPrimaryKey(hrResource.getUsername())==null){
                n = hrResourceMapper.insertSelective(hrResource);
            }else{
                n=-1;
            }
        }catch (Exception e){
            n=-1;
        }finally {
            if (n==1){
                Write.println(Mess.MES_SUCCCESS+"添加用户成功！"+hrResource.toString());
                return true;
            }else {
                Write.println(Mess.MES_ERROR_ADD);
                return false;
            }
        }
    }

    /**
     * 用户登录判断
     * @param hrResource 传入账号  和  密码  并登录IP
     * @return hrResourcereturn  返回用户信息
     */
    @Override
    public HrResource getHrResource(HrResource hrResource) {
        HrResource hrResourcereturn = new HrResource();
        hrResource.setNewpassword(CommonUtils.getEncode("MD5",hrResource.getNewpassword()));
        Write.println(hrResource.toString());
        try {
            hrResourcereturn =hrResourceMapper.selectIsEmplyHrResource(hrResource);

        }catch (NullPointerException e){
            Write.println(e.toString());
            hrResourcereturn = null;
        }

        if (hrResourcereturn==null){
            Write.println(CommonUtils.showTime()+"账号密码错误！");
            hrResourcereturn = new HrResource();
            hrResourcereturn.setMsg("账号或密码错误");
        }else {
            hrResource.setLoginTime(new Date());
            hrResource.setNewpassword(null); //防止修改密码  修改也可以
            this.updateHrResource(hrResource,2);
            hrResourcereturn.setMsg("登录成功");
            Write.println(CommonUtils.showTime()+hrResource.getUser()+"登录成功！");
        }
        return hrResourcereturn;
    }

    /**
     * 修改用户信息
     * @param hrResource 用户信息  flag:1为修改密码，其他为修改账号信息
     * @return -1旧密码与新密相同 1修改成功 0修改失败
     */
    @Override
    public int updateHrResource(HrResource hrResource,int flag) {
        Write.println("###########"+hrResource.getNewpassword()+"****"+flag);
        this.mhrResource = hrResource;
        if (flag==1){
            hrResource.setNewpassword(CommonUtils.getEncode("MD5",hrResource.getNewpassword()));
            HrResource hrResource1 = new HrResource();
            hrResource1 = hrResourceMapper.selectByPrimaryKey(hrResource.getUsername());

            if (hrResource1.getNewpassword().equals(hrResource.getNewpassword()) ){
                Write.println(CommonUtils.showTime()+"新密码与旧密码相同！");
                n = -1;
            }else {
                this.ifN();
            }
        }else {
            mhrResource.setNewpassword(null);
            HrResourceServiceImpl.this.ifN();
        }
        return n;
    }

    /**
     * 查询用户的详细信息
     * @param hrResource 传入用户对象，对象存有账号
     * @return HrResource 返回一个用户对象
     */
    @Override
    public HrResource showDetailHrResource(HrResource hrResource) {

        return hrResourceMapper.selectByPrimaryKey(hrResource.getUsername());
    }

    /**
     * 查询所有用户信息  状态UserStatus为1
     * @param hrResource 传入用户对象，（待做处理）只对admin赋予最高权限  admin为前缀的用户拥有权限与admin相同
     * @return List<HrResource> 返回所有用户信息
     */
    @Override
    public List<HrResource> showAllHrResource(HrResource hrResource) {
        Write.println(hrResource.getUsername());
        if (hrResource.getUsername().substring(0,5).equals("admin")){
            return hrResourceMapper.getAllHrResource();
        }else {
            return new ArrayList<HrResource>();
        }
    }

    /**
     * 将用户状态修改为2 冻结状态
     * @param ltOBJStr
     * @return
     */
    @Override
    public int delManager(String ltOBJStr, HttpServletRequest request){
        JSONArray jsonArray = JSONArray.fromObject(ltOBJStr);
        HrResource hrResource1 = (HrResource) request.getSession().getAttribute("user");
        int j=0;
        for(int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            HrResource hrResource = new HrResource();
            hrResource.setUsername(jsonObject.getString("username"));
            hrResource.setUserstatus(2);
            if (hrResource1.getUsername().equals(jsonObject.getString("username"))){
                continue;
            }
            this.updateHrResource(hrResource,0);
            j++;
        }
        return j;
    }

    /**
     * 修改用户信息
     * 重复代码部分处理
     */
    private void ifN(){
        j = hrResourceMapper.updateByPrimaryKeySelective(mhrResource);
        if (j == 1) {
            Write.println(CommonUtils.showTime() + "修改成功！" + mhrResource);
            n = 1;
        } else {
            n = 0;
            Write.println(CommonUtils.showTime()+"修改失败!");
        }
    }


}
