package com.basic.core.entity.enums;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 自定义【UserStates】枚举转换器
 */
public class UserStatesEnumHandler implements TypeHandler<UserStates> {

    //如何保存到数据库中
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, UserStates userStates, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,userStates.getCode());
    }

    //根据枚举状态码返回枚举对象
    @Override
    public UserStates getResult(ResultSet resultSet, String s) throws SQLException {
        int statesCode = resultSet.getInt(s);
        return UserStates.getStates(statesCode);
    }

    @Override
    public UserStates getResult(ResultSet resultSet, int i) throws SQLException {
        int statesCode = resultSet.getInt(i);
        return UserStates.getStates(statesCode);

    }

    @Override
    public UserStates getResult(CallableStatement callableStatement, int i) throws SQLException {
        int statesCode = callableStatement.getInt(i);
        return UserStates.getStates(statesCode);
    }
}
