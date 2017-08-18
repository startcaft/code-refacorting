package com.basic.core.config.shiro;

import com.basic.core.entity.vo.ResourceVo;
import com.basic.core.entity.vo.UserPwdVo;
import com.basic.core.service.ResourceService;
import com.basic.core.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by startcaft on 2017/7/7.
 * 自定义realm，用于系统用户的认证，授权工作
 */
public class UserDataRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDataRealm.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ResourceService resourceService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        {
            UserPwdVo user = (UserPwdVo) principals.getPrimaryPrincipal();
            String loginName = user.getLoginName();
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

            try {
                //添加权限，注意这里的权限不能有空值或者null，不然在验证权限的时候会报错
                List<ResourceVo> voList = resourceService.getUserRoleResrouces(loginName);
                Set<String> permissions = new HashSet<>();
                voList.forEach((r) -> {
                    permissions.add(r.getUrl());
                });

                authorizationInfo.addStringPermissions(permissions);

                //添加角色
                //authorizationInfo.setRoles(roleService.getResourcesByUser(loginname));
            } catch (Exception e) {
                LOGGER.error("用户授权失败",e);
                return null;
            }

            return authorizationInfo;
        }
    }

    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        {
            //收集用户凭据
            String loginName = (String) token.getPrincipal();
            String password = new String((char[]) token.getCredentials());

            UserPwdVo user = null;
            try {
                user = userService.userLogin(loginName, password);
            } catch (Exception e){
                throw new AuthenticationException(e.getMessage());
            }
            /*如果要给密码加盐
            //盐值，传递给AuthenticationInfo对象
            //ByteSource crendentialsSalt = ByteSource.Util.bytes(username);
            */
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), getName());

            return authenticationInfo;
        }
    }



    //清除认证缓存，仔细辨认保存在缓存中的key是什么？有时候无法清除缓存，可能跟key有关系
    @Override
    protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        {
            Cache<Object, AuthenticationInfo> cache = this.getAuthenticationCache();
            Set<Object> keys = cache.keys();
            for (Object obj : keys) {
                LOGGER.info("remove认证缓存：" + obj + "------" + cache.get(obj) + "------");
            }

            super.clearCachedAuthenticationInfo(principals);
        }
    }

    //清除授权缓存
    @Override
    protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        {
            Cache<Object, AuthorizationInfo> cache = this.getAuthorizationCache();
            Set<Object> keys = cache.keys();
            for (Object obj : keys) {
                LOGGER.info("remove授权缓存：" + obj + "------" + cache.get(obj) + "------");
            }

            super.clearCachedAuthorizationInfo(principals);
        }
    }
}
