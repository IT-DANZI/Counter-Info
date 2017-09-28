package com.danzi.pac.controller;

import com.danzi.pac.domain.AlterInfoMessage;
import com.danzi.pac.domain.GroundInfoMessage;
import com.danzi.pac.domain.ProRecordMain;
import com.danzi.pac.domain.SysMessage;
import com.danzi.pac.service.ProRecordMainServiceI;
import com.danzi.pac.utils.JsonResult;
import com.danzi.pac.utils.URL;
import com.danzi.pac.utils.Write;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Describe ：
 * <p>
 * Author   ：Lily
 * <p>
 * Date     ：2017/9/14.
 */
@Controller
@RequestMapping("ProRecordMainController")
public class ProRecordMainController {
    @Autowired
    private ProRecordMainServiceI proRecordMainServiceI;

    /**
     * 添加产品信息
     *
     * @param request
     * @param proRecordMain
     * @return
     */
    @RequestMapping(value = "addProRecord")
    private
    @ResponseBody
    JsonResult addProRecord(HttpServletRequest request, ProRecordMain proRecordMain) {
        String rqGroundInfoMessList = request.getParameter("groundInfoMessList");
        String rqAlterInfoMessList = request.getParameter("alterInfoMessList");
        String rqSysMessList = request.getParameter("sysMessList");
        Write.println(rqGroundInfoMessList);
        Write.println(rqAlterInfoMessList);
        Write.println(rqSysMessList);
        boolean flag;
        int i;
        try {
            flag = proRecordMainServiceI.insertProRecord(this.getProRecord(rqGroundInfoMessList, rqAlterInfoMessList, rqSysMessList, proRecordMain), request);
            if (flag) {
                i = 1;
            } else i = -1;
        } catch (Exception e) {
            flag = false;
            i = -1;
            e.printStackTrace();
        }
        return new JsonResult(i);
    }

    /**
     * 查询所有产品信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "showProRecord")
    private String showProRecord(HttpServletRequest request) {
        List<ProRecordMain> proRecordMainList = new ArrayList<ProRecordMain>();
        proRecordMainList = proRecordMainServiceI.showAllProRecord(null, null);
        request.setAttribute("proRecord", proRecordMainList);
        return URL.PRODUCT_RECORD;
    }

    /**
     * 修改商品前查询商品的详细信息
     *
     * @param request
     * @param proRecordMain
     * @return
     */
    @RequestMapping(value = "showDetailProRecord")
    private String showDetailProRecord(HttpServletRequest request, ProRecordMain proRecordMain) {
        List<ProRecordMain> proRecordMainList = new ArrayList<ProRecordMain>();
        proRecordMainList = proRecordMainServiceI.showAllProRecord(null, proRecordMain);
        ProRecordMain proRecordMain2 = new ProRecordMain();
        for (ProRecordMain proRecordMain1 : proRecordMainList) {
            proRecordMain2 = proRecordMain1;
        }
        request.setAttribute("proRecordList", proRecordMain2);
        return URL.PRODUCT_RECORD_EDIT;
    }

