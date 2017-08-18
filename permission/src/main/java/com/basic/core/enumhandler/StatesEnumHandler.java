package com.basic.core.enumhandler;

import com.basic.core.entity.enums.States;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 自定义枚举转换器
 */
public class StatesEnumHandler implements TypeHandler<States> {

    //如何保存到数据库中
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, States states, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, states.getCode());
    }

    //根据枚举状态码返回枚举对象
    @Override
    public States getResult(ResultSet resultSet, String s) throws SQLException {
        int statesCode = resultSet.getInt(s);
        return States.getStates(statesCode);
    }

    @Override
    public States getResult(ResultSet resultSet, int i) throws SQLException {
        int statesCode = resultSet.getInt(i);
        return States.getStates(statesCode);

    }

    @Override
    public States getResult(CallableStatement callableStatement, int i) throws SQLException {
        int statesCode = callableStatement.getInt(i);
        return States.getStates(statesCode);
    }
}
