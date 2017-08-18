package com.basic.core.service.impl;

import com.basic.core.dao.master.DicItemMapper;
import com.basic.core.entity.DicItem;
import com.basic.core.entity.query.Condition;
import com.basic.core.entity.query.DicItemQuery;
import com.basic.core.entity.vo.DicItemVo;
import com.basic.core.entity.vo.GridVo;
import com.basic.core.service.DicItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class DicItemServiceImpl implements DicItemService {

    @Autowired
    private DicItemMapper itemDao;

    @Transactional(value="masterTransactionManager",readOnly = true)
    @Override
    public GridVo<DicItemVo> getPageItems(DicItemQuery query) throws Exception {
        {
            List<DicItemVo> voList = new ArrayList<>();
            GridVo<DicItemVo> grid = new GridVo<>();

            Map<String,Object> params = query.dynamicBuildWhereConditions(Arrays.asList(
                    new Condition<DicItemQuery>((p) -> !StringUtils.isEmpty(p.getName()),"name",query.getName()),
                    new Condition<DicItemQuery>((p) -> p.getTypeId() != null,"typeId",query.getTypeId())
            ));

            //分页查询
            PageHelper.startPage(query.getPage(),query.getRows());
            List<DicItem> dicItems = itemDao.selectListDynamic(params);
            PageInfo<DicItem> pageInfo = new PageInfo<>(dicItems);

            //构建Vo值对象
            dicItems.forEach((p) -> {
                DicItemVo vo = this.cycleCopyProperties(p);
                voList.add(vo);
            });

            grid.setRows(voList);
            grid.setPage(query.getPage());
            grid.setTotal(pageInfo.getPages());
            grid.setRecords(new Integer(pageInfo.getTotal()+""));

            return grid;
        }
    }

    @Transactional(value="masterTransactionManager",readOnly = true)
    @Override
    public DicItemVo getSingle(Long itemId) throws Exception {
        {
            DicItem entity = itemDao.selectByPrimaryKey(itemId);
            return this.cycleCopyProperties(entity);
        }
    }

    @Transactional(value="masterTransactionManager")
    @Override
    public void saveDicItem(DicItemVo vo) throws Exception {
        {
            if (vo == null){
                throw new Exception("无法保存字典项，因为没有提供足够有效的数据");
            }

            DicItem entity = new DicItem();
            BeanUtils.copyProperties(vo,entity);

            itemDao.insert(entity);
        }
    }

    @Transactional(value="masterTransactionManager")
    @Override
    public void updateDicItem(DicItemVo vo) throws Exception {
        {
            if (vo.getId() == null){
                throw new Exception("无法更新字典项，因为没有提供主键ID值");
            }

            DicItem entity = new DicItem();
            BeanUtils.copyProperties(vo,entity);

            itemDao.updateByPrimaryKeySelective(entity);
        }
    }

    @Transactional(value="masterTransactionManager")
    @Override
    public void removeDicItem(Long itemId) throws Exception {
        {
            if (itemId == null){
                throw new Exception("无法删除字典项目，因为没有提供主键ID值");
            }

            itemDao.deleteByPrimaryKey(itemId);
        }
    }


    //////////////////////////////////////////////////////////
    /**
     * 从模型对象到vo对象的属性拷贝
     * @param entity DicItem 模型对象
     * @return DicItemVo vo对象
     */
    private DicItemVo cycleCopyProperties(DicItem entity){
        {
            DicItemVo vo = new DicItemVo();

            if (entity != null){
                BeanUtils.copyProperties(entity,vo);
                if (entity.getDicType() != null){
                    vo.setDicTypeName(entity.getDicType().getName());
                }
            }

            return vo;
        }
    }
}
