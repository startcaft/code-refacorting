package com.basic.core.taglib;

import com.basic.core.entity.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 系统权限功能按钮
 */
public class UserRoleButtonTag extends TagSupport {

    private String id;
    private String className;
    private String permission;
    private String style;
    private String name;
    private String text;

    @Override
    public int doStartTag() throws JspException {
        {
            StringBuilder btnHtmlStr = new StringBuilder("<button");
            Subject subject = SecurityUtils.getSubject();
            boolean hasPermission = false;

            if (StringUtils.isEmpty(permission)) {
                if (subject.isPermitted(permission)) {
                    hasPermission = true;
                }
            }
            try {
                if(hasPermission){
                    if(StringUtils.isEmpty(className)){
                        btnHtmlStr.append(" class=\""+className+"\"");
                    }
                    if(StringUtils.isEmpty(id)){
                        btnHtmlStr.append(" id=\""+id+"\"");
                    }
                    if(StringUtils.isEmpty(name)){
                        btnHtmlStr.append(" name=\""+name+"\"");
                    }
                    if(StringUtils.isEmpty(style)){
                        btnHtmlStr.append(" style=\""+style+"\"");
                    }
                    btnHtmlStr.append(">");
                    if(StringUtils.isEmpty(text)){
                        btnHtmlStr.append(text);
                    }
                    btnHtmlStr.append("</button>");
                    pageContext.getOut().print(btnHtmlStr.toString());
                }else{
                    pageContext.getOut().print("");
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }

            return SKIP_BODY;
        }
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
