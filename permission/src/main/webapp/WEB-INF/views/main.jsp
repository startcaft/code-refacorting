<%--
  Created by IntelliJ IDEA.
  User: startcaft
  Date: 2017/7/3
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>XXX后台管理</title>

    <meta name="description" content="overview &amp; stats" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <jsp:include flush="true" page="../basecss.jsp"/>
    <link rel="stylesheet" href="${context}/statics/css/conTabs.css?v=1.11" />
</head>
    <body class="no-skin full-height-layout" style="overflow:hidden">
        <div id="wrapper">
            <!--顶部导航栏-->
            <div id="navbar" class="navbar navbar-default navbar-fixed-top">
                <script type="text/javascript">
                    try{ace.settings.check('navbar' , 'fixed')}catch(e){}
                </script>
                <div class="navbar-container" id="navbar-container">
                    <!-- #section:basics/sidebar.mobile.toggle -->
                    <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
                        <span class="sr-only">Toggle sidebar</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <jsp:include page="../top.jsp" flush="true" />
                </div><!-- /.navbar-container -->
            </div>
            <!--顶部导航栏-->

            <div class="main-container" id="main-container">
                <script type="text/javascript">
                    try{ace.settings.check('main-container' , 'fixed')}catch(e){}
                </script>
                <!--竖直导航栏-->
                <div id="sidebar" class="sidebar sidebar-fixed responsive">
                    <script type="text/javascript">
                        try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
                    </script>
                    <!--导航栏快捷按钮-->
                    <div class="sidebar-shortcuts" id="sidebar-shortcuts">
                        <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
                            <button class="btn btn-success">
                                <i class="ace-icon fa fa-signal"></i>
                            </button>

                            <button class="btn btn-info">
                                <i class="ace-icon fa fa-pencil"></i>
                            </button>
                            <button class="btn btn-warning">
                                <i class="ace-icon fa fa-users"></i>
                            </button>

                            <button class="btn btn-danger">
                                <i class="ace-icon fa fa-cogs"></i>
                            </button>
                        </div>
                        <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
                            <span class="btn btn-success"></span>
                            <span class="btn btn-info"></span>
                            <span class="btn btn-warning"></span>
                            <span class="btn btn-danger"></span>
                        </div>
                    </div>
                    <!--导航栏菜单-->
                    <ul class="nav nav-list">
                        <li class="active">
                            <a url="/" href="javascript:;" data-index="152">
                                <i class="menu-icon fa fa-desktop"></i>
                                <span class="menu-text">控制台</span></a>
                            <b class="arrow"></b>
                        </li>
                        <li class="">
                            <a href="#" class="dropdown-toggle">
                                <i class="menu-icon fa fa-android"></i>
                                <span class="menu-text">APP管理</span>
                                <b class="arrow fa fa-angle-down"></b>
                            </a>
                            <b class="arrow"></b>
                            <ul class="submenu">
                                <li class="">
                                    <a url="/app" data-index="192" href="javascript:;">
                                        <i class="menu-icon fa fa-caret-right"></i>
                                        App版本管理
                                    </a>
                                    <b class="arrow"></b>
                                </li>
                            </ul>
                        <li class="">
                            <a href="#" class="dropdown-toggle">
                                <i class="menu-icon fa fa-cogs"></i>
                                <span class="menu-text">系统管理</span>
                                <b class="arrow fa fa-angle-down"></b>
                            </a>
                            <b class="arrow"></b>
                            <ul class="submenu">
                                <li class="">
                                    <a url="/sys/res" data-index="1" href="javascript:;">
                                        <i class="menu-icon fa fa-caret-right"></i>
                                        资源管理
                                    </a>
                                    <b class="arrow"></b>
                                </li>
                                <li class="">
                                    <a url="/sys/org" data-index="1" href="javascript:;">
                                        <i class="menu-icon fa fa-caret-right"></i>
                                        组织管理
                                    </a>
                                    <b class="arrow"></b>
                                </li>
                                <li class="">
                                    <a url="/sys/role" data-index="1" href="javascript:;">
                                        <i class="menu-icon fa fa-caret-right"></i>
                                        角色管理
                                    </a>
                                    <b class="arrow"></b>
                                </li>
                                <li class="">
                                    <a url="/sys/user" data-index="1" href="javascript:;">
                                        <i class="menu-icon fa fa-caret-right"></i>
                                        用户管理
                                    </a>
                                    <b class="arrow"></b>
                                </li>
                                <li class="">
                                    <a url="/sys/log" data-index="1" href="javascript:;">
                                        <i class="menu-icon fa fa-caret-right"></i>
                                        操作日志
                                    </a>
                                    <b class="arrow"></b>
                                </li>
                                <li class="">
                                    <a url="/dict" data-index="1" href="javascript:;">
                                        <i class="menu-icon fa fa-caret-right"></i>
                                        数据字典
                                    </a>
                                    <b class="arrow"></b>
                                </li>
                            </ul>
                        </li>
                    </ul>
                    <!--导航栏收缩按钮-->
                    <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
                        <i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
                    </div>
                    <script type="text/javascript">
                        try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
                    </script>
                </div>

                <!--中间正文-->
                <div class="main-content" id="page-wrapper">
                    <div class="breadcrumbs breadcrumbs-fixed" id="breadcrumbs">
                        <script type="text/javascript">
                            try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                        </script>
                        <!-- 面包屑 -->
                        <%--jc:breadcrumb / --%>
                        <div class="row content-tabs">
                            <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i></button>
                            <nav class="page-tabs J_menuTabs">
                                <div class="page-tabs-content" style="margin-left: 0px;">
                                    <a href="javascript:;" class="J_menuTab active" data-id="/home">控制台</a>
                                </div>
                            </nav>
                            <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i></button>
                        </div>
                    </div>
                    <div class="page-content" id="page-content">
                        <div class="row J_mainContent" id="content-main">
                            <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="http://www.baidu.com" frameborder="0" data-id="/home" seamless></iframe>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../basejs.jsp" flush="true"/>
        <script src="${ context }/statics/js/contabs.min.js"></script>
        <script type="text/javascript">
            var currentIframe;
            $(function(){
                initPwdSettingEvent();
            });

            /*修改密码*/
            function initPwdSettingEvent(){
                $("#pwd-update").click(function(){
                    layer.open({
                        title:'修改密码',
                        type: 2,
                        area: ['600px', '430px'],
                        fix: false, //不固定
                        maxmin: true,
                        content: 'http://www.baidu.com'
                    });
                });
            }
        </script>
    </body>
</html>
