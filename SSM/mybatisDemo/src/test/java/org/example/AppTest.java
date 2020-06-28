package org.example;

import static org.junit.Assert.assertTrue;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLOutput;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession sqlSession;

    @Before
    public void init() {
        //读取mybatis配置文件
        String resource = "mybatis-config.xml";
        InputStream inputStream;
        try {
            //得到配置文件流
            inputStream = Resources.getResourceAsStream(resource);
            //根据配置文件信息，创建会话工厂
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            //通过工厂得到Sqlsession
            sqlSession = sqlSessionFactory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //根据id查询用户
    @Test
    public void testFindUserInfoById() {
        //通过sqlSession执行映射文件中定义的SQL，并返回映射结果
        UserInfo ui = sqlSession.selectOne("findUserInfoById", 101);
        //打印输出结果
        System.out.println(ui.toString());
    }

    //根据用户名模糊查询用户
    @Test
    public void testFindUserInfoByUserName() {
        //执行映射文件中定义的SQL，并返回结果集
        List<UserInfo> uis = sqlSession.selectList("findUserInfoByUserName", "ang");
        for (UserInfo ui : uis) {
            System.out.println(ui);
        }
    }

    //添加用户
    @Test
    public void testAddUserInfo() {
        //创建UserInfo对象ui
        UserInfo ui = new UserInfo(104, "xia", "12334");
        int res = sqlSession.insert("addUserInfo", ui);
        if (res > 0) {
            System.out.println("insert successfully!");
        } else {
            System.out.println("insert failed!");
        }
    }

    //修改用户
    @Test
    public void testUpdateUserInfo(){
        //加载编号为103的用户
        UserInfo ui = sqlSession.selectOne("findUserInfoById",103);
        ui.setPassword("13345");
        int res = sqlSession.update("updateUserInfo",ui);
        if(res > 0){
            System.out.println("update successfully!");
        }else{
            System.out.println("update failed!");
        }
    }

    @Test
    public void testDeleteUserInfo(){
        //执行sqlSession的delete方法，返回结果受影响的行数
        int res = sqlSession.delete("deleteUserInfo",102);
        if(res > 0){
            System.out.println("delete successfully!");
        }else{
            System.out.println("delete failed!");
        }
    }

    @After
    public void destroy() {
        //提交事务
        sqlSession.commit();
        //关闭sqlSession
        sqlSession.close();
    }
}
