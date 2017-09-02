package com.gs.dao.impl;

import com.gs.bean.User;
import com.gs.common.Pager;
import com.gs.dao.AbstractBaseDAO;
import com.gs.dao.UserDAO;

import java.util.List;

public class UserDAOImpl extends AbstractBaseDAO implements UserDAO {

    @Override
    public void save(User user) {
        getSessionFactory().getCurrentSession().save(user);
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void remove(User user) {
        getSessionFactory().getCurrentSession().delete(user);
    }

    @Override
    public void validById(String id, String status) {

    }

    @Override
    public List<User> listAll() {
        return null;
    }

    @Override
    public Pager<User> listPager(Pager<User> pager) {
        return null;
    }
}
