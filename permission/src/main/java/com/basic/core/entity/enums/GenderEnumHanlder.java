package com.basic.core.entity.enums;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by startcaft on 2017/6/23.
 */
public class GenderEnumHanlder implements TypeHandler<Gender> {

    //如何保存到数据库中
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Gender gender, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,gender.getCode());
    }

    //根据枚举状态码返回枚举对象
    @Override
    public Gender getResult(ResultSet resultSet, String s) throws SQLException {
        int code = resultSet.getInt(s);
        return Gender.getGender(code);
    }

    @Override
    public Gender getResult(ResultSet resultSet, int i) throws SQLException {
        int code = resultSet.getInt(i);
        return Gender.getGender(code);

    }

    @Override
    public Gender getResult(CallableStatement callableStatement, int i) throws SQLException {
        int code = callableStatement.getInt(i);
        return Gender.getGender(code);
    }
}
