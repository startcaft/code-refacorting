<%--
  Created by IntelliJ IDEA.
  User: startcaft
  Date: 2017/7/3
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/pk-tag" prefix="pk"%>
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
                    <pk:SecondLevelMenu/>

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
                                    <a href="javascript:;" class="J_menuTab active" data-id="http://www.baidu.com">控制台</a>
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
            var contextPath = '${context}';
            $(function(){
                menuEventInit(contextPath);
                tabsEventInit();
                initPwdSettingEvent();
            });

            /**
             * 导航菜单单击事件处理函数<br/>
             * 获取到资源菜单的id和url和text，具体请看二级导航菜单的自定义标签的构成<br/>
             * 在 content-main 下激活或者添加一个当前url和id组成的iframe<br/>
             * 在 page-tabs-content 下激活或者添加一个当前id和text组成的a<br/>
             * 跟easyui差不多，easyui封装的更加完善些。
             */
            function menuEventInit(contextPath){
                $('.nav-list a').click(function(e){
                    e.preventDefault();
                    var url = $(this).attr('url');
                    if(url){
                        $('iframe').css('display','none');
                        window.location.hash=url;
//                        if(url=="/index"||url=="/"){
//                            url="/home";
//                        };
                        var iframe=$("#content-main").find("[data-id='"+url+"']");
                        $(".nav-list li").removeClass("active");
                        $(this).parent("li").addClass("active");
                        if(iframe.length>0){
                            iframe.css("display","inline");
                            currentIframe=iframe[0];
                        }else{
                            var index=$(this).attr("data-index");
                            var ihtml='<iframe class="J_iframe" name="iframe'+index+'" width="100%" height="100%" src="'+url+'" frameborder="0" data-id="'+url+'" seamless></iframe>';
                            $("#content-main").append(ihtml);
                            currentIframe=$("#content-main").find("[data-id='"+url+"']")[0];
                        }
                        var tab=$(".page-tabs-content").find("[data-id='"+url+"']");
                        $(".page-tabs-content a").removeClass("active");
                        if(tab.length > 0){
                            tab.addClass("active");
                        }else{
                            $(".page-tabs-content").append('<a href="javascript:;" class="J_menuTab active" data-id="'+url+'">'+$(this).text()+'<i class="fa fa-times-circle"></i></a>');
                            tabsEventClear();
                            tabsEventInit();
                        }
                    }
                });
            }

            /**
             * tabs选项卡的单击事件处理函数<br/>
             */
            function tabsEventInit(){
                $(".page-tabs-content a").bind("click",function(){
                    if($(this).hasClass("active")==false){
                        $(".page-tabs-content a").removeClass("active");
                        $(this).addClass("active");
                        var menu=$('[url="'+($(this).attr("data-id")=='/home'?'/':$(this).attr("data-id"))+'"]');
                        menu.click();
                        $(".nav-list li").removeClass("active");
                        menu.parent("li").addClass("active");
                        $(".nav-list li").removeClass("open");
                        menu.parent("li").parents("li").addClass("open");
                        menu.parent("ul").css("display","block");
                    }
                });
                $(".page-tabs-content .fa-times-circle").bind("click",function(){
                    if($(this).parent("a").hasClass("active")){
                        var nextnode=$(this).parent("a").next();
                        $(".page-tabs-content a").removeClass("active");
                        if(nextnode.length>0){
                            nextnode.addClass("active");
                        }else{
                            nextnode=$(this).parent("a").prev();
                        }
                        nextnode.addClass("active");
                        var menu=$('[url="'+(nextnode.attr("data-id")=='/home'?'/':nextnode.attr("data-id"))+'"]');
                        menu.click();
                        $(".nav-list li").removeClass("active");
                        menu.parent("li").addClass("active");
                        $(".nav-list li").removeClass("open");
                        menu.parent("li").parents("li").addClass("open");
                        menu.parent("ul").css("display","block");
                    }
                    var iframe=$("#content-main").find("[data-id='"+$(this).parent("a").attr("data-id")+"']");
                    iframe.remove();
                    $(this).parent("a").remove();
                });
            }

            function tabsEventClear(){
                $(".page-tabs-content a").unbind("click");
                $(".page-tabs-content .fa-times-circle").unbind("click");
            }

            /*修改密码*/
            function initPwdSettingEvent(){
                $("#pwd-update").click(function(){
                    layer.open({
                        title:'修改密码',
                        type: 2,
                        area: ['600px', '430px'],
                        fix: false, //不固定
                        maxmin: true,
                        content: '${context}/admin/pwdSettings'
                    });
                });
            }
        </script>
    </body>
</html>
