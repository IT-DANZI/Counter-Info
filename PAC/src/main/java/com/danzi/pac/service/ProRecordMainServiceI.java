package com.danzi.pac.service;

import com.danzi.pac.domain.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Describe ：
 * <p>
 * Author   ：Lily
 * <p>
 * Date     ：2017/9/14.
 */
public interface ProRecordMainServiceI {
    //新增备案信息
    boolean insertProRecord(ProRecordMain proRecordMain, HttpServletRequest request) throws Exception;
    //修改备案信息
    boolean updateProRecord(ProRecordMain proRecordMain,HttpServletRequest request);
    //删除备案信息
    int delProRecord(List<ProRecordMain> proRecordMainList,HttpServletRequest request);
    //查询备案信息
    List<ProRecordMain> showAllProRecord(HrResource hrResource,ProRecordMain proRecordMain);

    int delSysMessageByMainKey(SysMessage sysMessage);

    int delGroundInfoMessageByMainKey(GroundInfoMessage groundInfoMessage);

    int delAlterInfoMessageByMainKey(AlterInfoMessage alterInfoMessage);

    ProRecordMain checkProRecordMain(ProRecordMain proRecordMain);


}
