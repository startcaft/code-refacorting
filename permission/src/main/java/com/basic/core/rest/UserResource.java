package com.basic.core.rest;

import com.basic.core.entity.query.UserQuery;
import com.basic.core.entity.vo.UserVo;
import com.basic.core.exception.WebApplicationExceptionFactory;
import com.basic.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
            throw WebApplicationExceptionFactory.builderException(e);
        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/query/{condition}")
    public String pageQuery(@PathParam("condition") final PathSegment userQueryCondition) {

//        UserQuery query = new UserQuery();
        StringBuilder sb = new StringBuilder();
        MultivaluedMap<String, String> parameters = userQueryCondition.getMatrixParameters();
        Iterator<Map.Entry<String,List<String>>> iterator = parameters.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String,List<String>> entry = iterator.next();
            sb.append(entry.getKey()).append("=");
            sb.append(entry.getValue().get(0)).append(" ");
        }
        return sb.toString();

//        try {
//            List<UserVo> userVoList = userService.searchUserPage(query);
//            return userVoList;
//        }
//        catch (Exception e){
//            if (LOGGER.isErrorEnabled()){
//                LOGGER.error(e.getMessage(),e);
//            }
//        }
//
//        return null;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/query")
    public UserQuery pageQuery1(@BeanParam UserQuery query) {
        return query;
    }
}
