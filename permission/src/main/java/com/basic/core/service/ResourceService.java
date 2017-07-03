package com.basic.core.service;

import com.basic.core.entity.vo.ResourceVo;
import java.util.List;
import java.util.Set;


public interface ResourceService {

	/**获取登录用户的被授权的系统菜单**/
	List<ResourceVo> getUserRoleMenus(String loginName) throws Exception;
}
