package com.core;

import com.user.service.UserService;
import com.user.vo.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class MyRealm extends AuthorizingRealm{

    @Autowired
    UserService userService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        if(principalCollection == null){
            throw new AuthorizationException();
        }

        User user = (User) principalCollection.getPrimaryPrincipal();

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        Set rolesSet = userService.getUserRolesSet(user.getUsername());
        Set permissionsSet = new HashSet();
        for (Object role:rolesSet) {
            String role_name = (String)role;
            Set rolePermissionSet = userService.getRolePermissionsSet(role_name);
            permissionsSet.addAll(rolePermissionSet);

        }

        simpleAuthorizationInfo.setRoles(rolesSet);
        simpleAuthorizationInfo.setStringPermissions(permissionsSet);

        System.out.println("查询用户权限...【角色："+rolesSet+"】【权限："+permissionsSet+"】");
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        String username = token.getUsername();

        String password = "";
        if (token.getPassword() != null) {
            password = new String(token.getPassword());
        }

        //password = new MyCredentialsMatcher().encrypt(password);
        User user = userService.getUserAnthenticaition(username, password);

        if (user != null) {
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password.toCharArray(), getName());
            return info;
        }

        return null;
    }

}