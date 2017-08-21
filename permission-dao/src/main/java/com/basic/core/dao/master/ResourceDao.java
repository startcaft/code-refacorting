package com.basic.core.dao.master;

import com.basic.core.entity.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ResourceDao {
	
    Integer insert(Resource record);

    Resource selectByPrimaryKey(Long id);

    List<Resource> selectByLoginName(String loginName);

    //查询所有顶层节点
    List<Resource> selectRoots();

    //查询指定顶层节点，指定用户被授权的二级菜单
    List<Resource> selectSecondLevelMenus(@Param("pid") Long rootId, @Param("loginName") String loginName);

    List<Resource> selectAll(Long appId);

    List<Resource> selectChilds(Long pid);
}