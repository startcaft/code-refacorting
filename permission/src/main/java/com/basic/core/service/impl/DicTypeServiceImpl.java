package com.basic.core.service.impl;

import com.basic.core.dao.master.DicTypeDao;
import com.basic.core.entity.DicType;
import com.basic.core.entity.query.Condition;
import com.basic.core.entity.query.DicTypeVoQuery;
import com.basic.core.entity.vo.DicTypeVo;
import com.basic.core.entity.vo.GridVo;
import com.basic.core.service.DicTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class DicTypeServiceImpl implements DicTypeService {

    @Autowired
    private DicTypeDao typeDao;

    @Transactional(value="masterTransactionManager",readOnly = true)
    @Override
    public GridVo<DicTypeVo> getList(DicTypeVoQuery query) throws Exception {
        {
            List<DicTypeVo> voList = new ArrayList<>();
            GridVo<DicTypeVo> gridVo = new GridVo<>();

            Map<String,Object> param = query.dynamicBuildWhereConditions(Arrays.asList(
                    new Condition<DicTypeVoQuery>((q) -> !StringUtils.isEmpty(q.getName()),"name",query.getName())
            ));

            //分页
            PageHelper.startPage(query.getPage(),query.getRows());
            List<DicType> types = typeDao.selectList(param);
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
