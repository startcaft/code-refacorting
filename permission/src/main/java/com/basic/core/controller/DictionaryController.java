package com.basic.core.controller;

import com.basic.core.entity.App;
import com.basic.core.entity.vo.DicTypeVo;
import com.basic.core.entity.vo.MsgJson;
import com.basic.core.service.DicTypeService;
import jdk.nashorn.internal.ir.RuntimeNode;
import net.sf.ehcache.search.parser.MValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping("/admin/dics")
public class DictionaryController {

    @Autowired
    private DicTypeService typeService;

    @Autowired
    private App app;

    /* add/edit view */
    @RequestMapping(value="/add",method=RequestMethod.GET)
    public String modifyDicTypeView(@RequestParam(value="typeId",required = false) Long typeId,
                                    Model model) throws Exception {
        {
            if (typeId != null){
                DicTypeVo type = typeService.getSingle(typeId);
                model.addAttribute("type",type);
            }
            return "sys/dic_type_add";
        }
    }

    @RequestMapping(value="/types",method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public List<DicTypeVo> typeList() throws Exception {
        {
            List<DicTypeVo> data = typeService.getList();
            return data;
        }
    }

    @RequestMapping(value="/save",method= RequestMethod.POST)
    @ResponseBody
    public MsgJson saveOrUpdateDicType(DicTypeVo vo){
        {
            MsgJson json = new MsgJson();
            if (vo.getId() != null){
                //编辑
            }
            else {
                //保存
                try {
                    vo.setAppId(app.getId());
                    typeService.saveDicType(vo);
                    json.setSuccess(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    json.setTipInfo(e.getMessage());
                }
            }
            return json;
        }
    }
}
