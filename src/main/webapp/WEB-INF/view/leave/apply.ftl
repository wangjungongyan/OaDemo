<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/font-awesome.min.css" rel="stylesheet">
    <link href="/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <style type="text/css">
        .form-horizontal .control-label {
            padding-top: 7px;
            margin-bottom: 0;
            text-align: left;
            margin-left:50px
        }

        .form-horizontal input, .form-search textarea, .form-inline textarea, .form-horizontal textarea, .form-search select, .form-inline select, .form-horizontal select, .form-search .help-inline, .form-inline .help-inline, .form-horizontal .help-inline, .form-search .uneditable-input, .form-inline .uneditable-input, .form-horizontal .uneditable-input, .form-search .input-prepend, .form-inline .input-prepend, .form-horizontal .input-prepend, .form-search .input-append, .form-inline .input-append, .form-horizontal .input-append {
            display: inline-block;
            margin-bottom: 0;
            vertical-align: middle;
            margin-left: -300px;
        }
    </style>
</head>

<body>

<div style="margin-top:10px;margin-left:10px;visibility:visible">
    <form class="form-horizontal form-inline" action="/leave/apply" method="post">
        <div class="col-sm-10">
            <table class="table .table-condensed table-striped ">
                <tr>
                    <td colspan="4"><label id="cishu" style="margin-left: 450px;" class="col-sm-40 control-label">请 假 申 请 详 细 信 息</label></td>
                </tr>
                <tr>
                    <td><label class="col-sm-8 control-label">申请人</label></td>
                    <td>
                        <div class="col-sm-3">
                            <input class="form-control" readonly placeholder="${employee.chineseName}">
                        </div>
                    </td>
                    <td><label class="col-sm-8 control-label">经 理</label></td>
                    <td>
                        <div class="col-sm-3">
                            <input class="form-control" readonly placeholder="${employee.manger.chineseName}">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label class="col-sm-8 control-label">
                            <span style="color:#F00">*</span>请假类型
                        </label>
                    </td>
                    <td>
                        <div class="col-sm-3">
                            <select name ="leaveType" id="leaveType">
                                <#list employeeHolidays as holiday>
                                    <option value ="${holiday.type}">【${holiday.name}】 已用${holiday.used}天 剩余${holiday.surplus}天</option>
                                </#list>
                            </select>
                        </div>
                    </td>
                    <td><label class="col-sm-8 control-label">HR</label></td>
                    <td>
                        <div class="col-sm-3">
                            <input class="form-control" readonly placeholder="${employee.hr.chineseName}">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label class="col-sm-8 control-label">
                            <span style="color:#F00">*</span>开始时间
                        </label>
                    </td>
                    <td>
                        <div class="col-sm-3">
                            <input class="form-control" id="leaveStartTime" name="leaveStartTime" placeholder="" required="required" />
                        </div>
                    </td>
                    <td>
                        <label class="col-sm-8 control-label">
                            <span style="color:#F00">*</span>结束时间
                        </label>
                    </td>
                    <td>
                        <div class="col-sm-3">
                            <input class="form-control" id="leaveEndTime" name="leaveEndTime" required="required">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label class="col-sm-8 control-label">
                            <span style="color:#F00">*</span>请假事由
                        </label>
                    </td>
                    <td>
                        <div class="col-sm-3">
                                <textarea class="form-control" rows="3" cols="20" name="leaveReason" required="required" maxlength="100"></textarea>
                        </div>
                    </td>
                    <td><label class="col-sm-8 control-label">请假类型描述</label></td>
                    <td>
                        <div class="col-sm-3">
                            <textarea class="form-control" rows="3" cols="20" name="leaveDesc" readonly>${employeeHolidays[0].desc}</textarea>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">
                        <p style="margin-left: 450px;">
                            <a href="/leave/apply">
                            <button type="submit" class="btn btn-info">
                                提 交 请 假 申 请
                            </button>
                            </a>
                        </p>
                    </td>
                </tr>

            </table>
        </div>
    </form>

    <#list employeeHolidays as holiday>
        <input type="hidden" id="holidayDesc${holiday_index+1}" type="${holiday.type}" desc="${holiday.desc}">
    </#list>

</div>
</body>

<script src="/js/jquery-1.8.2.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/bootstrap-datetimepicker.min.js"></script>

<script type="text/javascript">

    $("#leaveStartTime").datetimepicker({
        format: 'yyyy-mm-dd hh:ii',
        minView:0,
        todayHighlight:true,
        todayBtn:true,
        autoclose:true
    });

    $("#leaveEndTime").datetimepicker({
        format: 'yyyy-mm-dd hh:ii',
        minView:0,
        todayHighlight:true,
        todayBtn:true,
        autoclose:true
    });

    $(document).ready(function(){

        $("#leaveType").change(function(){
            var type = $("#leaveType").find("option:selected").attr("value");
            var descId = "#holidayDesc" + type;
            var desc = $(descId).attr("desc");
            $("textarea[name='leaveDesc']").text(desc);
        })

        $("#leaveEndTime").change(function(){
            var leaveStartTime = $("#leaveStartTime").val();
            var leaveEndTime = $("#leaveEndTime").val();
            if(leaveEndTime <= leaveStartTime){
                $("#leaveEndTime").val("");
                alert("假期结束时间必须大于开始时间.");
            }
        })
    });

</script>

</html>
