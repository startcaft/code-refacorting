package com.basic.core.dao.master;


import com.basic.core.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {
	
    Integer insert(User record);

    User selectByPrimaryKey(Long id);

    Integer updateByPrimaryKeySelective(User record);

    //根据登录名和密码查询，登录名具有唯一性
    User selectByNameAndPwd(@Param("loginName") String loginName, @Param("loginPwd") String loginPwd);

    //根据登录名获取个数
    Long selectCountByLoginName(String loginName);

    //分页查询，两个必须的参数pageNo和pageSize
    List<User> selectUserPage(Map<String, Object> map);
    
    //查询指定用户并获取关联的权限列表
    User selectUserByIdWithRoles(Long userId);
    
    //查询指定角色下包含的用户列表
    List<User> selectUsersByRole(Long roleId);
}