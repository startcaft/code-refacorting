package com.basic.core.service;

import com.basic.core.entity.query.RoleQuery;
import com.basic.core.entity.vo.GridVo;
import com.basic.core.entity.vo.RoleVo;

public interface RoleService {

    /*角色分页查询*/
    GridVo<RoleVo> pageRoles(RoleQuery query) throws Exception;

    /*为角色授权*/
    void roleGrant(RoleVo vo) throws Exception;

    /**
     * 保存，确保角色名称是唯一的
     * @param vo
     * @throws Exception
     */
    void save(RoleVo vo) throws Exception;

    /*修改*/
    void update(RoleVo vo) throws Exception;

    /*详细*/
    RoleVo getSingle(Long id) throws Exception;
}
