package com.basic.core.service.impl;

import com.basic.core.dao.master.UserDao;
import com.basic.core.entity.User;
import com.basic.core.entity.enums.States;
import com.basic.core.entity.query.Condition;
import com.basic.core.entity.query.UserQuery;
import com.basic.core.entity.vo.UserPwdVo;
import com.basic.core.entity.vo.UserVo;
import com.basic.core.service.UserService;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional(value="masterTransactionManager",readOnly = true)
    @Override
    public List<UserVo> searchUserPage(UserQuery query) throws Exception {
        {
            List<UserVo> userVoList = new ArrayList<>();

            List<Condition> conditions = Arrays.asList(
                    new Condition<UserQuery>((q) -> !StringUtils.isEmpty(q.getName()), "name", query.getName()),
                    new Condition<UserQuery>((q) -> q.getOrgId() != null, "orgId", query.getOrgId())
            );

            Map<String,Object> params = query.dynamicBuildWhereConditions(conditions);
            PageHelper.startPage(query.getPage(), query.getRows());
            List<User> users = userDao.selectUserPage(params);
            users.forEach((u) -> {
                UserVo vo = new UserVo();
                BeanUtils.copyProperties(u,vo);
                if (u.getOrganization() != null) {
                    vo.setOrganizationId(u.getOrganization().getId());
                    vo.setOrganizationName(u.getOrganization().getName());
                }
                users.add(u);
            });

            return userVoList;
        }
    }

    @Transactional("masterTransactionManager")
    @Override
    public void saveUser(UserVo user) throws Exception {

    }

    @Transactional("masterTransactionManager")
    @Override
    public void lockUser(Long id) throws Exception {

    }

    @Transactional("masterTransactionManager")
    @Override
    public void enableUser(Long id) throws Exception {

    }

    @Transactional("masterTransactionManager")
    @Override
    public void editUser(UserVo user) throws Exception {

    }

    @Transactional(value="masterTransactionManager",readOnly = true)
    @Override
    public UserVo searchSingleUser(Long id) throws Exception {
        UserVo vo = new UserVo();
        if (id != null){
            User user = userDao.selectByPrimaryKey(id);
            if (user != null){
                BeanUtils.copyProperties(user,vo);
                if (!user.getRoles().isEmpty()){
                    List<Long> rolesIds = new ArrayList<>();
                    List<String> rolesNames = new ArrayList<>();
                    user.getRoles().forEach((u) -> {
                        rolesIds.add(u.getId());
                        rolesNames.add(u.getName());
                    });
                    vo.setRoleIds(rolesIds);
                    vo.setRoleNames(rolesNames);
                }
            }
        }
        return vo;
    }

    @Transactional(value="masterTransactionManager",readOnly = true)
    @Override
    public UserPwdVo userLogin(String loginName, String password) throws AuthenticationException {
        User user = userDao.selectByNameAndPwd(loginName, new Md5Hash(password).toString());
        if (user == null){
            throw new AuthenticationException();//用户不存在，或密码错误
        }
        if (user.getStates() == States.LOCKED){
            throw new LockedAccountException();//用户被锁定，无法使用
        }
        UserPwdVo vo = new UserPwdVo();
        BeanUtils.copyProperties(user,vo);
        return vo;
    }

    @Transactional(value="masterTransactionManager",readOnly = true)
    @Override
    public List<String> resourceList(Long id) throws Exception {
        return null;
    }

    @Transactional(value="masterTransactionManager")
    @Override
    public void editUserPwd(Long id, String oldPwd, String pwd) throws Exception {
        {
            UserVo vo = this.searchSingleUser(id);
            if (vo == null || vo.getId() == null){
                throw new Exception("无法获取指定的系统用户");
            }
            if (userDao.selectByNameAndPwd(vo.getLoginName(),new Md5Hash(oldPwd).toString()) == null){
                throw new Exception("错误的原始密码");
            }

            User user = new User();
            user.setId(vo.getId());
            user.setPassword(new Md5Hash(pwd).toString());

            userDao.updateByPrimaryKeySelective(user);
        }
    }

    @Transactional(value="masterTransactionManager",readOnly = true)
    @Override
    public Optional<UserVo> searchUserByLoginName(String loginName) throws Exception {
        {
            UserVo vo = null;
            User user = userDao.selectByLoginName(loginName);
            if (user != null){
                vo = new UserVo();
                BeanUtils.copyProperties(user,vo);
            }

            return Optional.ofNullable(vo);
        }
    }
}
