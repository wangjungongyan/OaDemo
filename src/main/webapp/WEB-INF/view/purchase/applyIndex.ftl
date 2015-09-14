<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta http-equiv=”content-type” content=”text/html; charset=UTF-8″/>
    <link href="/bootstrap3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        .form-title{text-align: center;
            border: 1px solid #ddd;
            background-color: #eee;
            padding-top: 10px;
            padding-bottom: 10px;}
        .form-label{
            border: 1px solid #ddd;
            padding-top: 10px;
            padding-bottom: 10px;
        }
        .form-input{
            border: 1px solid #ddd;
            padding-top: 3px;
            padding-bottom: 3px;
        }
    </style>
</head>

<body>
<div style="margin-top:10px;margin-left:10px;visibility:visible">
    <form class="form-horizontal form-inline" action="/leave/apply" method="post">
        <div class="container">
            <div class="row form-title" >
                <div class="col-md-12">请购申请</div>
            </div>
            <div class="row">
                <div class="col-md-2 form-label">申请人</div>
                <div class="col-md-2 form-input"><input class="form-control" readonly placeholder="${employee.chineseName}"></div>
                <div class="col-md-2 form-label">部门</div>
                <div class="col-md-2 form-input"><input class="form-control" readonly placeholder="${employee.deptName}"></div>
                <div class="col-md-2 form-label">时间</div>
                <div class="col-md-2 form-input"><input class="form-control" readonly placeholder="${employee.deptName}"></div>
            </div>
            <div class="row">
                <div class="col-md-2 form-label">收款人姓名:</div>
                <div class="col-md-10 form-input"><input class="form-control" readonly placeholder="${employee.chineseName}"></div>
            </div>
            <div class="row">
                <div class="col-md-2 form-label">收款人银行名称:</div>
                <div class="col-md-10 form-input"><input class="form-control" readonly placeholder="${employee.deptName}"></div>

            </div>
            <div class="row">
                <div class="col-md-2 form-label">收款人银行帐号:</div>
                <div class="col-md-10 form-input"><input class="form-control" readonly placeholder="${employee.deptName}"></div>

            </div>
        </div>
    </form>

<#list employeeHolidays as holiday>
    <input type="hidden" id="holidayDesc${holiday_index+1}" type="${holiday.type}" desc="${holiday.desc}" surplus="${holiday.surplus}" own="${holiday.own}">
</#list>

</div>
</body>

<script src="/js/jquery-1.8.2.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/bootstrap-datetimepicker.min.js"></script>

<script type="text/javascript">

    $("#leaveStartTime").datetimepicker({
        format: 'yyyy-mm-dd hh',
        minView: 0,
        todayHighlight: true,
        todayBtn: true,
        autoclose: true
    });

    $("#leaveEndTime").datetimepicker({
        format: 'yyyy-mm-dd hh',
        minView: 0,
        todayHighlight: true,
        todayBtn: true,
        autoclose: true
    });

    $(document).ready(function () {

        $("#leaveType").change(function () {
            var obj = $(this).find("option:selected");
            var type = obj.attr("value");
            var descId = "#holidayDesc" + type;
            var desc = $(descId).attr("desc");
            var own = $(descId).attr("own");
            var surplus = $(descId).attr("surplus");
            $("textarea[name='leaveDesc']").text(desc);
            var leaveDeatil= "共" + own + "天，余" + surplus + "天";
            $("#leaveDeatil").text(leaveDeatil);
        })

        $("#leaveEndTime").change(function () {
            var leaveStartTime = $("#leaveStartTime").val();
            var leaveEndTime = $("#leaveEndTime").val();
            if (leaveEndTime <= leaveStartTime) {
                $("#leaveEndTime").val("");
                alert("假期结束时间必须大于开始时间.");
            }
        })
    });

</script>

</html>
