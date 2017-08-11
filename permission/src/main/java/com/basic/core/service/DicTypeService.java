package com.basic.core.service;

import com.basic.core.entity.vo.DicTypeVo;
import java.util.List;

public interface DicTypeService {

    /*分页获取所有字典类别*/
    List<DicTypeVo> getList() throws Exception;

    /*获取字典类别详细*/
    DicTypeVo getSingle(Long typeId) throws Exception;

    /*保存字典类别*/
    void saveDicType(DicTypeVo vo) throws Exception;
}
