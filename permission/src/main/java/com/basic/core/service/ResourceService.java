package com.basic.core.service;

import com.basic.core.entity.vo.NodeTree;
import com.basic.core.entity.vo.ResourceVo;
import java.util.List;
import java.util.Set;


public interface ResourceService {

	/**获取登录用户的被授权的系统菜单**/

	List<ResourceVo> getUserRoleMenus(String loginName) throws Exception;

	/**获取登录用户的被授权的所有系统资源**/
	List<ResourceVo> getUserRoleResrouces(String loginName) throws Exception;

	/**获取第一级的菜单，是不需要被授权的**/
	List<ResourceVo> getRootLevelMenus() throws Exception;

	/**获取指定顶层节点和指定用户被授权的二级菜单**/
	List<ResourceVo> getSecondLevelMenusByRoot(Long rootId,String loginName) throws Exception;
}