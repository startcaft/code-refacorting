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
</head>
<body class="no-skin">
    <!-- /section:basics/navbar.layout -->
    <div class="main-container" id="main-container">
        <script type="text/javascript">
            try{ace.settings.check('main-container' , 'fixed')}catch(e){}
        </script>
        <div class="main-content" id="page-wrapper">
            <div class="page-content" id="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                    </div>
                    <div class="col-xs-12">
                        <div class="row-fluid" style="margin-bottom: 5px;">
                            <div class="span12 control-group">
                                <button class="btn btn-success" onclick="setVisible(100)">启用</button>
                                <button class="btn btn-danger" onclick="setVisible(200)">禁用</button>
                                <button class="btn btn-primary" id="btn-add">添加</button>
                                <button class="btn btn-info" id="btn-edit">编辑</button>
                                <button class="btn" id="btn-grant">授权</button>
                            </div>
                        </div>
                        <!-- PAGE CONTENT BEGINS -->
                        <table id="grid-table"></table>

                        <div id="grid-pager"></div>

                        <script type="text/javascript">
                            var $path_base = "..";//in Ace demo this will be used for editurl parameter
                        </script>

                        <!-- PAGE CONTENT ENDS -->
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div>
        </div>
    </div><!-- /.main-container -->

    <!-- basic scripts -->
    <jsp:include page="/WEB-INF/basejs.jsp" flush="true" />
    <script type="text/javascript">
        var selectRowid=-1;
        $(function(){
            var grid_selector = "#grid-table";
            var pager_selector = "#grid-pager";

            /*响应式的表格，固定代码*/
            /*make the grid responsive */
            var parent_column = $(grid_selector).closest('[class*="col-"]');
            $(window).on('resize.jqGrid', function () {
                $(grid_selector).jqGrid( 'setGridWidth', $(".page-content").width() );
            })
            //optional: resize on sidebar collapse/expand and container fixed/unfixed
            $(document).on('settings.ace.jqGrid' , function(ev, event_name, collapsed) {
                if( event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed' ) {
                    $(grid_selector).jqGrid( 'setGridWidth', parent_column.width() );
                }
            })

            $("#grid-table").jqGrid({
                url:"${context}/admin/resources/all",
                datatype:"json",
                colModel:[
                    {
                        name:"id",
                        index:"id",
                        sorttype:"int",
                        key:true,
                        hidden:true
                    },{
                        name:"name",
                        index:"name",
                        sorttype:"string",
                        label:"资源名称",
                        width:120,sortable:false
                    },{
                        name:"url",
                        index:"url",
                        sorttype:"string",
                        label:"资源地址",
                        align:'center',
                        width:90,sortable:false
                    }, {
                        name: 'resTypeCode',
                        label: '资源类型',
                        align:'center',
                        formatter:fmatterType,
                        width:50,
                        sortable:false
                    }, {
                        name: 'seq',
                        label: '排序号',
                        align:'center',
                        width:50,
                        sortable:false
                    }, {
                        name:"pid",
                        hidden:true
                    },{
                        name:"statesCode",
                        label:'是否启用',
                        align:'center',
                        width:50,
                        formatter:fmatterEnabled,
                        sortable:false
                    }
                ],
                height: 'auto',
                hoverrows:true,
                viewrecords:false,
                gridview:true,
                rowNum : "-1",     // 显示全部记录
                pager: "false",
                sortname: 'seq',
                sortorder: "desc",
                scrollrows:true,
                multiselect: true,//checkbox多选
                treeGrid:true,
                ExpandColumn:"name",
                treedatatype:"json",
                treeGridModel:"adjacency",
                loadonce:true,
                treeReader:{
                    parent_id_field:"pid",
                    level_field:"level",
                    leaf_field:"isLeaf",
                    expanded_field:"expanded",
                    loaded:"loaded",
                    icon_field:"icon"
                },
                onSelectRow : function( rowid ) {
                    if(rowid) {
                        selectRowid=rowid;
                    }
                },
            });
            $("#grid-table").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
            $(window).triggerHandler('resize.jqGrid');

            //添加资源
            $("#btn-add").click(function(){
                layer.open({
                    type: 2,
                    fix: false,
                    title: '添加资源',
                    maxmin: true,
                    content: '${context}/admin/resources/edit',
                    area: ['770px', '520px']
                });
            });
        });

        //获取选中的行
        function selectRows() {
            var rData = $('#grid-table').jqGrid('getRowData',selectRowid);
            return rData;
        }

        function setVisible(statesCode){
            var rData = selectRows();
            var id = rData.id;
            if (typeof(id) == 'undefined'){
                layer.msg('请选择要操作的资源');
                return;
            }
            var submitData = {
                "id":id,
                "statesCode":statesCode
            };

            $.post("${context}/admin/resources/modify", submitData,function(data) {
                if (data.success) {
                    layer.msg('操作成功', {
                        icon: 1,
                        time: 2000 //2秒关闭（如果不配置，默认是3秒）
                    },function(){
                        $("#grid-table").trigger("reloadGrid"); //重新载入
                    });
                }else{
                    layer.msg(data.tipInfo);
                }
            },"json");
        }

        //格式化状态显示
        function fmatterType(cellvalue, options, rowObject){
            if(cellvalue==1){
                return '<span class="label label-sm label-info">系统菜单</span>';
            }else{
                return '<span class="label label-sm label-success">功能按钮</span>';
            }
        }
        //格式化状态显示
        function fmatterEnabled(cellvalue, options, rowObject){
            if(cellvalue==100){
                return '<span class="label label-sm label-success">启用</span>';
            }else{
                return '<span class="label label-sm label-warning">禁用</span>';
            }
        }
        function reloadGrid(){
            $("#grid-table").trigger("reloadGrid"); //重新载入
        }
    </script>
</body>
</html>
