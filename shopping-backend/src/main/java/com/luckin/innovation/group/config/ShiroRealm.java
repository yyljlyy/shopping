package com.luckin.innovation.group.config;

import com.luckin.innovation.group.entity.SystemPermission;
import com.luckin.innovation.group.entity.SystemRole;
import com.luckin.innovation.group.entity.SystemUser;
import com.luckin.innovation.group.service.impl.SystemUserServiceImpl;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

/**
 * @author Jasper Lee (Jian) 创建于 2018-11-23 上午12:16
 * @company Luckin Coffe AI Group
 * @description com.luckin.innovation.group.service.impl
 * 版权所有 违法必究
 */
public class ShiroRealm extends AuthorizingRealm {
    @Resource
    private SystemUserServiceImpl userService;

    /**
     * 认证信息(身份验证) Authentication 是用来验证用户身份
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        SystemUser user = userService.findByUsername(username);
        if (user == null) {
            return null;
        }

        return new SimpleAuthenticationInfo(user,
                user.getPassWord(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                getName()
        );
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SystemUser user = (SystemUser) principals.getPrimaryPrincipal();
        for(SystemRole role:user.getRoles()){
            authorizationInfo.addRole(role.getRoleName());
            for(SystemPermission p:role.getPermissions()){
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return authorizationInfo;
    }
}
