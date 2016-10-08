package com.login;

import com.core.MyCredentialsMatcher;
import com.user.vo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/9/26.
 */
@Controller
@RequestMapping("/")
public class LoginController {

    @RequestMapping(value = "login")
    public String login(User curUser, ModelMap model){

        Subject user = SecurityUtils.getSubject();
        try{
            curUser.setPassword(new MyCredentialsMatcher().encrypt(curUser.getPassword()));
        }catch (Exception e){
            return "login/login";
        }

        UsernamePasswordToken token = new UsernamePasswordToken(curUser.getUsername(),curUser.getPassword());
        token.setRememberMe(true);

        try {
            user.login(token);
        }catch (UnknownAccountException e){
            token.clear();
            model.put("message", "用户不存在！");
        }catch (IncorrectCredentialsException e){
            token.clear();
            model.put("message", "用户密码错误！");
        }catch (AuthenticationException e) {
            token.clear();
            model.put("message", "出错了！");
            return "login/login";
        }

        model.put("username", curUser.getUsername());
        //判断用户是否成功登录
        if(user.isAuthenticated()){
            return "main";
        }else{
            return "login/login";
        }
    }

    @RequestMapping("logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login/login";
    }

}
