package com.basic.core.service.impl;

import com.basic.core.dao.master.RoleDao;
import com.basic.core.dao.master.UserDao;
import com.basic.core.entity.Role;
import com.basic.core.entity.query.Condition;
import com.basic.core.entity.query.RoleQuery;
import com.basic.core.entity.vo.GridVo;
import com.basic.core.entity.vo.RoleVo;
import com.basic.core.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Qualifier("batchSession")
    @Autowired
    private SqlSession session;

    @Transactional(value="masterTransactionManager",readOnly = true)
    @Override
    public GridVo<RoleVo> pageRoles(RoleQuery query) throws Exception {
        {
            GridVo<RoleVo> gridVo = new GridVo<>();
            List<RoleVo> voList = new ArrayList<>();

            //分页参数
            Map<String,Object> param = query.dynamicBuildWhereConditions(Arrays.asList(
                    new Condition<RoleQuery>((q) -> !StringUtils.isEmpty(q.getRoleName()),"roleName",query.getRoleName())
            ));

            //分页
            PageHelper.startPage(query.getPage(),query.getRows());
            List<Role> roles = roleDao.selectListDynamic(param);
            PageInfo<Role> pageInfo = new PageInfo<>(roles);

            //构建Vo值对象
            roles.forEach((t) -> {
                RoleVo vo = this.cycleCopyProperties(t);
                voList.add(vo);
            });

            gridVo.setRows(voList);
            gridVo.setPage(query.getPage());
            gridVo.setTotal(pageInfo.getPages());
            gridVo.setRecords(new Integer(pageInfo.getTotal()+""));

            return gridVo;
        }
    }

    @Override
    public void roleGrant(RoleVo vo) throws Exception {
        {
            if (vo == null){
                throw new Exception("无法完成授权，缺少相关的数据");
            }

            //第一步，批量删除该角色原本的关联资源
            roleDao.deleteRoleRes(vo.getId());
            //第二部，批量添加传递进来的资源id数组
            if (!StringUtils.isEmpty(vo.getResIds())){
                String[] ids = vo.getResIds().split(",");
                for (int i=0;i<ids.length;i++){
                    //roleDao.insertRoleRes(vo.getId(),new Long(ids[i]));
                    session.getMapper(RoleDao.class).insertRoleRes(vo.getId(),new Long(ids[i]));
                }
            }
        }
    }


    //////////////////////////////////////////////
    private RoleVo cycleCopyProperties(Role entity){
        {
            RoleVo vo = new RoleVo();
            if (entity != null){
                BeanUtils.copyProperties(entity,vo);
            }
            return vo;
        }
    }
}
