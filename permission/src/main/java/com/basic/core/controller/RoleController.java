package com.basic.core.controller;

import com.basic.core.entity.App;
import com.basic.core.entity.query.RoleQuery;
import com.basic.core.entity.vo.GridVo;
import com.basic.core.entity.vo.MsgJson;
import com.basic.core.entity.vo.RoleVo;
import com.basic.core.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "/admin/roles")
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private App app;

    @RequestMapping(value = "/grant",method = RequestMethod.GET)
    public String grantView(@RequestParam(value = "roleId",required = true) Long id,
                            Model model) throws Exception {
        {
            model.addAttribute("roleId",id);
            return "sys/res_grant";
        }
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public GridVo<RoleVo> rolePage(RoleQuery query) throws Exception {
        {
            GridVo<RoleVo> vo = roleService.pageRoles(query);
            return vo;
        }
    }

    @RequestMapping(value = "/grant",method = RequestMethod.POST)
    @ResponseBody
    public MsgJson grant(@RequestParam(value = "roleId",required = true) Long id,
                         @RequestParam(value = "resIds") String resId){
        {
            MsgJson json = new MsgJson();
            try {
                RoleVo vo = new RoleVo();
                vo.setId(id);
                vo.setResIds(resId);

                roleService.roleGrant(vo);
                json.setSuccess(true);
            }
            catch (Exception e){
                e.printStackTrace();
                json.setTipInfo(e.getMessage());
            }
            return json;
        }
    }
}
