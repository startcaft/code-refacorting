package com.basic.core.service.impl;

import com.basic.core.dao.master.ResourceDao;
import com.basic.core.entity.vo.ResourceVo;
import com.basic.core.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resDao;

    @Override
    public List<ResourceVo> getUserRoleMenus(String loginName) throws Exception {
        return null;
    }
}
