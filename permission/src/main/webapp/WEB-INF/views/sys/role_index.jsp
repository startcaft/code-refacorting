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
                        <div class="widget-box">
                            <div class="widget-header widget-header-small">
                                <h5 class="widget-title lighter">筛选</h5>
                            </div>
                            <div class="widget-body">
                                <div class="widget-main">
                                    <div class="row">
                                        <div class="col-xs-12 col-sm-8">
                                            <div class="input-group">
                                                <span class="input-group-addon">
                                                    <i class="ace-icon fa fa-check"></i>
                                                </span>
                                                <input type="text" id="name" name="name" class="form-control search-query" placeholder="请输入关键字" />
                                                <span class="input-group-btn">
                                                    <button type="button" id="btn_search" class="btn btn-purple btn-sm">
                                                        <span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
                                                        搜索
                                                    </button>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12">
                        <div class="row-fluid" style="margin-bottom: 5px;">
                            <div class="span12 control-group">
                                <shiro:hasPermission name="/admin/role/add">
                                    <button class="btn btn-primary" id="btn-add">添加</button>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="/admin/role/edit">
                                    <button class="btn btn-info" id="btn-edit">编辑</button>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="/admin/role/grant">
                                    <button class="btn" id="btn-grant">授权</button>
                                </shiro:hasPermission>
                            </div>
                        </div>
                        <!-- PAGE CONTENT BEGINS -->
                        <table id="grid-table"></table>

                        <div id="grid-pager"></div>
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
        $(function () {
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
                url:'${context}/admin/roles/all',
                mtype: "GET",
                datatype: "json",
                colModel: [
                    { label: '角色ID', name: 'id', key: true, hidden:true },
                    { label: '角色名称', name: 'name', width: 150, align: 'center' },
                    { label: '描述', name: 'description', width: 150 ,sortable:false, align: 'center'}
                ],
                viewrecords: true,
                height: 'auto',
                rowNum: 10,
                rowList:[10,20,30],
                multiselect: false,//checkbox多选
                altRows: true,//隔行变色
                recordtext:"{0} - {1} 共 {2} 条",
                pgtext:"第 {0} 页 共 {1} 页",
                pager: pager_selector,
                loadComplete : function() {
                    var table = this;
                    setTimeout(function(){
                        updatePagerIcons(table);
                    }, 0);
                },
                onSelectRow : function( rowid ) {
                    if(rowid) {
                        selectRowid=rowid;
                    }
                },
            });
            $("#grid-table").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
            $(window).triggerHandler('resize.jqGrid');

            //添加
            $("#btn-add").click(function(){
                layer.open({
                    title:'添加角色',
                    type: 2,
                    area: ['370px', '430px'],
                    fix: false, //不固定
                    maxmin: true,
                    content: '${context}/admin/roles/add'
                });
            });
            //查询
            $("#btn_search").click(function(){
                //此处可以添加对查询数据的合法验证
                var name = $("#name").val();
                $("#grid-table").jqGrid('setGridParam',{
                    datatype:'json',
                    postData:{'roleName':name}, //发送数据
                    page:1
                }).trigger("reloadGrid"); //重新载入
            });
            //授权
            $("#btn-grant").click(function(){
                var rData = selectRows();
                var id = rData.id;
                if (typeof(id) == 'undefined'){
                    layer.msg('请选择要操作的资源');
                    return;
                }

                layer.open({
                    title:'给角色【'+rData.name+'】分配资源',
                    type: 2,
                    area: ['380px', '430px'],
                    fix: false, //不固定
                    maxmin: true,
                    content: '${context}/admin/roles/grant?roleId='+id
                });
            });
            //编辑
            $("#btn-edit").click(function(){
                var rData = selectRows();
                var id = rData.id;
                if (typeof(id) == 'undefined'){
                    layer.msg('请选择要操作的资源');
                    return;
                }
                layer.open({
                    title:'修改角色',
                    type: 2,
                    area: ['370px', '430px'],
                    fix: false, //不固定
                    maxmin: true,
                    content: '${context}/admin/roles/add?roleId='+id
                });
            });
        })

        //获取选中的行
        function selectRows() {
            var rData = $('#grid-table').jqGrid('getRowData',selectRowid);
            return rData;
        }

        function reloadGrid(){
            $("#grid-table").trigger("reloadGrid"); //重新载入
        }

        //replace icons with FontAwesome icons like above
        function updatePagerIcons(table) {
            var replacement =
                {
                    'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
                    'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
                    'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
                    'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
                };
            $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
                var icon = $(this);
                var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
                if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
            })
        }
    </script>
</body>
</html>
