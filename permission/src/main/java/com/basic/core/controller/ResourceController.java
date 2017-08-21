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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
                msgJson.setTipInfo(e.getMessage());
                msgJson.setSuccess(false);
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
                msgJson.setTipInfo(e.getMessage());
                msgJson.setSuccess(false);
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
}
