package com.basic.core.dao.master;

import com.basic.core.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RoleDao {

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    List<Role> selectListDynamic(Map<String,Object> param);

    int deleteRoleRes(Long id);

    int insertRoleRes(@Param("roleId")Long id, @Param("resId") Long resId);
}