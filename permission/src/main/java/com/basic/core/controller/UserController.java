package com.basic.core.controller;

import com.basic.core.entity.query.UserQuery;
import com.basic.core.entity.vo.UserVo;
import com.basic.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping("/permission/users")
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
}
