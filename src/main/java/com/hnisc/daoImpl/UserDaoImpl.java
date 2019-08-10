package com.hnisc.daoImpl;

import com.hnisc.dao.UserDao;
import com.hnisc.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author lh141
 */
public class UserDaoImpl implements UserDao {

    private SqlSessionFactory sqlSessionFactory;

    //通过构造方法注入
    public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    //按id查询
    public User findUserById(Integer id) {
        //sqlSesion是线程不安全的,所以它的最佳使用范围在方法体内
        SqlSession session = sqlSessionFactory.openSession();
        User user= session.selectOne("test.findUserById",id);
        return user;
    }

    //按用户名查询
    public List<User> findUserByUserName(String userName) {
        SqlSession session = sqlSessionFactory.openSession();
        List<User> list = session.selectList("findUserByUserName",userName);
        return list;
    }
}
