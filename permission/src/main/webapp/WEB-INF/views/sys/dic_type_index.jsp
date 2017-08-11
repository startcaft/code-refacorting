<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/pk-tag" prefix="pk"%>
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
</head>
<body>
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
                                                <input type="text" id="keyword" name="keyword" class="form-control search-query" placeholder="请输入关键字" />
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
                                <button class="btn btn-primary" id="btn-add" type="button">添加</button>
                                <button class="btn btn-info" id="btn-edit" type="button">编辑</button>
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
                </div>
            </div>
        </div>
    </div><!-- /.main-container -->

    <!-- basic scripts -->
    <jsp:include page="/WEB-INF/basejs.jsp" flush="true" />
    <script type="text/javascript">
        $(function(){
            var grid_selector = "#grid-table";
            var pager_selector = "#grid-pager";

            /*响应式的表格，固定代码*/
            /* resize to fit page size */
            $(window).on('resize.jqGird',function(){
                $(grid_selector).jqGrid( 'setGridWidth', $(".page-content").width() );
            });

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

            $('#grid-table').jqGrid({
                url:'${context}/admin/dics/types',
                mtype: "GET",
                datatype: "json",
                colModel: [
                    { label: 'ID', name: 'id', key: true,width: 75,align: 'center'},
                    { label: '字典类型', name: 'name',width: 75,align: 'center' },
                    { label: '类名描述', name: 'remark', width: 250,align: 'center' },
                    { label: '可供操作', name: 'id',formatter:fmatterOperation, width:120,align: 'center',sortable: false}
                ],
                viewrecords: true,
                height: 280,
                rowNum: 20,
                sortname:"id",
                sortorder:"desc",
                multiselect: true,//checkbox多选
                altRows: true,//隔行变色
                recordtext:"{0} - {1} 共 {2} 条",
                pgtext:"第 {0} 页 共 {1} 页",
                pager: pager_selector,
                loadComplete : function() {
                    var table = this;
                    setTimeout(function(){
                        updatePagerIcons(table);
                    }, 0);
                }
            });
            $(window).triggerHandler('resize.jqGrid');


        });

        $("#btn-add").click(function(){//添加字典类别
            layer.open({
                title:'添加字典类别',
                type: 2,
                area: ['370px', '430px'],
                fix: false, //不固定
                maxmin: true,
                content: '${context}/admin/dics/add'
            });
        });
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
        function fmatterOperation(cellvalue, options, rowObject){
            return '<button class="btn btn-primary btn-sm" onclick="to_dictdata_list('+cellvalue+')">查看数据列表</button>';
        }
        function reloadGrid(){
            $("#grid-table").trigger("reloadGrid"); //重新载入
        }
    </script>
</body>
</html>
