package com.basic.core.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * 数据库服务器异常，常见情况如无法创建数据库连接，惟一性限制，当前的操作因为死锁而失败等
 * 具体参见DataAccessException体系
 */
public class DbServerException extends WebApplicationException {


    //定义HTTP状态码
    public DbServerException() {
        super(Response.Status.INTERNAL_SERVER_ERROR);
    }

    public DbServerException(String message) {
        super(message);
    }
}
