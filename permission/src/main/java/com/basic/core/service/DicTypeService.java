package com.basic.core.service;

import com.basic.core.entity.DicType;
import com.basic.core.entity.query.DicTypeVoQuery;
import com.basic.core.entity.vo.DicTypeVo;
import com.basic.core.entity.vo.GridVo;

import java.util.List;

public interface DicTypeService {

    /*分页获取所有字典类别*/
    GridVo<DicTypeVo> getList(DicTypeVoQuery query) throws Exception;

    /*根据主键ID获取详细信息*/
    DicTypeVo getSingle(Long typeId) throws Exception;

    /*保存*/
    void saveDicType(DicTypeVo vo) throws Exception;

    /*更新*/
    void updateDicType(DicTypeVo vo) throws Exception;
}
