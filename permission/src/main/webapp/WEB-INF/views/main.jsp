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
    </div>
</body>
</html>
