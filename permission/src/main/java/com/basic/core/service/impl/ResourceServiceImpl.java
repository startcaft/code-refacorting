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

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resDao;

    @Transactional(value="masterTransactionManager",readOnly = true)
    @Override
    public List<ResourceVo> getUserRoleResrouces(String loginName) throws Exception {
        {
            List<Resource> resourceList = resDao.selectByLoginName(loginName);
            List<ResourceVo> voList = new ArrayList<>();

            //过滤被停用的系统资源
            Stream<Resource> resourceStream = resourceList.stream()
                    .filter((r) -> r.getStates() != States.LOCKED);

            resourceStream.forEach((r) -> {
                voList.add(this.cycleCopyProperties(r));
            });
            return voList;
        }
    }

    @Transactional(value="masterTransactionManager",readOnly = true)
    @Override
    public List<ResourceVo> getRootLevelMenus() throws Exception {
        {
            List<Resource> list = resDao.selectRoots();
            List<ResourceVo> voList = new ArrayList<>();
            list.forEach((entity) -> {
                voList.add(this.cycleCopyProperties(entity));
            });
            return voList;
        }
    }

    @Transactional(value="masterTransactionManager",readOnly = true)
    @Override
    public List<ResourceVo> getSecondLevelMenusByRoot(Long rootId,String loginName) throws Exception {
        {
            List<Resource> list = resDao.selectSecondLevelMenus(rootId,loginName);
            List<ResourceVo> voList = new ArrayList<>();
            list.forEach((entity) -> {
                voList.add(this.cycleCopyProperties(entity));
            });
            return voList;
        }
    }

    /**
     * 从模型对象到vo对象的属性拷贝
     * @param entity Resource 模型对象
     * @return ResourceVo vo对象
     */
    private ResourceVo cycleCopyProperties(Resource entity){
        {
            ResourceVo vo = new ResourceVo();
            if (entity != null){
                BeanUtils.copyProperties(entity, vo);
                vo.setResTypeCode(entity.getResourceType().getCode());
                vo.setResTypeMsg(entity.getResourceType().getDesc());
                vo.setStatesCode(entity.getStates().getCode());
                vo.setStatesMsg(entity.getStates().getMsg());

                if (entity.getResource() != null){
                    vo.setPid(entity.getResource().getId());
                    vo.setPname(entity.getResource().getName());
                }
            }
            return vo;
        }
    }
}
