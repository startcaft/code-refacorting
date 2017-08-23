package com.basic.core.service.impl;

import com.basic.core.dao.master.ResourceDao;
import com.basic.core.entity.Resource;
import com.basic.core.entity.enums.ResourceType;
import com.basic.core.entity.enums.States;
import com.basic.core.entity.vo.ResourceVo;
import com.basic.core.service.ResourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
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

            //过滤被停用的系统资源和url为空的资源
            Stream<Resource> resourceStream = resourceList.stream()
                    .filter((r) -> r.getStates() != States.LOCKED)
                    .filter((r) -> !StringUtils.isEmpty(r.getUrl()));

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

    @Transactional(value="masterTransactionManager",readOnly = true)
    @Override
    public List<ResourceVo> getAllResource(Long appId) throws Exception {
        {
            if (appId == null){
                throw new Exception("需要提供指定的appId");
            }
            List<ResourceVo> voList = new ArrayList<>();
            List<Resource> list = resDao.selectAll(appId);
            list.forEach((entity) -> {
                voList.add(this.cycleCopyProperties(entity));
            });
            return voList;
        }
    }

    @Transactional(value="masterTransactionManager")
    @Override
    public void updateResource(ResourceVo vo) throws Exception {
        {
            if (vo.getId() == null){
                throw new Exception("无法更新数据，因为没有提供必要的主键ID");
            }
            Resource entity = new Resource();
            BeanUtils.copyProperties(vo,entity);
            States states = States.getStates(vo.getStatesCode());
            entity.setStates(states);
            resDao.updateByPrimaryKeySelective(entity);
        }
    }

    @Transactional(value="masterTransactionManager")
    @Override
    public void saveResource(ResourceVo vo) throws Exception {
        {
            if (vo == null){
                throw new Exception("无法保存数据，因为没有提供足够的信息");
            }
            Resource entity = new Resource();
            BeanUtils.copyProperties(vo,entity);
            entity.setCreateDatetime(new Date());
            ResourceType type = ResourceType.getResourceType(vo.getResTypeCode());
            entity.setStates(States.NORMAL);
            entity.setResourceType(type);
            //顶级节点，无url，expanded为true，level为1，isLeaf为false
            if (vo.isRoot()){
                entity.setExpanded(true);
                entity.setLevel(1);
                entity.setLeaf(false);
            }
            else {
                if (type == ResourceType.MENU){//二级菜单
                    entity.setExpanded(true);
                    entity.setLevel(2);
                    entity.setLeaf(false);
                }
                else if (type == ResourceType.FUNC){//三级功能
                    entity.setLeaf(true);
                    entity.setExpanded(false);
                    entity.setLevel(3);
                }
            }
            //是否公共资源,公共资源appId为0
            if (vo.isPublic()){
                vo.setAppId(new Long(0));
            }

            resDao.insert(entity);
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
