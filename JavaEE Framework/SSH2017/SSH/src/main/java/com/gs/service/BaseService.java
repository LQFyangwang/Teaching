package com.gs.service;

import com.gs.common.Pager;

import java.util.List;

public interface BaseService<PK, T> {

    void save(T t);
    void update(T t);
    void remove(T t);
    void validById(PK id, String status);
    List<T> listAll();
    Pager<T> listPager(Pager<T> pager);

}
