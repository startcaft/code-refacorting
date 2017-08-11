package com.basic.core.service.impl;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.basic.core.dao.master.DicTypeDao;
import com.basic.core.entity.DicType;
import com.basic.core.entity.vo.DicTypeVo;
import com.basic.core.entity.vo.ResourceVo;
import com.basic.core.service.DicTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DicTypeServiceImpl implements DicTypeService {

    @Autowired
    private DicTypeDao typeDao;

    @Transactional(value="masterTransactionManager",readOnly = true)
    @Override
    public List<DicTypeVo> getList() throws Exception {
        {
            List<DicTypeVo> voList = new ArrayList<>();
            List<DicType> types = typeDao.selectList();

            types.forEach((t) -> {
                DicTypeVo vo = this.cycleCopyProperties(t);
                voList.add(vo);
            });

            return voList;
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
