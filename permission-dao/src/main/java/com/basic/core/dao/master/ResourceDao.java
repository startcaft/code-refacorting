package com.basic.core.dao.master;

import com.basic.core.entity.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ResourceDao {

    int insert(Resource record);

    Resource selectByPrimaryKey(Long id);

    List<Resource> selectByLoginName(String loginName);

    List<Resource> selectRoots();

    List<Resource> selectSecondLevelMenus(@Param("pid") Long rootId, @Param("loginName") String loginName);

    List<Resource> selectAll(Long appId);

    int updateByPrimaryKeySelective(Resource record);
}