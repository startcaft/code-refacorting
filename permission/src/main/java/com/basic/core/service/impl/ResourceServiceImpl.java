package com.basic.core.service.impl;

import com.basic.core.dao.master.ResourceDao;
import com.basic.core.entity.Resource;
import com.basic.core.entity.vo.ResourceVo;
import com.basic.core.service.ResourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resDao;

    @Transactional(value="masterTransactionManager",readOnly = true)
    @Override
    public List<ResourceVo> getUserRoleMenus(String loginName) throws Exception {
        {
            List<ResourceVo> voList = new ArrayList<>();
            List<Resource> resourceList = resDao.selectByLoginName(loginName);
            resourceList.forEach((r) -> {
                ResourceVo vo = new ResourceVo();
                BeanUtils.copyProperties(r,vo);
                vo.setResTypeCode(r.getResourceType().getCode());
                vo.setResTypeMsg(r.getResourceType().getDesc());
                voList.add(vo);
            });
            return voList;
        }
    }
}
