package com.gs.service.impl;

import com.gs.bean.User;
import com.gs.common.Pager;
import com.gs.dao.UserDAO;
import com.gs.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void remove(User user) {
        userDAO.remove(user);
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
