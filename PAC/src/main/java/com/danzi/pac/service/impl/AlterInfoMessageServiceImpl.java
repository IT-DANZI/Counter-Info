package com.danzi.pac.service.impl;

import com.danzi.pac.dao.AlterInfoMessageMapper;
import com.danzi.pac.domain.AlterInfoMessage;
import com.danzi.pac.domain.Utils;
import com.danzi.pac.service.AlterInfoMessageServiceI;
import com.danzi.pac.utils.CommonUtils;
import com.danzi.pac.utils.Mess;
import com.danzi.pac.utils.Write;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Describe ：备案后抽查信息实现类
 * <p>
 * Author   ：Lily
 * <p>
 * Date     ：2017/8/23.
 */
@Service
public class AlterInfoMessageServiceImpl implements AlterInfoMessageServiceI {
    @Autowired
    private AlterInfoMessageMapper alterInfoMessageMapper;

    private int n;
    private boolean flag;
    private int j = 0;

    /**
     * 查询备案后抽查信息详细
     *
     * @param id AlterInforMessage的id
     * @return AlterInforMessage
     */
    @Override
    public AlterInfoMessage showDetailAlterInfoMessage(Integer id) {
        AlterInfoMessage alterInfoMessage = new AlterInfoMessage();
        alterInfoMessage = alterInfoMessageMapper.selectByPrimaryKey(id);
        Write.println(CommonUtils.showTime() + alterInfoMessage.toString());
        return alterInfoMessage;
    }

    /**
     * 按主表ID查询所有备案信息详细
     *
     * @return List<AlterInfoMessage>
     */
    @Override
    public List<AlterInfoMessage> showAlterInfoMessageByMainkey(AlterInfoMessage alterInfoMessage) {
        return alterInfoMessageMapper.selectByMainKey(alterInfoMessage);
    }

    /**
     * 修改备案后抽查信息
     *
     * @param alterInfoMessage
     * @return Integer类型
     */
    @Override
    public int updateAlterInfoMessage(AlterInfoMessage alterInfoMessage) {
        alterInfoMessage.setUpdateTime(new Date());
        return alterInfoMessageMapper.updateByPrimaryKeySelective(alterInfoMessage);
    }

    /**
     * 单个插入备案后抽查信息
     *
     * @param alterInfoMessage
     * @return false 为插入失败  true 为插入成功
     */
    @Override
    public boolean insertAlterInfoMessage(AlterInfoMessage alterInfoMessage) {
        try {
            alterInfoMessage.setCreateTime(new Date());
            n = alterInfoMessageMapper.insertSelective(alterInfoMessage);
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
     * 按主表ID批量插入备案后抽查信息
     *
     * @param alterInfoMessageList
     * @return true 插入成功  false 插入失败
     */
    @Override
    public boolean insertAlterInfoMessageByMainKey(List<AlterInfoMessage> alterInfoMessageList) {
        n = alterInfoMessageList.size();
        try {
            if (n > 0) {
                for (AlterInfoMessage alterInfoMessage : alterInfoMessageList) {
                    j = j + 1;
                    alterInfoMessage.setCreateTime(new Date());
                    alterInfoMessageMapper.insertSelective(alterInfoMessage);
                    Write.println(CommonUtils.showTime() + alterInfoMessage.toString());
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

    /**
     * 按主表ID批量修改备案后抽查信息
     *
     * @param alterInfoMessageList
     * @return -1 修改失败  其他修改成功条数
     */
    @Override
    public int updateAlterInfoMessageByMainKey(List<AlterInfoMessage> alterInfoMessageList) {
        try {
            for (AlterInfoMessage alterInfoMessage : alterInfoMessageList) {
                alterInfoMessage.setUpdateTime(new Date());
                alterInfoMessageMapper.updateByPrimaryKeySelective(alterInfoMessage);
                Write.println(CommonUtils.showTime() + alterInfoMessage.toString());
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
    public int updateAlterInfoMessageByMainKey(AlterInfoMessage alterInfoMessageList) {
        return alterInfoMessageMapper.updateByPrimaryKeySelective(alterInfoMessageList);
    }

    @Override
    public int delAlterInfoMessageByMainKey(AlterInfoMessage alterInfoMessage) {

        return alterInfoMessageMapper.deleteByPrimaryKey(alterInfoMessage.getId());
    }
}
