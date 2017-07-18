package com.basic.core.controller;

import com.basic.core.entity.query.UserQuery;
import com.basic.core.entity.vo.MsgJson;
import com.basic.core.entity.vo.UserVo;
import com.basic.core.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/page",method = RequestMethod.POST)
    @ResponseBody
    public List<UserVo> dataGrid(UserQuery query) {

        try {
            List<UserVo> userVoList = userService.searchUserPage(query);
            return userVoList;
        }
        catch (Exception e){
            if (LOGGER.isErrorEnabled()){
                LOGGER.error(e.getMessage(),e);
            }
        }

        return null;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public UserVo getById(@PathVariable("id") Long userId) {

        try {
            UserVo vo = userService.searchSingleUser(userId);
            return vo;
        }
        catch (Exception e){
            if (LOGGER.isErrorEnabled()){
                LOGGER.error(e.getMessage(),e);
            }
        }
        return null;
    }

    @RequestMapping(value="/pwdUpdate",method = RequestMethod.POST)
    @ResponseBody
    public MsgJson pwdUpdate(HttpServletRequest request){
        {
            MsgJson msg = new MsgJson();
            String oldPwd = request.getParameter("oldPwd");
            String newPwd = request.getParameter("newPwd");
            String pwdRepeat = request.getParameter("pwdRepeat");
            if (!newPwd.equalsIgnoreCase(pwdRepeat)){
                msg.setTipInfo("两次输入的密码不一致");
                return msg;
            }

            Subject subject = SecurityUtils.getSubject();
            UserVo user = (UserVo) subject.getPrincipal();

            try {
                userService.editUserPwd(user.getId(),oldPwd,newPwd);
                msg.setSuccess(true);
            } catch (Exception e){
                msg.setTipInfo(e.getMessage());
                LOGGER.debug(e.getMessage(),e);
            }

            return msg;
        }
    }
}
