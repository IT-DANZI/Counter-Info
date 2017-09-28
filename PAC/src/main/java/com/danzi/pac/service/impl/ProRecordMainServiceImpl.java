package com.danzi.pac.service.impl;

import com.danzi.pac.dao.ProRecordMainMapper;
import com.danzi.pac.domain.*;
import com.danzi.pac.service.AlterInfoMessageServiceI;
import com.danzi.pac.service.GroundInfoMessageServiceI;
import com.danzi.pac.service.ProRecordMainServiceI;
import com.danzi.pac.service.SysMessageServiceI;
import com.danzi.pac.utils.CommonUtils;
import com.danzi.pac.utils.Write;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Describe ：
 * <p>
 * Author   ：Lily
 * <p>
 * Date     ：2017/9/14.
 */
@Service
public class ProRecordMainServiceImpl extends RuntimeException implements ProRecordMainServiceI {
    @Autowired
    private SysMessageServiceI sysMessageServiceI;
    @Autowired
    private AlterInfoMessageServiceI alterInfoMessageServiceI;
    @Autowired
    private GroundInfoMessageServiceI groundInfoMessageServiceI;
    @Autowired
    private ProRecordMainMapper proRecordMainMapper;

    /**
     * 产品备案信息插入
     *
     * @param proRecordMain
     * @param request
     * @return
     */
    @Transactional
    @Override
    public boolean insertProRecord(ProRecordMain proRecordMain, HttpServletRequest request) {
        boolean flag;
        try {
            proRecordMain.setCreateTimeStr(CommonUtils.showTime());
            proRecordMain.setCreateTime(new Date());
            HrResource hrResource = (HrResource) request.getSession().getAttribute("user");
            proRecordMain.setOwerid(hrResource.getUsername());
            proRecordMain.setDelstatus(1);
            if (proRecordMainMapper.showProRecordMainByProcodeAndPackcode(proRecordMain) != null) {
                flag = false;
            }else {
                proRecordMainMapper.insertSelective(proRecordMain);
                if (proRecordMain.getSysMessageList().size() != 0) {
                    for (SysMessage sysMessage : proRecordMain.getSysMessageList()) {

                        Write.println("++++++++++++++" + proRecordMain.getSysMessageList());
                        sysMessage.setCreateTimeStr(CommonUtils.showTime());
                        sysMessage.setCreateTime(new Date());
                        sysMessage.setDelstatus(1);
                        sysMessage.setSysmessagecol1(proRecordMain.getProductcode());  //产品编码
                        sysMessage.setSysmessagecol2(proRecordMain.getPackagcoding()); //包材编码
                        Write.println("++++++++++++++" + sysMessage);
                        sysMessageServiceI.insertSysMessage(sysMessage);
                        Write.println("++++++++++++++" + proRecordMain.getSysMessageList());
                    }
                }
                if (proRecordMain.getGroundInfoMessageList().size() != 0) {
                    for (GroundInfoMessage groundInfoMessage : proRecordMain.getGroundInfoMessageList()) {
                        groundInfoMessage.setCreateTime(new Date());
                        groundInfoMessage.setCreateTimeStr(CommonUtils.showTime());
                        groundInfoMessage.setDelstatus(1);
                        groundInfoMessage.setSysmessagecol1(proRecordMain.getProductcode());//产品编码
                        groundInfoMessage.setSysmessagecol2(proRecordMain.getPackagcoding());//包材编码
                        groundInfoMessageServiceI.insertGroundInfoMessage(groundInfoMessage);
                    }
                }
                if (proRecordMain.getAlterInfoMessagesList().size() != 0) {
                    for (AlterInfoMessage alterInfoMessage : proRecordMain.getAlterInfoMessagesList()) {
                        alterInfoMessage.setCreateTime(new Date());
                        alterInfoMessage.setCreateTimeStr(CommonUtils.showTime());
                        alterInfoMessage.setSysmessagecol2(proRecordMain.getPackagcoding());//包材编码
                        alterInfoMessage.setDelstatus(1);
                        alterInfoMessage.setSysmessagecol1(proRecordMain.getProductcode());//产品编码
                        alterInfoMessageServiceI.insertAlterInfoMessage(alterInfoMessage);
                    }
                }
                Write.println(CommonUtils.showTime() + "插入" + proRecordMain.getProductcode() + ":" + proRecordMain.getPackagcoding() + "成功！");
                flag = true;
            }
        } catch (Exception e) {
            flag = false;
            Write.println("==============" + e);
        } finally {

        }

        return flag;
    }

