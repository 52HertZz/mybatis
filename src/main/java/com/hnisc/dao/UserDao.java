package com.hnisc.dao;

import com.hnisc.pojo.User;

import java.util.List;

/**
 * @author lh141
 */
public interface UserDao {

    public User findUserById(Integer id);

    public List<User> findUserByUserName(String userName);
}
