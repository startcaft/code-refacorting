package com.basic.core.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by startcaft on 2017/6/28.
 */
@Provider
public class DbServerExceptionMapper implements ExceptionMapper<DbServerException> {

    @Override
    public Response toResponse(DbServerException exception) {
        return Response.status(500).entity(exception.getMessage()).type(MediaType.TEXT_PLAIN).build();
    }
}
