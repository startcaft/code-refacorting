<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>后台管理系统</title>
    <meta name="description" content="overview &amp; stats" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <jsp:include page="/WEB-INF/basecss.jsp" flush="true" />
    <link rel="stylesheet" href="${context}/statics/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
</head>
<body class="no-skin">
    <div class="main-container" id="main-container">
        <script type="text/javascript">
            try{ace.settings.check('main-container' , 'fixed')}catch(e){}
        </script>

        <div class="main-container-inner">
            <div class="main-content" style="margin-left: 0px;">
                <div class="page-content">
                    <div class="row">
                        <div class="col-xs-12">
                            <!-- PAGE CONTENT BEGINS -->
                            <form class="form-horizontal" id="validation-form" method="post">
                                <div class="form-group">
                                    <input name="id" type="hidden" value="${res.id}"/>
                                    <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="pname">上级资源</label>
                                    <div class="col-xs-12 col-sm-9">
                                        <div class="input-icon input-icon-right" id="parentRes">
                                            <input type="hidden" id="pid" name="pid" value="${res.pid }">
                                            <input type="text" readonly="readonly" id="pname" name="pname" value="${res.pname}" class="col-xs-12 col-sm-6">
                                            <button id="menuBtn" type="button" class="btn btn-sm btn-default" onclick="showMenu(); return false;" style="float: left;">选择</button>
                                        </div>
                                        <div id="menuContent" class="menuContent" style="overflow-y:auto; overflow-x:auto;display:none;position:absolute; z-index: 99999;background-color: #FFFFFF;border: 1px solid #858585;height: 250px;width: 270px;">
                                            <ul id="resTree" class="ztree" ></ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">资源名称</label>
                                    <div class="col-xs-12 col-sm-9">
                                        <div class="clearfix">
                                            <input type="text" name="name" id="name" value="${res.name}" class="col-xs-12 col-sm-6">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="url">资源路径</label>
                                    <div class="col-xs-12 col-sm-9">
                                        <div class="clearfix">
                                            <input type="text" name="url" id="url" value="${sysRes.url}" class="col-xs-12 col-sm-6">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="resTypeCode">资源类型</label>
                                    <div class="col-xs-12 col-sm-9">
                                        <div class="clearfix">
                                            <select name="resTypeCode" id="resTypeCode" class="col-xs-12 col-sm-6">
                                                <option ${res.resTypeCode eq 1?'selected':''} value="1">菜单</option>
                                                <option ${res.resTypeCode eq 2?'selected':'' } value="2">功能</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="seq">排序号</label>
                                    <div class="col-xs-12 col-sm-9">
                                        <div class="clearfix">
                                            <input type="text" name="seq" id="seq" value="${res.seq}" class="col-xs-12 col-sm-6">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="icon">图标</label>
                                    <div class="col-xs-12 col-sm-9">
                                        <div class="clearfix">
                                            <input type="text" name="icon" id="icon" value="${res.icon}" class="col-xs-12 col-sm-6">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-12 col-sm-3 no-padding-right checkbox-inline" for="isRoot">是否一级菜单</label>
                                    <div class="col-xs-12 col-sm-9">
                                        <div class="clearfix">
                                            <input name="isRoot" id="isRoot" class="ace ace-switch" type="checkbox" />
                                            <span class="lbl"></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-12 col-sm-3 no-padding-right checkbox-inline" for="isPublic">是否公共资源</label>
                                    <div class="col-xs-12 col-sm-9">
                                        <div class="clearfix">
                                            <input name="isPublic" id="isPublic" class="ace ace-switch" type="checkbox" />
                                            <span class="lbl"></span>
                                        </div>
                                    </div>
                                </div>

                                <div class="clearfix form-actions" align="center">
                                    <div class="col-md-offset-3 col-md-9">
                                        <button id="submit-btn" class="btn btn-info" type="submit" data-last="Finish">
                                            <i class="ace-icon fa fa-check bigger-110"></i>
                                            提交
                                        </button>
                                        &nbsp; &nbsp; &nbsp;
                                        <button class="btn" type="reset">
                                            <i class="ace-icon fa fa-undo bigger-110"></i>
                                            重置
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div><!-- /.col -->
                    </div><!-- /.row -->
                </div><!-- /.page-content -->
            </div><!-- /.main-content -->
        </div><!-- /.main-container-inner -->
    </div><!-- /.main-container -->

    <jsp:include page="/WEB-INF/basejs.jsp" flush="true" />
    <script src="${context}/statics/js/ztree/js/jquery.ztree.core-3.5.min.js"></script>
    <script type="application/javascript">
        $(document).ready(function(){
            initformSubmitEvent();
            initResTree();

            $('#isRoot').change(function () {
                if ($(this).is(':checked')){
                    //一级菜单不需要上级资源和url
                    $('#pid').attr("disabled",true);
                    $('#pname').attr('disabled',true);
                    $('#url').attr('disabled',true);
                    $('#menuBtn').attr('disabled',true);
                    //一级菜单类型不能为功能
                    $("#resTypeCode option[value='1']").attr("selected",true);
                }
                else {
                    $('#pid').removeAttr("disabled");
                    $('#pname').removeAttr("disabled");
                    $('#url').removeAttr("disabled");
                    $('#menuBtn').removeAttr("disabled");
                }
            })
        });

        //zTree的设置
        var setting = {
            data: {
                simpleData: {//简单数据模式
                    enable:true,
                    idKey: "id",
                    pIdKey: "pid",
                    rootPId: ""
                }
            },
            callback: {
                onClick: onClick
            }
        };

        function initResTree(){
            var treeNodes;
            $.get('${context}/admin/resources/all',null,function(data,status,xhr){
                treeNodes = data;
                var treeRes = $('#resTree');
                treeRes = $.fn.zTree.init(treeRes, setting, treeNodes);
                treeRes.expandAll(true);//全部展开
            },'json');
        }
        function onClick(e, treeId, treeNode){
            var zTree = $.fn.zTree.getZTreeObj("resTree")
                ,nodes = zTree.getSelectedNodes();

            var pName = $("#pname");
            $("#pid").val(treeNode.id);
            pName.attr("value", nodes[0].name);

            e.preventDefault();//阻止冒泡
        }

        //显示资源菜单树
        function showMenu() {
            var cityObj = $("#pname");
            var cityOffset = $("#pname").offset();
            var sel=document.getElementById("parentRes");
            $("#menuContent").css({left:sel.offsetLeft + "px", top:sel.offsetTop + cityObj.outerHeight() + "px"}).slideDown("fast");
            $("body").bind("mousedown", onBodyDown);
        }
        function onBodyDown(event) {
            if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
                hideMenu();
            }
            if(event.target.id.length>=15&&event.target.id.length!=17){
                hideMenu();
            }
        }
        function hideMenu() {
            $("#menuContent").fadeOut("fast");
            $("body").unbind("mousedown", onBodyDown);
        }


        var $validation = true;
        function initformSubmitEvent(){
            $('#validation-form').validate({
                errorElement: 'div',
                errorClass: 'help-block',
                focusInvalid: false,
                rules: {
                    name:{
                        required: true
                    },
                    seq:{
                        required: true,
                        digits:true
                    }
                },
                messages: {
                    name:{
                        required: "请输入资源"
                    },
                    seq:{
                        required: "请输入排序号",
                        digits:"排序号只能输入整数"
                    }
                },
                highlight: function (e) {
                    $(e).closest('.form-group').removeClass('has-info').addClass('has-error');
                },

                success: function (e) {
                    $(e).closest('.form-group').removeClass('has-error');//.addClass('has-info');
                    $(e).remove();
                },

                errorPlacement: function (error, element) {
                    if(element.is(':checkbox') || element.is(':radio')) {
                        var controls = element.closest('div[class*="col-"]');
                        if(controls.find(':checkbox,:radio').length > 1) controls.append(error);
                        else error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
                    }
                    else if(element.is('.select2')) {
                        error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
                    }
                    else if(element.is('.chosen-select')) {
                        error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)'));
                    }
                    else error.insertAfter(element.parent());
                },

                submitHandler: function (form) {
                    var $form = $("#validation-form");
                    var $btn = $("#submit-btn");
                    if($btn.hasClass("disabled")) return;
                    $btn.addClass("disabled");
                    var postData=$("#validation-form").serialize();
                    $.post("${context}/admin/resources/save" ,postData,function(data){
                        if(data.success){
                            layer.msg('操作成功', {
                                icon: 1,
                                time: 2000 //2秒关闭（如果不配置，默认是3秒）
                            },function(){
                                parent.reloadGrid();
                                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                                parent.layer.close(index); //再执行关闭
                            });
                        }
                        else {
                            layer.msg(data.tipInfo);
                        }
                        $("#submit-btn").removeClass("disabled");
                    },"json");
                    return false;
                },
                invalidHandler: function (form) {
                }
            });
        }
    </script>
</body>
</html>
