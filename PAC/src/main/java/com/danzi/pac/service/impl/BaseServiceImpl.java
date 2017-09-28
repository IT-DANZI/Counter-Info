package com.danzi.pac.service.impl;

import com.danzi.pac.service.BaseServiceI;

import java.io.Serializable;
import java.util.List;

/**
 * Describe ：
 * <p>
 * Author   ：Lily
 * <p>
 * Date     ：2017/8/24.
 */
public class BaseServiceImpl<T, PK extends Serializable> implements BaseServiceI<T, PK> {

    @Override
    public T showBaseMessage(PK id) {
        return null;
    }

    @Override
    public List<T> showBaseMessageByMainkey(PK id) {
        return null;
    }

    @Override
    public int updateBaseMessage(T t) {

        return 0;
    }

    @Override
    public boolean insertBaseMessage(T t) {
        return false;
    }

    @Override
    public boolean insertBaseMessageByMainKey(List<T> list) {
        return false;
    }

    @Override
    public int updateBaseMessageByMainKey(List<T> list) {
        return 0;
    }
}
