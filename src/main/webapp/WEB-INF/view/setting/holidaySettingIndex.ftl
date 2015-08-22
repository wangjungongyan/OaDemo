<!DOCTYPE html>
<html>
<head>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/font-awesome.min.css" rel="stylesheet">
    <link href="/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <style>
        .form-horizontal .control-label {
            float: left;
            width: 80px;
            padding-top: 5px;
            text-align: right;
        }

        .modal {
            position: fixed;
            left: 50%;
            z-index: 1050;
            margin-left: -280px;
            background-color: #fff;
            border-radius: 6px;
            outline: 0;
            width: auto;
            box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);
            background-clip: padding-box;
        }

        .modal.fade.in {
            top: 10%;
            margin-left: -350px;
        }

    </style>
</head>
<body>

<div class="container">

    <form class="form-inline" id="queryForm" action="/sys/holidaySettingIndex">
        <table>
            <tr>
                <div class="form-group">

                    <label>年份</label>
                    <input name="year" value="${year}" type="text" class="form_datetime" style="width: 150px;">
                    &nbsp;&nbsp;&nbsp;
                    <button name="queryButton" type="submit" class="btn btn-default">查询</button>
                    &nbsp;&nbsp;&nbsp;
                    <a href="javascript:void(0)" name="showApplyDetail"
                       data-toggle="modal" data-target="#dialog_id_add" onclick="openInitDailog()">添加法定假日日期</a>
                </div>
            </tr>
        </table>
    </form>

    <table class="table  table-striped table-bordered table-hover ">
        <thead>
        <tr style="background-color: #eee">
            <th>年份</th>
            <th>相关节日</th>
            <th>日期</th>
            <th>是否工作日</th>
            <th>备注</th>
            <th>删除</th>
        </tr>
        </thead>
        <tbody>
            <#list holidaySettingDTOList as holiday>
            <tr>
                <td>${holiday.year}</td>
                <td>${holiday.day?string('yyyy-MM-dd')}</td>
                <td>${holiday.holiday}</td>
                <td>${holiday.mark}</td>
                <td>${holiday.isWorkDayStr}</td>
                <td><a href="javascript:void(0);" onclick="deleteHolidaySetting(${holiday.id})">删除</a></td>
            </tr>
            </#list>
        </tbody>
    </table>
</div>
<div class="modal fade" id="dialog_id_add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" style="display: none">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 500px;">
            <div class="modal-header">
                <button type="button" name="closeButton" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加假日设置</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <table class="table .table-condensed table-striped ">
                        <tr>
                            <td><label class="col-sm-4 control-label" style="width:150px;">日期</label></td>
                            <td>
                                <div class="col-sm-4">
                                    <input name="day" id="add-day"  required="true" type="text" class="form_datetime" style="width: 150px;">
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-4 control-label" style="width:150px;">是否是工作日</label></td>
                            <td>
                                <div class="col-sm-4">
                                    <input type="radio" value="1" name="isWorkDay" required="true">是
                                    <input type="radio" value="0" name="isWorkDay" required="true">否
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label class="col-sm-4 control-label" style="width:150px;">
                                    节假日
                                </label>
                            </td>
                            <td>
                                <div class="col-sm-4">
                                    <input class="form-control" name="holiday" id="holiday" />
                                </div>
                            </td>
                        </tr>
                        
                        <tr>
                            <td>
                                <label class="col-sm-4 control-label" style="width:150px;">
                                    备注
                                </label>
                            </td>
                            <td>
                                <label class="col-sm-4 control-label">
                                    <input class="form-control" name="mark" id="mark" />
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" style="text-align: center">
                                <button type="button"  class="btn btn-default" id="addHolidayBtn">添加
                                </button>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="/js/jquery-1.8.2.min.js"></script>
<script src="/js/jquery.json.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/bootstrap-datetimepicker.min.js"></script>
<script>
    $("input[name='year']").datetimepicker({
        format: 'yyyy',
        minView: 4,
        startView:4,
        todayHighlight: true,
        todayBtn: true,
        autoclose: true
    });
    $("#add-day").datetimepicker({
        format: 'yyyy-mm-dd',
        minView: 2,
        startView:2,
        todayHighlight: true,
        todayBtn: true,
        autoclose: true
    });
    var checkIsEmpty = function(str){
        if(str==null || str==''){
            return true;
        }
        return false;
    }
    var openInitDailog = function(){
        $("#add-day").val("");
        $("#holiday").val("");
        $("#mark").val("");
        $('input[name="isWorkDay"]:checked').removeAttr("checked");
    }
    $("#addHolidayBtn").click(function(){
        var day = $("#add-day").val();
        var holiday = $("#holiday").val();
        var mark = $("#mark").val();
        var isWorkDay = $('input[name="isWorkDay"]:checked').val();
        var url = "/sys/saveHolidaySetting";
        var data = {};
        data.day = day;
        data.holiday = holiday;
        data.mark = mark;
        data.isWorkDay = isWorkDay;
        if(checkIsEmpty(data.day)){
            alert("请选择日期");
            return;
        }
        if(checkIsEmpty(data.holiday)){
            alert("请填写节日");
            return;
        }
        if(checkIsEmpty(data.mark)){
            alert("请填写备注");
            return;
        }
        if(checkIsEmpty(data.isWorkDay)){
            alert("请选择是否工作日");
            return;
        }

        $.ajax(url, {
            dataType: "json",
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            type: "post",
            data: data,
            success: function (result) {
                if(result.success){
                    location.reload();
                    $("#dialog_id_add").modal('hide');
                }else{
                    alert(result.errorMsg);
                }

            },
            error: function () {
                $("#dialog_id_add").modal('hide');
            }
        });



    });
    var deleteHolidaySetting =function(id){

        var url = "/sys/deleteHolidaySetting";
        $.ajax(url, {
            dataType: "json",
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            type: "post",
            data: {"id":id},
            success: function (result) {
                if(result.success){
                    location.reload();
                }else{
                    alert(result.errorMsg);
                }

            },
            error: function () {
                alert("请重试");
            }
        });
    }
</script>
</body>


</html>
