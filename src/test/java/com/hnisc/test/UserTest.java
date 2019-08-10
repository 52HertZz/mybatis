package com.hnisc.test;

import com.hnisc.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @author lh141
 */
public class UserTest {

    /**
     * 入门案例
     */
    @Test
    public void testFindUserById() throws Exception {
        String resource = "SqlMapConfig.xml";
        //通过流将核心配置文件读取进来
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //通过核心配置文件输入流来创建会话工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        //通过工厂创建会话
        SqlSession session = factory.openSession();
        //第一个参数:所调用的sql语句= namespace+.+sql的ID
        User user = session.selectOne("test.findUserById", 1);
        System.out.println(user);
        session.close();
    }

    @Test
    public void testFindUserByUserName() throws Exception {
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session =factory.openSession();
        List<User> list = session.selectList("test.findUserByUserName", "王");
        System.out.println(list);
        session.close();
    }

    @Test
    public void testInsertUser() throws Exception {
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session =factory.openSession();

        User user = new User();
        user.setUsername("张三");
        user.setSex("男");

        System.out.println("增加前的id"+user.getId());

        session.insert("test.insertUser",user);
        //提交事务(mybatis会自动开启事务,但是它不知道何时提交,所以需要手动提交事务)
        session.commit();

        System.out.println("增加后的id"+user.getId());
    }

    @Test
    public void testDeleteUserById() throws Exception {
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session =factory.openSession();

        session.delete("test.deleteUserById",28);
        session.commit();
    }

    @Test
    public void testUpdateUserById() throws Exception {
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session =factory.openSession();

        User user = new User();
        user.setId(27);
        user.setUsername("李四");
        session.update("test.updateUserById",user);
        session.commit();
    }
}
