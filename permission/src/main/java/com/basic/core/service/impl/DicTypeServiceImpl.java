package com.basic.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.basic.core.dao.master.DicTypeDao;
import com.basic.core.entity.DicType;
import com.basic.core.entity.query.Condition;
import com.basic.core.entity.query.DicTypeQuery;
import com.basic.core.entity.vo.DicTypeVo;
import com.basic.core.entity.vo.GridVo;
import com.basic.core.service.DicTypeService;
import com.basic.core.service.MQProducerService;
import com.basic.core.util.GlobalConstants;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.jms.Destination;
import javax.lang.model.element.Name;
import java.util.*;

@Service
public class DicTypeServiceImpl implements DicTypeService {

    @Autowired
    private DicTypeDao typeDao;

    @Autowired
    private MQProducerService mqProducerService;

    @Autowired
    @Qualifier(GlobalConstants.DICTYPE_QUEUE_BEAN_NAME)
    private ActiveMQQueue destination;

    @Transactional(value="masterTransactionManager",readOnly = true)
    @Override
    public GridVo<DicTypeVo> getList(DicTypeQuery query) throws Exception {
        {
            List<DicTypeVo> voList = new ArrayList<>();
            GridVo<DicTypeVo> gridVo = new GridVo<>();

            Map<String,Object> param = query.dynamicBuildWhereConditions(Arrays.asList(
                    new Condition<DicTypeQuery>((q) -> !StringUtils.isEmpty(q.getName()),"name",query.getName())
            ));

            //分页
            PageHelper.startPage(query.getPage(),query.getRows());
            List<DicType> types = typeDao.selectListDynamic(param);
            PageInfo<DicType> pageInfo = new PageInfo<DicType>(types);

            //构建Vo值对象
            types.forEach((t) -> {
                DicTypeVo vo = this.cycleCopyProperties(t);
                voList.add(vo);
            });

            gridVo.setRows(voList);
            gridVo.setPage(query.getPage());
            gridVo.setTotal(pageInfo.getPages());
            gridVo.setRecords(new Integer(pageInfo.getTotal()+""));

            return gridVo;
        }
    }

    @Transactional(value="masterTransactionManager",readOnly = true)
    @Override
    public DicTypeVo getSingle(Long typeId) throws Exception {
        {
            DicType entity = typeDao.selectByPrimaryKey(typeId);
            return this.cycleCopyProperties(entity);
        }
    }

    @Transactional(value="masterTransactionManager")
    @Override
    public void saveDicType(DicTypeVo vo) throws Exception {
        {
            if (vo == null){
                throw new Exception("无法保存字典类别，因为没有提供任何有效数据");
            }
            DicType entity = new DicType();
            BeanUtils.copyProperties(vo,entity);
            entity.setUpdateTime(new Date());

            typeDao.insert(entity);

            //推送
            mqProducerService.sendMessage(this.destination, JSON.toJSONString(entity));
        }
    }

    @Transactional(value="masterTransactionManager")
    @Override
    public void updateDicType(DicTypeVo vo) throws Exception {
        {
            if (vo.getId() == null){
                throw new Exception("无法更新字典类别数据，因为没有指定的主键ID");
            }

            DicType entity = new DicType();
            BeanUtils.copyProperties(vo,entity);
            entity.setUpdateTime(new Date());

            typeDao.updateByPrimaryKeySelective(entity);

            //推送
            mqProducerService.sendMessage(this.destination, JSON.toJSONString(entity));
        }
    }


    /**
     * 从模型对象到vo对象的属性拷贝
     * @param entity DicType 模型对象
     * @return DicTypeVo vo对象
     */
    private DicTypeVo cycleCopyProperties(DicType entity){
        {
            DicTypeVo vo = new DicTypeVo();
            if (entity != null){
                BeanUtils.copyProperties(entity,vo);
            }
            return vo;
        }
    }
}
