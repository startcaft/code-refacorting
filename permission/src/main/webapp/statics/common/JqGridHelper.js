/**
 * Created by startcaft on 2017/8/15.
 */

var jqGridHelper = function($){

    //模块API定义

    /**
     * 返回-1表示没有选中任何行；
     * 返回-2表示选中了多行；
     */
    var selectSingeRow = function (tableId) {
        var grid = $('#' + tableId);
        var rowKey = grid.getGridParam('selrow');
        if (!rowKey){//没有选中任何行
            return "-1";
        }else {
            var selectedIDs = grid.getGridParam('selarrrow');
            var result = "";
            if(selectedIDs.length==1){
                return selectedIDs[0];
            }else{//选中的行超过1
                return "-2";
            }
        }
    };
    
    var reload = function (tableId) {
        $('#' + tableId).trigger("reloadGrid"); //重新载入
    }

    //返回模块对象
    return {
        selectSingeRow : selectSingeRow,
        reload : reload
    };

}