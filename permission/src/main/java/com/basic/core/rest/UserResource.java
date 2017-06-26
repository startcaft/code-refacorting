package com.basic.core.rest;

import com.basic.core.controller.UserController;
import com.basic.core.entity.vo.UserVo;
import com.basic.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by startcaft on 2017/6/26.
 */
@Component
@Path("/users")
public class UserResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);

    @Autowired
    private UserService userService;

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserVo getById(@PathParam("userId") Long userId) {
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
