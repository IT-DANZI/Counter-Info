package com.danzi.pac.service;

import java.io.Serializable;
import java.util.List;

/**
 * Describe ：
 * <p>
 * Author   ：Lily
 * <p>
 * Date     ：2017/8/24.
 */
public interface BaseServiceI<T,PK extends Serializable> {
    /**
     * 查询详细信息
     * @param id
     * @return
     */
    T showBaseMessage(PK id);

    /**
     * 通过主表查询明细表所有信息
     * @param id
     * @return
     */
    List<T> showBaseMessageByMainkey(PK id);

    /**
     * 修改信息
     * @param t
     * @return
     */
    int updateBaseMessage(T t);

    /**
     * 插入信息
     * @param t
     * @return
     */
    boolean insertBaseMessage(T t);

    /**
     * 按主表ID插入所有明细信息
     * @param list
     * @return
     */
    boolean insertBaseMessageByMainKey(List<T> list);

    /**
     * 按主表ID修改所有明细信息
     * @param list
     * @return
     */
    int updateBaseMessageByMainKey(List<T> list);


}
