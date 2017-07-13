package com.basic.core.service.impl;

import com.basic.core.dao.master.ResourceDao;
import com.basic.core.entity.Resource;
import com.basic.core.entity.enums.ResourceType;
import com.basic.core.entity.enums.States;
import com.basic.core.entity.vo.NodeTree;
import com.basic.core.entity.vo.ResourceVo;
import com.basic.core.service.ResourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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

            //过滤功能按钮类型的资源和被停用的资源
            Stream<Resource> resourceStream = resourceList.stream()
                    .filter((r) -> r.getResourceType() == ResourceType.FUNC)
                    .filter((r) -> r.getStates() == States.LOCKED);
            resourceStream.forEach((r) -> {
                ResourceVo vo = new ResourceVo();
                BeanUtils.copyProperties(r,vo);
                vo.setResTypeCode(r.getResourceType().getCode());
                vo.setResTypeMsg(r.getResourceType().getDesc());
                voList.add(vo);
            });
            return voList;
        }
    }

    @Transactional(value="masterTransactionManager",readOnly = true)
    @Override
    public List<ResourceVo> getUserRoleResrouces(String loginName) throws Exception {
        {
            List<Resource> resourceList = resDao.selectByLoginName(loginName);
            return this.cycleCopyProperties(resourceList);
        }
    }

    @Transactional(value="masterTransactionManager",readOnly = true)
    @Override
    public List<ResourceVo> getRootLevelMenus() throws Exception {
        {
            List<Resource> list = resDao.selectRoots();
            return this.cycleCopyProperties(list);
        }
    }

    @Transactional(value="masterTransactionManager",readOnly = true)
    @Override
    public List<ResourceVo> getSecondLevelMenusByRoot(Long rootId,String loginName) throws Exception {
        {
            List<Resource> list = resDao.selectSecondLevelMenus(rootId,loginName);
            return this.cycleCopyProperties(list);
        }
    }

    /**
     * 循环copyProperties
     * @param list 原始查询到的集合
     * @return
     */
    private List<ResourceVo> cycleCopyProperties(List<Resource> list){
        {
            List<ResourceVo> voList = new ArrayList<>();
            list.forEach((r) -> {
                ResourceVo vo = new ResourceVo();
                BeanUtils.copyProperties(r, vo);
                vo.setResTypeCode(r.getResourceType().getCode());
                vo.setResTypeMsg(r.getResourceType().getDesc());
                vo.setStatesCode(r.getStates().getCode());
                vo.setStatesMsg(r.getStates().getMsg());

                if (r.getResource() != null){
                    vo.setPid(r.getResource().getId());
                    vo.setPname(r.getResource().getName());
                }
                voList.add(vo);
            });
            return voList;
        }
    }
}
