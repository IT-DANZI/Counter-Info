package com.danzi.pac.service.impl;

import com.danzi.pac.dao.GroundInfoMessageMapper;
import com.danzi.pac.dao.SysMessageMapper;
import com.danzi.pac.domain.GroundInfoMessage;
import com.danzi.pac.domain.SysMessage;
import com.danzi.pac.service.SysMessageServiceI;
import com.danzi.pac.utils.CommonUtils;
import com.danzi.pac.utils.Mess;
import com.danzi.pac.utils.Write;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Describe ：系统备案信息实现类
 * <p>
 * Author   ：Lily
 * <p>
 * Date     ：2017/8/24.
 */
@Service
public class SysMessageServiceImpl implements SysMessageServiceI {
    @Autowired
    private SysMessageMapper sysMessageMapper;
    private int n;
    private boolean flag;
    private int j = 0;

    /**
     * 查询系统备案信息详情信息
     * @param id
     * @return
     */
    @Override
    public SysMessage showSysMessage(Integer id) {
        return sysMessageMapper.selectByPrimaryKey(id);
    }

    /**
     * 按主表ID查询所有系统备案信息
     * @return
     */
    @Override
    public List<SysMessage> showSysMessageByMainkey(SysMessage sysMessage) {
        return sysMessageMapper.selectByMainKey(sysMessage);
    }

    /**
     * 修改系统备案信息
     * @param sysMessage
     * @return
     */
    @Override
    public int updateSysMessage(SysMessage sysMessage) {
        sysMessage.setUpdateTime(new Date());
        return sysMessageMapper.updateByPrimaryKeySelective(sysMessage);
    }

    /**
     * 插入系统备案信息
     * @param sysMessage
     * @return
     */
    @Override
    public boolean insertSysMessage(SysMessage sysMessage) {
        try {
            Write.println(sysMessage.toString());
            sysMessage.setCreateTime(new Date());
            n = sysMessageMapper.insertSelective(sysMessage);
            Write.println(sysMessage.toString());
        } catch (Exception e) {
            n = -1;
            Write.println(Mess.MES_ERROR);
        } finally {
            flag = false;
            if (n == 1) {
                flag = true;
                Write.println(Mess.MES_SUCCCESS);
            } else {
                flag = false;
                Write.println(Mess.MES_ERROR);
            }
            return flag;
        }
    }

    /**
     * 按主表ID插入所有系统备案信息
     * @param sysMessageList
     * @return
     */
    @Override
    public boolean insertSysMessageByMainKey(List<SysMessage> sysMessageList) {
        n = sysMessageList.size();
        try {
            if (n > 0) {
                for (SysMessage sysMessage : sysMessageList) {
                    j = j + 1;
                    sysMessage.setCreateTime(new Date());
                    sysMessageMapper.insertSelective(sysMessage);
                    Write.println(CommonUtils.showTime() + sysMessage.toString());
                }
            } else {
            }
        } catch (Exception e) {
            flag = false;
        } finally {
            if (n == j) {
                flag = true;
                Write.println(Mess.MES_SUCCCESS);
            } else {
                flag = false;
                Write.println(Mess.MES_ERROR);
            }
            return flag;
        }
    }

    @Override
    public int updateSysMessageByMainKey(SysMessage sysMessageList) {
        return sysMessageMapper.updateByPrimaryKeySelective(sysMessageList);
    }

    @Override
    public int delSysMessageByMainKey(SysMessage sysMessage) {
        return sysMessageMapper.deleteByPrimaryKey(sysMessage.getId());
    }

    /**
     * 按主表修改系统备案信息
     * @param sysMessageList
     * @return
     */
    public int updateSysMessageByMainKey(List<SysMessage> sysMessageList) {
        try {
            for (SysMessage sysMessage : sysMessageList) {
                sysMessage.setUpdateTime(new Date());
                sysMessageMapper.updateByPrimaryKeySelective(sysMessage);
                Write.println(CommonUtils.showTime() + sysMessage.toString());
                j = j + 1;
            }
        } catch (Exception e) {
            j = -1;
            Write.println(Mess.MES_ERROR);
        } finally {
            return j;
        }
    }
}
