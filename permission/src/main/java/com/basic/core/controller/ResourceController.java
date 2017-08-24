package com.basic.core.controller;

import com.basic.core.entity.App;
import com.basic.core.entity.vo.MsgJson;
import com.basic.core.entity.vo.ResourceVo;
import com.basic.core.entity.vo.UserVo;
import com.basic.core.service.ResourceService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/admin/resources")
public class ResourceController {

    @Autowired
    private ResourceService resService;

    @Autowired
    private App app;

    @RequestMapping(value="/roots",method= RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public MsgJson getRoots(){
        {
            MsgJson msgJson = new MsgJson();
            try {
                List<ResourceVo> list = resService.getRootLevelMenus();
                msgJson.setSuccess(true);
                msgJson.setResultData(list);
            }
            catch (Exception e){
                e.printStackTrace();
                msgJson.setTipInfo(e.getMessage());
            }
            return msgJson;
        }
    }

    @RequestMapping(value="/childs/{pid}",method= RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public MsgJson getRoots(@PathVariable("pid")  Long pid){
        {
            MsgJson msgJson = new MsgJson();
            Subject subject = SecurityUtils.getSubject();
            try {
                UserVo userVo = (UserVo) subject.getPrincipal();
                List<ResourceVo> list = resService.getSecondLevelMenusByRoot(pid,userVo.getLoginName());
                msgJson.setSuccess(true);
                msgJson.setResultData(list);
            }
            catch (Exception e){
                e.printStackTrace();
                msgJson.setTipInfo(e.getMessage());
            }
            return msgJson;
        }
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public List<ResourceVo> getAllRes() throws Exception {
        {
            return resService.getAllResource(app.getId());
        }
    }

    @RequestMapping(value = "/roleAll",method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public List<ResourceVo> getAllRoleRes(@RequestParam(value = "roleId") Long id) throws Exception {
        {
            //获取指定角色的系统资源（每个APP都有不同的角色数据）
            List<ResourceVo> roles = resService.getResourcesByRole(id);
            //获取所有的系统资源（包含公共资源和指定appId的资源）
            List<ResourceVo> alls = resService.getAllResource(app.getId());
            alls.forEach((v) -> {
                for(ResourceVo vo : roles){
                    if(v.getId().equals(vo.getId())){
                        v.setChecked(true);
                        break;
                    }
                }
            });

            return alls;
        }
    }

    @RequestMapping(value = "/modify",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public MsgJson modifyRes(ResourceVo vo){
        {
            MsgJson json = new MsgJson();
            try {
                resService.updateResource(vo);
                json.setSuccess(true);
            }
            catch (Exception e){
                e.printStackTrace();
                json.setTipInfo(e.getMessage());
            }
            return json;
        }
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public MsgJson add(ResourceVo vo){
        {
            MsgJson json = new MsgJson();
            if (vo.getId() != null){
                //修改
                try {
                    resService.updateResource(vo);
                    json.setSuccess(true);
                }
                catch (Exception e){
                    e.printStackTrace();
                    json.setTipInfo(e.getMessage());
                }
            }
            else {
                try {
                    vo.setAppId(app.getId());
                    resService.saveResource(vo);
                    json.setSuccess(true);
                }
                catch (Exception e){
                    e.printStackTrace();
                    json.setTipInfo(e.getMessage());
                }
            }
            return json;
        }
    }

    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String editRes(@RequestParam(value="id",required = false) Long resId,
                          Model model) throws Exception{
        {
            if (resId != null){
                //编辑
                ResourceVo vo = resService.getSingle(resId);
                model.addAttribute("res",vo);
            }
            return "sys/res_add";
        }
    }


}
