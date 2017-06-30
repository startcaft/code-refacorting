package com.basic.core.dao.master;

import java.util.List;
import java.util.Map;
import com.basic.core.entity.Resource;


public interface ResourceDao {
	
    Integer insert(Resource record);

    Resource selectByPrimaryKey(Long id);

    List<Resource> selectAll(Map<String, Object> map);
    
    /**获取用户相关的系统资源列表,map的key可以有resourcetype和userId**/
    List<Resource> selectUserRoleResouces(Map<String, Object> map);
    
    /**获取子菜单，map的key可以有resourcetype和userId和pid**/
    List<Resource> selectUserRoleChildReouces(Map<String, Object> map);
    
    List<Resource> selectByLoginName(String loginName);
}