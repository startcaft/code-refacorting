package com.basic.core.service;

import com.basic.core.entity.User;
import com.basic.core.entity.query.UserQuery;
import com.basic.core.entity.vo.UserVo;
import java.util.List;

public interface UserService {

    /**获取一个用户分页列表**/
	List<UserVo> searchUserPage(UserQuery query) throws Exception;
	
	/**保存一个用户**/
	void saveUser(UserVo user) throws Exception;
	
	/**删除一个用户，假删除，将状态改为停用**/
	void lockUser(Long id) throws Exception;
	
	/**启用一个用户**/
	void enableUser(Long id) throws Exception;

	/**更新一个用户**/
	void editUser(UserVo user) throws Exception;
	
	/**根据id获取用户信息**/
	UserVo searchSingleUser(Long id) throws Exception;

	/**用户登录，客户端传递的密码需要使用Shiro提供的MD5工具进行加密再到数据库中查询(把用户名当作slat)**/
	UserVo userLogin(UserVo user) throws Exception;
	
	/**根据用户id获取对应的所有资源列表**/
	List<String> resourceList(Long id) throws Exception;
	
	/**更新用户密码**/
	void editUserPwd(Long id, String oldPwd, String pwd) throws Exception;
	
	/**根据名字查询用户**/
	Long searchUserByLoginName(String loginName) throws Exception;
}