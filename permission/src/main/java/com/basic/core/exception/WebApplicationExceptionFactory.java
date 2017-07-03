package com.basic.core.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.transaction.CannotCreateTransactionException;

import javax.ws.rs.WebApplicationException;

/**
 * Rest服务WebApplicationException异常工厂
 */
public class WebApplicationExceptionFactory {

    public static WebApplicationException builderException(Exception ex){

        WebApplicationException exception = null;

        //spring无法构建datasource
        if (ex instanceof CannotCreateTransactionException){
            exception = new DbServerException("数据访问资源彻底失败，例如不能连接数据库");
        }
        if (ex instanceof DataIntegrityViolationException){
            exception = new DbServerException("Insert或Update数据时违反了完整性，例如违反了惟一性限制");
        }
        if (ex instanceof BadSqlGrammarException){
            exception = new DbServerException("无法解析的SQL语句，例如字段名不一致");
        }
        return exception;
    }
}