    /**
     * 修改产品信息
     *
     * @param proRecordMain
     * @param request
     * @return
     */
    @Override
    public boolean updateProRecord(ProRecordMain proRecordMain, HttpServletRequest request) {
        boolean flag;
        try {
            proRecordMain.setUpdateTime(new Date());
            proRecordMain.setCreateTimeStr(CommonUtils.showTime());
            HrResource hrResource = (HrResource) request.getSession().getAttribute("user");
            proRecordMain.setUser(hrResource.getUsername());

            proRecordMainMapper.updateByPrimaryKeySelective(proRecordMain);
            for (AlterInfoMessage alterInfoMessage : proRecordMain.getAlterInfoMessagesList()) {
                Write.println(alterInfoMessage.getId()+":0000000000alterInfoMessage.getId()");
                if (alterInfoMessage.getId()!=null) {//判断该数据是否存在
                    alterInfoMessage.setUpdateTime(new Date());
                    alterInfoMessage.setUpdateTimeStr(CommonUtils.showTime());
                    alterInfoMessage.setUpdateuser(hrResource.getUsername());
                    alterInfoMessageServiceI.updateAlterInfoMessage(alterInfoMessage);
                } else {
                    Write.println("+++++++++++++++++++++++++++++1111");
                    alterInfoMessage.setCreateTime(new Date());
                    alterInfoMessage.setCreateTimeStr(CommonUtils.showTime());
                    alterInfoMessage.setSysmessagecol1(proRecordMain.getProductcode());//产品编码
                    alterInfoMessage.setSysmessagecol2(proRecordMain.getPackagcoding());//包材编码
                    alterInfoMessage.setDelstatus(1);
                    alterInfoMessageServiceI.insertAlterInfoMessage(alterInfoMessage);
                }
            }
            for (SysMessage sysMessage : proRecordMain.getSysMessageList()) {
                Write.println(sysMessage.getId()+":0000000000sysMessage.getId()");
                if (sysMessage.getId()!=null) {
                    sysMessage.setUpdateTime(new Date());
                    sysMessage.setUpdateuser(hrResource.getUsername());
                    sysMessage.setUpdateTimeStr(CommonUtils.showTime());
                    sysMessageServiceI.updateSysMessage(sysMessage);
                } else { Write.println("+++++++++++++++++++++++++++++2222");
                    sysMessage.setCreateTime(new Date());
                    sysMessage.setSysmessagecol1(proRecordMain.getProductcode());
                    sysMessage.setSysmessagecol2(proRecordMain.getPackagcoding());
                    sysMessage.setCreateTimeStr(CommonUtils.showTime());
                    sysMessage.setDelstatus(1);
                    sysMessageServiceI.insertSysMessage(sysMessage);

                }
            }
            for (GroundInfoMessage groundInfoMessage : proRecordMain.getGroundInfoMessageList()) {
                Write.println(groundInfoMessage.getId()+":0000000000groundInfoMessage.getId()");
                if (groundInfoMessage.getId()!=null) {
                    groundInfoMessage.setUpdateTime(new Date());
                    groundInfoMessage.setUpdateTimeStr(CommonUtils.showTime());
                    groundInfoMessage.setUpdateuser(hrResource.getUsername());
                    groundInfoMessageServiceI.updateGroundInfoMessage(groundInfoMessage);
                } else {
                    Write.println("+++++++++++++++++++++++++++++3333");
                    groundInfoMessage.setCreateTime(new Date());
                    groundInfoMessage.setCreateTimeStr(CommonUtils.showTime());
                    groundInfoMessage.setSysmessagecol2(proRecordMain.getPackagcoding());
                    groundInfoMessage.setSysmessagecol1(proRecordMain.getProductcode());
                    groundInfoMessage.setDelstatus(1);
                    groundInfoMessageServiceI.insertGroundInfoMessage(groundInfoMessage);

                }
            }
            flag = true;
            Write.println(CommonUtils.showTime() + "修改" + proRecordMain.getProductcode() + ":" + proRecordMain.getPackagcoding() + "成功！");
        } catch (Exception e) {
            flag = false;
            Write.println("ProRecordMainServiceImpl异常：");e.printStackTrace();
        } finally {

        }
        return flag;
    }

