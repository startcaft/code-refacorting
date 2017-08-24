<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <style>
        body{
            background-color: #ffffff;
        }
        .left{
            position: absolute;
            top: 10px;
            left: 20px;
            width: 358px;
            border-right: 1px solid #CCC;
            overflow-y: auto;
        }
        .tree-menu{
            float: right;
        }
        .tree-menu span{
            margin-left: 6px;
        }
        .tree-menu span i{
            cursor: pointer;
        }
        .icon-plus {
            background-position: -408px -96px;
        }
        .icon-remove {
            background-position: -312px 0;
        }
        .icon-edit {
            background-position: -96px -72px;
        }
        [class^="icon-"], [class*=" icon-"] {
            display: inline-block;
            width: 14px;
            height: 14px;
            line-height: 14px;
            vertical-align: text-top;
            background-image: url("${basePath}res/bootstrap/img/glyphicons-halflings.png");
            background-repeat: no-repeat;
            margin-top: 1px;
        }
        #menu_tree{
            margin-right: 20px;
        }

        li{
            line-height: 16px;
        }
        .om-tree-node a{
            display: inline-block;
            *display: inline;
            *zoom: 1;
            width: 115px;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        #vip_tip{
            text-align: center;
        }
        .actions{
            position: absolute;
            bottom: 20px;
            left: 10px;
            width: 368px;
            border-right: 1px solid #CCC;
            height: 60px;
        }
        .ztree li span.button.add {margin-left:15px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
    </style>
</head>
<body>

    <div class="left">
        <ul id="resTree" class="ztree"></ul>
    </div>
    <div class="actions" style="margin-left: 300px;margin-top: 30px;">
        <button class="btn btn-big btn-primar" id="btn_saveOrder">保存</button>
    </div>

    <!-- basic scripts -->
    <jsp:include page="/WEB-INF/basejs.jsp" flush="true" />
    <script src="${context}/statics/js/ztree/js/jquery.ztree.core-3.5.min.js"></script>
    <script src="${context}/statics/js/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
    <script src="${context}/statics/js/ztree/js/jquery.ztree.exedit-3.5.min.js"></script>
    <script type="text/javascript">
        $(function () {
            initResTree();
            resize();

            $(window).resize(function(){
                resize();
            });
        })

        function resize(){
            var h = $(window).height(),
                th = $("#top").outerHeight(true),
                mh = $(".main-title h3").outerHeight(true);
            $(".left").height(h - th - mh- 55);
        }

        function initResTree(){
            var treeNodes;
            $.get('${context}/admin/resources/roleAll?roleId=${roleId}',null,function(data,status,xhr){
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

        //zTree的设置
        var setting = {
            check: {
                enable: true
            },
            data: {
                simpleData: {//简单数据模式
                    enable:true,
                    idKey: "id",
                    pIdKey: "pid",
                    rootPId: ""
                }
            },
            callback: {
                beforeCheck: beforeCheck
            }
        };
//        setting.check.chkboxType = { "Y" : "ps", "N" : "ps" };//父子关联关系
        
        function beforeCheck(treeId, treeNode) {
//            console.log(treeId);
        }

        //授权
        $("#btn_saveOrder").click(function(){
            $("#btn_saveOrder").attr("disabled","disabled");
            var nodes = $.fn.zTree.getZTreeObj("resTree").getCheckedNodes(true);
            var selectIds="";
            for(var index in nodes){
                var item=nodes[index];
                selectIds+=item.id+",";
            }
            var submitData={
                "resIds":selectIds,
                "roleId":"${roleId}",
            }
            $.post("${context}/admin/roles/grant" , submitData ,
                function(data){
                    if(data.success){
                        layer.msg("操作成功", {
                            icon: 1,
                            time: 1000 //1秒关闭（如果不配置，默认是3秒）
                        });
                    }
                    else {
                        layer.msg(data.tipInfo);
                    }
                    $("#btn_saveOrder").removeAttr("disabled");
                },"json");
        });
    </script>
</body>
</html>
