package com.basic.core.controller;

import com.basic.core.entity.vo.MsgJson;
import com.basic.core.entity.vo.UserPwdVo;
import com.basic.core.entity.vo.UserVo;
import com.basic.core.service.UserService;
import com.basic.core.util.WebUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @RequestMapping(value={"index","login","main"},method = RequestMethod.GET)
    public String main(HttpServletRequest request){
        {
            Optional<UserVo> userVoOptional = WebUtils.getCurrentLoginUser(request.getSession());
            //判断Session中的用户信息，如果存在则返回主页面，否则返回登陆页面
            if (userVoOptional.isPresent()) {
                return "main";
            }
            return "login";
        }
    }

    @RequestMapping(value="/login",method = RequestMethod.POST)
    @ResponseBody
    public MsgJson login(HttpServletRequest request){
        {
            MsgJson msg = new MsgJson();
            String loginName = request.getParameter("username");
            String password = request.getParameter("password");
            //构建AuthenticationToken的子类，会传递到Realm的doGetAuthenticationInfo方法进行身份认证。
            UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
            try {
                Subject subject = SecurityUtils.getSubject();
                subject.login(token);//执行登录

                UserPwdVo user = (UserPwdVo) subject.getPrincipal();
                WebUtils.setCurrentLoginSysUserToSession(request.getSession(),user);//保存session
                msg.setSuccess(true);
            } catch (LockedAccountException e) {
                msg.setTipInfo("账户被停用,请联系系统管理员");
            } catch (AuthenticationException e) {
                msg.setTipInfo("用户名或密码错误");
            }
            return msg;
        }
    }

    @RequestMapping(value="/logout",method=RequestMethod.GET)
    public void logOut(){
        {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
        }
    }
}