    /**
     * 删除备案产品信息
     *
     * @param proRecordMainlist
     * @param request
     * @return
     */
    @Override
    public int delProRecord(List<ProRecordMain> proRecordMainlist, HttpServletRequest request) {
        int n = -1;
        HrResource hrResource = (HrResource) request.getSession().getAttribute("user");
        for (ProRecordMain proRecordMain : proRecordMainlist) {
            proRecordMain.setProrecordcol3(hrResource.getHrresourcecol3());//删除备案信息的人
            proRecordMain.setProrecordcol4(CommonUtils.showTime());//删除备案信息的时间
            proRecordMain.setDelstatus(2);
            proRecordMainMapper.updateByPrimaryKeySelective(proRecordMain);
            ProRecordMain proRecordMainReturn = new ProRecordMain();
            proRecordMainReturn = this.checkProRecordMain(proRecordMain);
            SysMessage sysMessage = new SysMessage();
            AlterInfoMessage alterInfoMessage = new AlterInfoMessage();
            GroundInfoMessage groundInfoMessage = new GroundInfoMessage();
            sysMessage.setSysmessagecol1(proRecordMainReturn.getProductcode());
            sysMessage.setSysmessagecol2(proRecordMainReturn.getPackagcoding());
            sysMessage.setDelstatus(2);
            sysMessageServiceI.updateSysMessageByMainKey(sysMessage);
            alterInfoMessage.setSysmessagecol1(proRecordMainReturn.getProductcode());
            alterInfoMessage.setSysmessagecol2(proRecordMainReturn.getPackagcoding());
            alterInfoMessage.setDelstatus(2);
            alterInfoMessageServiceI.updateAlterInfoMessageByMainKey(alterInfoMessage);
            groundInfoMessage.setSysmessagecol1(proRecordMainReturn.getProductcode());
            groundInfoMessage.setSysmessagecol2(proRecordMainReturn.getPackagcoding());
            groundInfoMessage.setDelstatus(2);
            groundInfoMessageServiceI.updateGroundInfoMessageByMainKey(groundInfoMessage);
            n++;
        }
        return n;
    }

    /**
     * 显示所有备案产品信息
     *
     * @param hrResource
     * @param proRecordMain
     * @return
     */
    @Override
    public List<ProRecordMain> showAllProRecord(HrResource hrResource, ProRecordMain proRecordMain) {
        List<ProRecordMain> proRecordMainList = new ArrayList<ProRecordMain>();
        List<ProRecordMain> proRecordMainListReturn = new ArrayList<ProRecordMain>();
        proRecordMainList = proRecordMainMapper.showAllProRecordMain(proRecordMain);
        for (ProRecordMain proRecordMain1 : proRecordMainList) {
            AlterInfoMessage alterInfoMessage = new AlterInfoMessage();
            GroundInfoMessage groundInfoMessage = new GroundInfoMessage();
            SysMessage sysMessage = new SysMessage();
            alterInfoMessage.setSysmessagecol1(proRecordMain1.getProductcode());
            alterInfoMessage.setSysmessagecol2(proRecordMain1.getPackagcoding());
            groundInfoMessage.setSysmessagecol1(proRecordMain1.getProductcode());
            groundInfoMessage.setSysmessagecol2(proRecordMain1.getPackagcoding());
            sysMessage.setSysmessagecol1(proRecordMain1.getProductcode());
            sysMessage.setSysmessagecol2(proRecordMain1.getPackagcoding());
            proRecordMain1.setSysMessageList(sysMessageServiceI.showSysMessageByMainkey(sysMessage));
            proRecordMain1.setGroundInfoMessageList(groundInfoMessageServiceI.showGroundInfoMessageByMainkey(groundInfoMessage));
            proRecordMain1.setAlterInfoMessagesList(alterInfoMessageServiceI.showAlterInfoMessageByMainkey(alterInfoMessage));
            proRecordMainListReturn.add(proRecordMain1);
        }
        return proRecordMainListReturn;
    }

    @Override
    public int delSysMessageByMainKey(SysMessage sysMessage) {

        return sysMessageServiceI.delSysMessageByMainKey(sysMessage);
    }

    @Override
    public int delGroundInfoMessageByMainKey(GroundInfoMessage groundInfoMessage) {

        return groundInfoMessageServiceI.delGroundInfoMessageByMainKey(groundInfoMessage);
    }

    @Override
    public int delAlterInfoMessageByMainKey(AlterInfoMessage alterInfoMessage) {

        return alterInfoMessageServiceI.delAlterInfoMessageByMainKey(alterInfoMessage);
    }

    @Override
    public ProRecordMain checkProRecordMain(ProRecordMain proRecordMain) {
        return proRecordMainMapper.selectByPrimaryKey(proRecordMain.getId());
    }
}
