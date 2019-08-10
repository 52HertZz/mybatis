package com.hnisc.test;

import com.hnisc.mapper.UserMapper;
import com.hnisc.pojo.CustomOrders;
import com.hnisc.pojo.Orders;
import com.hnisc.pojo.QueryVo;
import com.hnisc.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lh141
 */
public class UserMapperTest {

    private SqlSessionFactory factory;

    //作用:在测试方法前执行这个方法
    @Before
    public void setUp() throws Exception{
        String resource = "SqlMapConfig.xml";
        //通过流将核心配置文件读取进来
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //通过核心配置文件输入流来创建会话工厂
        factory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testFindUserById() throws Exception {
        SqlSession session = factory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);

        User user = mapper.findUserById(1);
        System.out.println(user);

    }

    @Test
    public void testFindUserByUserName() throws Exception {
        SqlSession session = factory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);

        List<User> list = mapper.findUserByUserName("王");
        System.out.println(list);
    }

    @Test
    public void testInsertUser() throws  Exception {
        SqlSession session = factory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);

        User user = new User();
        user.setUsername("阿凡达");
        user.setSex("男");
        user.setBirthday(new Date());

        mapper.insertUser(user);

        session.commit();
    }

    @Test
    public void testFindUserByVo() throws Exception {
        SqlSession session = factory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);

        QueryVo queryVo = new QueryVo();
        User user = new User();

        user.setUsername("张");
        user.setSex("1");

        queryVo.setUser(user);

        List<User> list = mapper.findUserByVo(queryVo);
        System.out.println(list);
    }

    @Test
    public void testFindUserCount() throws  Exception {
        SqlSession session = factory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);

        Integer integer = mapper.findUserCount();
        System.out.println(integer);
    }

    @Test
    public void testFindUserByUserNameAndSex() throws  Exception {
        SqlSession session = factory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);

        User user = new User();

        user.setUsername("张");
        user.setSex("1");

        List<User> list = mapper.findUserByUserNameAndSex(user);
        System.out.println(list);
    }

    @Test
    public void testFindUserByIds() throws  Exception {
        SqlSession session = factory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);

        QueryVo queryVo =new QueryVo();
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(1);
        ids.add(22);
        ids.add(27);
        ids.add(30);
        queryVo.setIds(ids);

        List<User> list = mapper.findUserByIds(queryVo);
        System.out.println(list);
    }

    @Test
    public void testFindOrdersAndUser1() throws  Exception {
        SqlSession session = factory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);

        List<CustomOrders> list = mapper.findOrdersAndUser1();
        System.out.println(list);
    }

    @Test
    public void testFindOrdersAnduUser2() throws Exception{
        SqlSession session = factory.openSession();
        //通过getMapper方法来实例化接口
        UserMapper mapper = session.getMapper(UserMapper.class);

        List<Orders> list = mapper.findOrdersAndUser2();
        System.out.println(list);
    }

    @Test
    public void testFindUserAndOrders() throws Exception{
        SqlSession session = factory.openSession();
        //通过getMapper方法来实例化接口
        UserMapper mapper = session.getMapper(UserMapper.class);

        List<User> list = mapper.findUserAndOrders();
        System.out.println(list);
    }
}
