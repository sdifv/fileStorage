package com.yhao.fileStorage.config;

import com.yhao.fileStorage.entity.User;
import com.yhao.fileStorage.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken userToken = (UsernamePasswordToken)token;

        User user = new User();
        user.setUsername(userToken.getUsername());
        user.setPassword(String.valueOf(String.valueOf(userToken.getPassword())));

        User userDB = userService.login(user);
        if(userDB == null){
            return null;
        }

        return new SimpleAuthenticationInfo(userDB,user.getPassword(),"");
    }
}
