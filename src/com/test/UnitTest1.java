package com.test;

import com.core.UUIDGen;
import com.user.controller.UserController;
import com.user.vo.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.ModelAndView;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:/resources/test/spring-mvc.xml","classpath:/resources/test/spring-content.xml","classpath:/resources/test/spring-shiro.xml"})
public class UnitTest1 {

    static int flag = 0;
    // request,response
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Autowired
    UserController userController;

    @Before
    public void init(){
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }

    @Test
    public void testGetUserByUserName(){
        ModelAndView mav = userController.getUserByUsername("admin");
        System.out.println(mav.getModel());
    }

    @Test
    public void testGetUserList(){
        ModelAndView mav = userController.getUserList();
        System.out.println(mav.getViewName());
        System.out.println(mav.getModel().get("userList"));
    }

    @Test
    public void testAddUser(){
        long start = System.currentTimeMillis();
        /**
         *  循环插入100个用户,测试用户1547ms
         */
        for(int i = 0;i<1000; i++) {
            User user = new User();
            user.setId(UUIDGen.getUUID());
            user.setPassword(i + "");
            user.setUsername("username" + i);
            userController.add(user);
        }
        System.out.println((System.currentTimeMillis() - start)+"ms");// 总共耗时：10736ms
    }

    @Test
    public void testAddUser2(){

        // 最小线程数为2，最大线程数为4，队列深度为4
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 4, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(4),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        System.out.println("线程开始...");
        long start = System.currentTimeMillis();

        for (int i=0; i<4; i++){
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 250; i++) {
                        User user = new User();
                        user.setId(UUIDGen.getUUID());
                        user.setPassword("password" + i);
                        user.setUsername("username" + i);
                        userController.add(user);
                        flag++;
                    }
                }
            });
        }

        threadPool.shutdown();

        boolean b = true;
        while (b){
            System.out.print(""); // 为什么不写这句话，线程永远不知道自动判断flag的值已经被修改成了多少，主线程一直在循环无法结束。
            if(flag>=999){
                b = false;
            }
        }
        System.out.println((System.currentTimeMillis() - start)+"ms"); // 总耗时：5854ms
        System.out.println("线程结束...");
    }
}