    @RequestMapping(value = "updateProRecordMessage")
    private
    @ResponseBody
    JsonResult updateProRecordMessage(HttpServletRequest request, ProRecordMain proRecordMain) {
        String rqGroundInfoMessList = request.getParameter("groundInfoMessList");
        String rqAlterInfoMessList = request.getParameter("alterInfoMessList");
        String rqSysMessList = request.getParameter("sysMessList");
        Write.println(rqGroundInfoMessList);
        Write.println(rqAlterInfoMessList);
        Write.println(rqSysMessList);
        boolean flag;
        try {
            flag = proRecordMainServiceI.updateProRecord(this.getProRecord(rqGroundInfoMessList, rqAlterInfoMessList, rqSysMessList, proRecordMain), request);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return new JsonResult(flag);
    }

    /**
     * 删除地面审查信息
     *
     * @param groundInfoMessage
     * @return
     */
    @RequestMapping(value = "delGroundMessage")
    private
    @ResponseBody
    JsonResult delGroundMessage(GroundInfoMessage groundInfoMessage) {

        return new JsonResult(proRecordMainServiceI.delGroundInfoMessageByMainKey(groundInfoMessage));
    }

    /**
     * 删除备后审查信息
     *
     * @param alterInfoMessage
     * @return
     */
    @RequestMapping(value = "delAlterMessage")
    private
    @ResponseBody
    JsonResult delAlterMessage(AlterInfoMessage alterInfoMessage) {

        return new JsonResult(proRecordMainServiceI.delAlterInfoMessageByMainKey(alterInfoMessage));
    }

    /**
     * 删除系统备案信息
     *
     * @param sysMessage
     * @return
     */
    @RequestMapping(value = "delSystemMessage")
    private
    @ResponseBody
    JsonResult delSystemMessage(SysMessage sysMessage) {

        return new JsonResult(proRecordMainServiceI.delSysMessageByMainKey(sysMessage));
    }

    @RequestMapping(value = "delProRecordMessage")
    private
    @ResponseBody
    JsonResult delProRecordMessage(HttpServletRequest request) {
        String rqProRecordMessageList = request.getParameter("proRecordMessageList");
        proRecordMainServiceI.delProRecord(this.getProRecordMain(rqProRecordMessageList),request);
        return new JsonResult();
    }

    private ProRecordMain getProRecord(String rqGroundInfoMessList, String rqAlterInfoMessList, String rqSysMessList, ProRecordMain proObj) throws Exception {
        proObj.setAlterInfoMessagesList(this.getAlterInfoMessage(rqAlterInfoMessList));
        proObj.setGroundInfoMessageList(this.getGroundInfoMess(rqGroundInfoMessList));
        proObj.setSysMessageList(this.getSysMessage(rqSysMessList));
        Write.println("==============" + proObj.getSysMessageList().size());
        Write.println("==============" + proObj.getGroundInfoMessageList().size());
        Write.println("==============" + proObj.getAlterInfoMessagesList().size());
        return proObj;
    }

    private List<GroundInfoMessage> getGroundInfoMess(String str) {
        JSONArray JSONArryOfGroundInfoMess = JSONArray.fromObject(str);
        List<GroundInfoMessage> ltGroundInfoMessage = new ArrayList<GroundInfoMessage>();
        try {
            Write.println(str + "-------+1");
            for (int i = 0; i < JSONArryOfGroundInfoMess.size(); i++) {
                JSONObject jsonOBJ = JSONArryOfGroundInfoMess.getJSONObject(i);
                GroundInfoMessage groundInfoMessage = new GroundInfoMessage();
                groundInfoMessage.setBornspace(jsonOBJ.getString("bornspace"));
                groundInfoMessage.setIfstatus(jsonOBJ.getInt("ifstatus"));
                if (jsonOBJ.getInt("id") != -1) {
                    groundInfoMessage.setId(jsonOBJ.getInt("id"));
                } else {
                    groundInfoMessage.setId(null);
                }
                Write.println(" groundInfoMessage.setId::::::" + JSONArryOfGroundInfoMess.size());
                ltGroundInfoMessage.add(groundInfoMessage);

            }
        } catch (Exception e) {

        }

        return ltGroundInfoMessage;
    }

    private List<AlterInfoMessage> getAlterInfoMessage(String str) {
        Write.println(str + "-------+2");
        JSONArray JSONArryOfAlterInfoMess = JSONArray.fromObject(str);
        List<AlterInfoMessage> ltAlterInfoMessage = new ArrayList<AlterInfoMessage>();
        try {

            for (int j = 0; j < JSONArryOfAlterInfoMess.size(); j++) {
                JSONObject jsonOBJ = JSONArryOfAlterInfoMess.getJSONObject(j);
                AlterInfoMessage alterinfoMessage = new AlterInfoMessage();
                alterinfoMessage.setIfstatus(jsonOBJ.getInt("ifstatus"));
                alterinfoMessage.setBornspace(jsonOBJ.getString("bornspace"));
                if (jsonOBJ.getInt("id") != -1) {
                    alterinfoMessage.setId(jsonOBJ.getInt("id"));
                } else {
                    alterinfoMessage.setId(null);
                }
                ltAlterInfoMessage.add(alterinfoMessage);
            }
        } catch (Exception e) {

        }

        return ltAlterInfoMessage;
    }

    private List<SysMessage> getSysMessage(String str) throws Exception {
        Write.println(str + "-------+3");
        JSONArray JSONArryOfSysMess = JSONArray.fromObject(str);
        List<SysMessage> ltSysMessage = new ArrayList<SysMessage>();
        try {
            for (int k = 0; k < JSONArryOfSysMess.size(); k++) {
                JSONObject jsonOBJ = JSONArryOfSysMess.getJSONObject(k);
                SysMessage sysMessage = new SysMessage();
                sysMessage.setBornspace(jsonOBJ.getString("bornspace"));
                sysMessage.setIfstatus(jsonOBJ.getInt("ifstatus"));
                if (jsonOBJ.getInt("id") != -1) {
                    sysMessage.setId(jsonOBJ.getInt("id"));
                } else {
                    sysMessage.setId(null);
                }
                ltSysMessage.add(sysMessage);
            }
        } catch (Exception e) {

        }
        return ltSysMessage;
    }

    private List<ProRecordMain> getProRecordMain(String str){
        JSONArray jsonArrayOfProRecordMain = JSONArray.fromObject(str);
        List<ProRecordMain> proRecordMainList = new ArrayList<ProRecordMain>();
        try {
            for (int k=0;k<jsonArrayOfProRecordMain.size();k++){
                JSONObject jsonObject = jsonArrayOfProRecordMain.getJSONObject(k);
                ProRecordMain proRecordMain = new ProRecordMain();
                proRecordMain.setId(jsonObject.getInt("id"));
                proRecordMainList.add(proRecordMain);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return proRecordMainList;
        }
    }
}
