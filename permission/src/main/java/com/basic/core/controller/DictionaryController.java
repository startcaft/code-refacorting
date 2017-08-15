package com.basic.core.controller;

import com.basic.core.entity.App;
import com.basic.core.entity.DicItem;
import com.basic.core.entity.query.DicItemQuery;
import com.basic.core.entity.query.DicTypeQuery;
import com.basic.core.entity.vo.DicItemVo;
import com.basic.core.entity.vo.DicTypeVo;
import com.basic.core.entity.vo.GridVo;
import com.basic.core.entity.vo.MsgJson;
import com.basic.core.service.DicItemService;
import com.basic.core.service.DicTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/dics")
public class DictionaryController {

    @Autowired
    private DicTypeService typeService;

    @Autowired
    private DicItemService itemService;

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
    public GridVo<DicTypeVo> typeList(DicTypeQuery query) throws Exception {
        {
            return typeService.getList(query);
        }
    }

    @RequestMapping(value="/save",method= RequestMethod.POST)
    @ResponseBody
    public MsgJson saveOrUpdateDicType(DicTypeVo vo){
        {
            MsgJson json = new MsgJson();
            if (vo.getId() != null){
                //编辑
                try {
                    typeService.updateDicType(vo);
                    json.setSuccess(true);
                }
                catch (Exception e){
                    e.printStackTrace();
                    json.setTipInfo(e.getMessage());
                }
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

    //////////////////////////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping(value="/items/{typeId}",method = RequestMethod.GET)
    public String itemIndex(@PathVariable(name = "typeId",required = true) long typeId,
                            Model model){
        {
            model.addAttribute("typeId",typeId);
            return "sys/dic_item_index";
        }
    }

    /* item add/edit view */
    @RequestMapping(value="/items/add",method=RequestMethod.GET)
    public String modifyDicItemView(@RequestParam(value="typeId",required = true) Long typeId,
                                    @RequestParam(value = "itemId",required = false) Long itemId,
                                    Model model) throws Exception {
        {
            if (itemId != null){
                DicItemVo item = itemService.getSingle(itemId);
                model.addAttribute("item",item);
            }
            model.addAttribute("typeId",typeId);
            return "sys/dic_item_add";
        }
    }

    @RequestMapping(value="/items/save",method= RequestMethod.POST)
    @ResponseBody
    public MsgJson saveOrUpdateDicItem(DicItemVo vo){
        {
            MsgJson json = new MsgJson();
            if (vo.getId() != null){
                //编辑
                try {
                    itemService.updateDicItem(vo);
                    json.setSuccess(true);
                }
                catch (Exception e){
                    e.printStackTrace();
                    json.setTipInfo(e.getMessage());
                }
            }
            else {
                //保存
                try {
                    itemService.saveDicItem(vo);
                    json.setSuccess(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    json.setTipInfo(e.getMessage());
                }
            }
            return json;
        }
    }

    @RequestMapping(value="/items/search",method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public GridVo<DicItemVo> itemList(DicItemQuery query) throws Exception {
        {
            return itemService.getPageItems(query);
        }
    }

    @RequestMapping(value="/items/remove",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public MsgJson itemRemove(@RequestParam(value = "itemId",required = true) Long itemId){
        {
            MsgJson json = new MsgJson();

            try {
                itemService.removeDicItem(itemId);
                json.setSuccess(true);
            }
            catch (Exception e){
                json.setTipInfo(e.getMessage());
            }

            return json;
        }
    }

}
