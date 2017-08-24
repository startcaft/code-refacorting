package com.basic.core.service;

import com.basic.core.entity.query.RoleQuery;
import com.basic.core.entity.vo.GridVo;
import com.basic.core.entity.vo.RoleVo;

public interface RoleService {

    /*角色分页查询*/
    GridVo<RoleVo> pageRoles(RoleQuery query) throws Exception;

    /*为角色授权*/
    void roleGrant(RoleVo vo) throws Exception;
}
