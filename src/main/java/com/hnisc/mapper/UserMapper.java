package com.hnisc.mapper;

import com.hnisc.pojo.CustomOrders;
import com.hnisc.pojo.Orders;
import com.hnisc.pojo.QueryVo;
import com.hnisc.pojo.User;

import java.util.List;

/**
 * @author lh141
 */
public interface UserMapper {

    public User findUserById(Integer id);

    //动态代理形势中,如果返回结果集问List,那么mybatis会在生成实现类的使用会自动调用selectList方法
    public List<User> findUserByUserName(String userName);

    public  void insertUser(User user);

    public List<User> findUserByVo(QueryVo queryVo);

    public Integer findUserCount();

    public List<User> findUserByUserNameAndSex(User user);

    public  List<User> findUserByIds(QueryVo queryVo);

    public List<CustomOrders> findOrdersAndUser1();

    public List<Orders>  findOrdersAndUser2();

    public List<User> findUserAndOrders();
}
