package com.danzi.pac.service;

import com.danzi.pac.domain.AlterInfoMessage;

import java.util.List;

/**
 * Describe ：备案后抽查信息接口
 * <p>
 * Author   ：Lily
 * <p>
 * Date     ：2017/8/23.
 */
public interface AlterInfoMessageServiceI {

    AlterInfoMessage showDetailAlterInfoMessage(Integer id);

    List<AlterInfoMessage> showAlterInfoMessageByMainkey(AlterInfoMessage alterInfoMessage);

    int updateAlterInfoMessage(AlterInfoMessage alterInfoMessage);

    boolean insertAlterInfoMessage(AlterInfoMessage alterInfoMessage);

    boolean insertAlterInfoMessageByMainKey(List<AlterInfoMessage> alterInfoMessageList);

    int updateAlterInfoMessageByMainKey(List<AlterInfoMessage> alterInfoMessageList);

    int updateAlterInfoMessageByMainKey(AlterInfoMessage alterInfoMessageList);

    int delAlterInfoMessageByMainKey(AlterInfoMessage alterInfoMessage);
}
