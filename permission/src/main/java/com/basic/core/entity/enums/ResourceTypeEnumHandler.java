package com.basic.core.entity.enums;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by startcaft on 2017/6/29.
 */
public class ResourceTypeEnumHandler implements TypeHandler<ResourceType> {

    //保存code到数据库
    @Override
    public void setParameter(PreparedStatement ps, int i, ResourceType parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i,parameter.getCode());
    }

    //通过code获取枚举类型
    @Override
    public ResourceType getResult(ResultSet rs, String columnName) throws SQLException {
        Integer code = rs.getInt(columnName);
        return ResourceType.getResourceType(code);
    }

    @Override
    public ResourceType getResult(ResultSet rs, int columnIndex) throws SQLException {
        Integer code = rs.getInt(columnIndex);
        return ResourceType.getResourceType(code);
    }

    @Override
    public ResourceType getResult(CallableStatement cs, int columnIndex) throws SQLException {
        Integer code = cs.getInt(columnIndex);
        return ResourceType.getResourceType(code);
    }
}
