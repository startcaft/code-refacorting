package com.basic.core.taglib;

import com.basic.core.entity.vo.ResourceVo;
import com.basic.core.entity.vo.UserVo;
import com.basic.core.service.ResourceService;
import com.basic.core.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 * 用户被授权的导航菜单标签<br/>
 * 为了获取到Spring容器中的bean，所以继承org.springframework.web.servlet.tags.RequestContextAwareTag类<br/>
 * 该类继承自javax.servlet.jsp.tagext.TagSupport类<br/>
 * 最后重写其 int doStartTagInternal() 方法即可。
 */
public class UserRoleMenuTag extends RequestContextAwareTag {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRoleMenuTag.class);

    private ResourceService resService;

    //构建只包含二级菜单的HTML字符串
    private String buildMenuHtmlStr(List<ResourceVo> rootMenus, String loginName) throws Exception {
        {
            StringBuilder xmlStr = new StringBuilder();
            xmlStr.append("<ul class=\"nav nav-list\">");
            //默认页
            xmlStr.append("<li class=\"active\">");
            xmlStr.append("<a url=\"/\" href=\"javascript:;\" data-index=\"0\">");
            xmlStr.append("<i class=\"menu-icon fa fa-desktop\"></i>");
            xmlStr.append("<span class=\"menu-text\">控制台</span>");
            xmlStr.append("</a>");
            xmlStr.append("<b class=\"arrow\"></b>");
            xmlStr.append("</li>");
            //一级菜单
            rootMenus.forEach((r) -> {
                xmlStr.append("<li class=\"\">");
                xmlStr.append("<a href=\"#\" class=\"dropdown-toggle\">");
                xmlStr.append("<i class=\"menu-icon fa fa-android\"></i>");
                xmlStr.append(String.format("<span class=\"menu-text\">%1$s</span>", r.getName()));
                xmlStr.append("<b class=\"arrow fa fa-angle-down\"></b>");
                xmlStr.append("</a>");
                xmlStr.append("<b class=\"arrow\"></b>");
                //二级菜单
                try {
                    List<ResourceVo> secondMenu = resService.getSecondLevelMenusByRoot(r.getId(), loginName);
                    if (!secondMenu.isEmpty() && secondMenu.size() > 0) {
                        xmlStr.append("<ul class=\"submenu\">");
                        secondMenu.forEach((c) -> {
                            xmlStr.append("<li class=\"\">");
                            xmlStr.append(String.format("<a url=\"%s\" data-index=\"%s\" href=\"javascript:;\">", c.getUrl(), c.getId()));
                            xmlStr.append("<i class=\"menu-icon fa fa-caret-right\"></i>");
                            xmlStr.append(c.getName());
                            xmlStr.append("</a>");
                            xmlStr.append("<b class=\"arrow\"></b>");
                            xmlStr.append("</li>");
                        });
                        xmlStr.append("</ul>");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                xmlStr.append("</li>");
            });
            xmlStr.append("</ul>");
            return xmlStr.toString();
        }

//          HTML格式
//        <ul class="nav nav-list">
//                    <li class="active">
//                    <a url="/" href="javascript:;" data-index="152">
//                    <i class="menu-icon fa fa-desktop"></i>
//                    <span class="menu-text">控制台</span></a>
//                    <b class="arrow"></b>
//                    </li>
//                    <li class="">
//                    <a href="#" class="dropdown-toggle">
//                    <i class="menu-icon fa fa-android"></i>
//                    <span class="menu-text">APP管理</span>
//                    <b class="arrow fa fa-angle-down"></b>
//                    </a>
//                    <b class="arrow"></b>
//                    <ul class="submenu">
//                    <li class="">
//                    <a url="/app" data-index="192" href="javascript:;">
//                    <i class="menu-icon fa fa-caret-right"></i>
//                App版本管理
//                </a>
//                    <b class="arrow"></b>
//                    </li>
//                    </ul>
//                    <li class="">
//                    <a href="#" class="dropdown-toggle">
//                    <i class="menu-icon fa fa-cogs"></i>
//                    <span class="menu-text">系统管理</span>
//                    <b class="arrow fa fa-angle-down"></b>
//                    </a>
//                    <b class="arrow"></b>
//                    <ul class="submenu">
//                    <li class="">
//                    <a url="/sys/res" data-index="1" href="javascript:;">
//                    <i class="menu-icon fa fa-caret-right"></i>
//                资源管理
//                </a>
//                    <b class="arrow"></b>
//                    </li>
//                    <li class="">
//                    <a url="/sys/org" data-index="1" href="javascript:;">
//                    <i class="menu-icon fa fa-caret-right"></i>
//                组织管理
//                </a>
//                    <b class="arrow"></b>
//                    </li>
//                    <li class="">
//                    <a url="/sys/role" data-index="1" href="javascript:;">
//                    <i class="menu-icon fa fa-caret-right"></i>
//                角色管理
//                </a>
//                    <b class="arrow"></b>
//                    </li>
//                    <li class="">
//                    <a url="/sys/user" data-index="1" href="javascript:;">
//                    <i class="menu-icon fa fa-caret-right"></i>
//                用户管理
//                </a>
//                    <b class="arrow"></b>
//                    </li>
//                    <li class="">
//                    <a url="/sys/log" data-index="1" href="javascript:;">
//                    <i class="menu-icon fa fa-caret-right"></i>
//                操作日志
//                </a>
//                    <b class="arrow"></b>
//                    </li>
//                    <li class="">
//                    <a url="/dict" data-index="1" href="javascript:;">
//                    <i class="menu-icon fa fa-caret-right"></i>
//                数据字典
//                </a>
//                    <b class="arrow"></b>
//                    </li>
//                    </ul>
//                    </li>
//                    </ul>
    }

    @Override
    protected int doStartTagInternal() throws Exception {
        {
            List<ResourceVo> rootMenus = null;
            StringBuilder xmlStr = new StringBuilder();
            try {
                resService = this.getRequestContext().getWebApplicationContext().getBean(ResourceService.class);
                rootMenus = resService.getRootLevelMenus();
            } catch (Exception e) {
                e.printStackTrace();
            }

            HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
            Optional<UserVo> userVoOptional = WebUtils.getCurrentLoginUser(request.getSession());
            if (userVoOptional.isPresent()) {
                String result = this.buildMenuHtmlStr(rootMenus, userVoOptional.get().getLoginName());
                LOGGER.info(result);
                pageContext.getOut().print(result);
            } else {
                pageContext.getOut().print("");
            }
            return SKIP_BODY;
        }
    }
}
