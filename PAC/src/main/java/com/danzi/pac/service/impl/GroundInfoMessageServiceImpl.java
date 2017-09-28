package com.danzi.pac.service.impl;

import com.danzi.pac.dao.GroundInfoMessageMapper;
import com.danzi.pac.domain.AlterInfoMessage;
import com.danzi.pac.domain.GroundInfoMessage;
import com.danzi.pac.service.GroundInfoMessageServiceI;
import com.danzi.pac.utils.CommonUtils;
import com.danzi.pac.utils.Mess;
import com.danzi.pac.utils.Write;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

/**
 * Describe ：备案地面审查信息（3个月内送现场审查）实现类
 * <p>
 * Author   ：Lily
 * <p>
 * Date     ：2017/8/24.
 */
@Service
public class GroundInfoMessageServiceImpl implements GroundInfoMessageServiceI {

    @Autowired
    private GroundInfoMessageMapper groundInfoMessageMapper;
    private static AlterInfoMessageServiceImpl alterInfoMessageServiceImpl;
    private int n;
    private boolean flag;
    private int j = 0;

    /**
     * 查询备案地面审查信息详细信息
     *
     * @return id对应的一条详细信息
     */
    @Override
    public GroundInfoMessage showGroundInfoMessage(Integer id) {
        return groundInfoMessageMapper.selectByPrimaryKey(id);
    }

    /**
     * 按主表ID查询备案地面审查信息
     *
     * @return 主表ID下对应的所有信息
     */
    @Override
    public List<GroundInfoMessage> showGroundInfoMessageByMainkey(GroundInfoMessage groundInfoMessage) {
        return groundInfoMessageMapper.selectByMainKey(groundInfoMessage);
    }

    /**
     * 修改备案地面审查信息
     *
     * @param groundInfoMessage
     * @return
     */
    @Override
    public int updateGroundInfoMessage(GroundInfoMessage groundInfoMessage) {
        groundInfoMessage.setUpdateTime(new Date());
        return groundInfoMessageMapper.updateByPrimaryKeySelective(groundInfoMessage);
    }

    /**
     * 插入备案地面审查信息
     * @param groundInfoMessage
     * @return true 插入成功 false 插入失败
     */
    @Override
    public boolean insertGroundInfoMessage(GroundInfoMessage groundInfoMessage)  {
        try {
            groundInfoMessage.setCreateTime(new Date());
            n = groundInfoMessageMapper.insertSelective(groundInfoMessage);
        } catch (Exception e) {
            Write.println(Mess.MES_ERROR);
            n = -1;
        } finally {
            flag = false;
            if (n == 1) {
                Write.println(Mess.MES_SUCCCESS);
                flag = true;
            } else {
                flag = false;
                 Write.println(Mess.MES_ERROR);
            }
        return flag;
        }
    }

    /**
     * 按主表ID批量插入明细表信息
     * @param groundInfoMessageList
     * @return
     */
    @Override
    public boolean insertGroundInfoMessageByMainKey(List<GroundInfoMessage> groundInfoMessageList) {
        n = groundInfoMessageList.size();
        try {
            if (n > 0) {
                for (GroundInfoMessage groundInfoMessage : groundInfoMessageList) {
                    j = j + 1;
                    groundInfoMessage.setCreateTime(new Date());
                    groundInfoMessageMapper.insertSelective(groundInfoMessage);
                    Write.println(CommonUtils.showTime() + groundInfoMessage.toString());
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
                Write.println(Mess.MES_ERROR);
                flag = false;
            }
            return flag;
        }
    }

    /**
     * 按主表ID批量修改明细表信息
     * @param groundInfoMessageList
     * @return
     */
    @Override
    public int updateGroundInfoMessageByMainKey(List<GroundInfoMessage> groundInfoMessageList) {
        try {
            for (GroundInfoMessage groundInfoMessage : groundInfoMessageList) {
                groundInfoMessage.setUpdateTime(new Date());
                groundInfoMessageMapper.updateByPrimaryKeySelective(groundInfoMessage);
                Write.println(CommonUtils.showTime() + groundInfoMessage.toString());
                j = j + 1;
            }
        } catch (Exception e) {
            j = -1;
            Write.println(Mess.MES_ERROR);
        } finally {
            return j;
        }
    }

    @Override
    public int updateGroundInfoMessageByMainKey(GroundInfoMessage groundInfoMessageList) {

        return groundInfoMessageMapper.updateByPrimaryKeySelective(groundInfoMessageList);
    }

    @Override
    public int delGroundInfoMessageByMainKey(GroundInfoMessage groundInfoMessage) {
        return groundInfoMessageMapper.deleteByPrimaryKey(groundInfoMessage.getId());
    }


}
