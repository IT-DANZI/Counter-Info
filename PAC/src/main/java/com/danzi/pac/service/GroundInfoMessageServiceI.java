package com.danzi.pac.service;

import com.danzi.pac.domain.AlterInfoMessage;
import com.danzi.pac.domain.GroundInfoMessage;

import java.util.List;

/**
 * Describe ：备案地面审查信息（3个月内送现场审查）接口
 * <p>
 * Author   ：Lily
 * <p>
 * Date     ：2017/8/23.
 */
public interface GroundInfoMessageServiceI {
    GroundInfoMessage showGroundInfoMessage(Integer id);

    List<GroundInfoMessage> showGroundInfoMessageByMainkey(GroundInfoMessage groundInfoMessage);

    int updateGroundInfoMessage(GroundInfoMessage groundInfoMessage);

    boolean insertGroundInfoMessage(GroundInfoMessage groundInfoMessage) throws ClassNotFoundException;

    boolean insertGroundInfoMessageByMainKey(List<GroundInfoMessage> groundInfoMessageList);

    int updateGroundInfoMessageByMainKey(List<GroundInfoMessage> groundInfoMessageList);

    int updateGroundInfoMessageByMainKey(GroundInfoMessage groundInfoMessageList);

    int delGroundInfoMessageByMainKey(GroundInfoMessage groundInfoMessage);
}
