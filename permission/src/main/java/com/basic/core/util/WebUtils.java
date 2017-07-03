package com.basic.core.util;

import com.basic.core.entity.vo.UserVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by startcaft on 2017/7/3.
 */
public class WebUtils {

    public static final String USER_KEY_IN_SESSION = "loginUser";

    /**
     * 获取当前登录用户
     * @param httpSession
     * @return
     */
    public static UserVo getCurrentLoginUser(HttpSession httpSession){
        UserVo sysUser=(UserVo)httpSession.getAttribute(USER_KEY_IN_SESSION);
        return sysUser;
    }

    public static UserVo getCurrentLoginUser(HttpServletRequest request){
        UserVo sysUser=getCurrentLoginUser(request.getSession());
        return sysUser;
    }

    /**
     * 保存登陆用户
     * @param httpSession
     * @param user
     */
    public static void setCurrentLoginSysUserToSession(HttpSession httpSession ,UserVo user){
        httpSession.setAttribute(USER_KEY_IN_SESSION,user);
    }
}