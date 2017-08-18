package com.basic.core.dao.master;

import com.basic.core.entity.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ResourceDao {
	
    Integer insert(Resource record);

    Resource selectByPrimaryKey(Long id);

    /**获取用户相关的系统资源列表,map的key可以有resourcetype和userId**/
//    List<Resource> selectUserRoleResouces(Map<String, Object> map);
    
    /**获取子菜单，map的key可以有resourcetype和userId和pid**/
//    List<Resource> selectUserRoleChildReouces(Map<String, Object> map);
    
    List<Resource> selectByLoginName(String loginName);

    //查询所有顶层节点
    List<Resource> selectRoots();

    //查询指定顶层节点，指定用户被授权的二级菜单
    List<Resource> selectSecondLevelMenus(@Param("pid") Long rootId, @Param("loginName") String loginName);
}