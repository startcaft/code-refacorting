package com.basic.core.service;

import com.basic.core.entity.vo.ResourceVo;

import java.util.List;


public interface ResourceService {

	/**获取登录用户的被授权的所有系统资源**/
	List<ResourceVo> getUserRoleResrouces(String loginName) throws Exception;

	/**获取第一级的菜单，是不需要被授权的**/
	List<ResourceVo> getRootLevelMenus() throws Exception;

	/**获取指定顶层节点和指定用户被授权的二级菜单**/
	List<ResourceVo> getSecondLevelMenusByRoot(Long rootId, String loginName) throws Exception;

	/*appId,0为共有的资源，加上应用组成in查询*/
	List<ResourceVo> getAllResource(Long appId) throws Exception;

	/*修改*/
	void updateResource(ResourceVo vo) throws Exception;

	/*保存*/
	void saveResource(ResourceVo vo) throws Exception;
}