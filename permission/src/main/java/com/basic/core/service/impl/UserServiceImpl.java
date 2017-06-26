package com.basic.core.service.impl;

import com.basic.core.dao.master.UserDao;
import com.basic.core.entity.User;
import com.basic.core.entity.query.Condition;
import com.basic.core.entity.query.UserQuery;
import com.basic.core.entity.vo.UserVo;
import com.basic.core.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

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
    public UserVo userLogin(UserVo user) throws Exception {
        return null;
    }

    @Transactional(value="masterTransactionManager",readOnly = true)
    @Override
    public List<String> resourceList(Long id) throws Exception {
        return null;
    }

    @Transactional(value="masterTransactionManager",readOnly = true)
    @Override
    public void editUserPwd(Long id, String oldPwd, String pwd) throws Exception {

    }

    @Transactional(value="masterTransactionManager",readOnly = true)
    @Override
    public Long searchUserByLoginName(String loginName) throws Exception {
        return null;
    }
}
