package com.basic.core.service;

import com.basic.core.entity.vo.ResourceVo;
import java.util.List;
import java.util.Set;


public interface ResourceService {
	
	/**添加资源，只支持二级菜单，二级菜单下可以有一级功能按钮**/
	void add(ResourceVo vo) throws Exception;
	
	/**删除资源**/
	void delete(long id) throws Exception;
	
	/**编辑资源**/
	void edit(ResourceVo resource) throws Exception;
	
	/**根据主键获取Resource对象**/
	ResourceVo get(Long id) throws Exception;

	/**获取所有的顶层节点菜单**/
	List<ResourceVo> getRoots() throws Exception;

	/*获取指定一级节点的所有二级子节点*/
	List<ResourceVo> getChildrens(Long pid) throws Exception;

	/*获取二级节点下的功能那妞*/
	List<ResourceVo> getFuncs(Long pid) throws Exception;
	
	/**根据登录用户获取资源列表的集合**/
	public Set<String> getResourcesByUser(String loginName) throws Exception;
}
