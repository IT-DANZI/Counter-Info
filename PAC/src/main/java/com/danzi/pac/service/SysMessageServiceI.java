package com.danzi.pac.service;

import com.danzi.pac.domain.GroundInfoMessage;
import com.danzi.pac.domain.SysMessage;

import java.util.List;

/**
 * Describe ：系统备案信息接口
 * <p>
 * Author   ：Lily
 * <p>
 * Date     ：2017/8/23.
 */
public interface SysMessageServiceI {
    SysMessage showSysMessage(Integer id);

    List<SysMessage> showSysMessageByMainkey(SysMessage sysMessage);

    int updateSysMessage(SysMessage sysMessage);

    boolean insertSysMessage(SysMessage sysMessage);

    boolean insertSysMessageByMainKey(List<SysMessage> sysMessageList);

    int updateSysMessageByMainKey(SysMessage sysMessageList);

    int delSysMessageByMainKey(SysMessage sysMessage);
}
