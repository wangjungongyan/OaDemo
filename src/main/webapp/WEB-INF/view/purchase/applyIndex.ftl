<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta http-equiv=”content-type” content=”text/html; charset=UTF-8″/>
    <link href="/bootstrap3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/font-awesome.min.css" rel="stylesheet">
    <link href="/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
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
                <div class="col-md-2 form-label">申请人:</div>
                <div class="col-md-2 form-input"><input class="form-control" readonly  value="${employee.chineseName}" style="width:130px;"></div>
                <div class="col-md-1 form-label">部门:</div>
                <div class="col-md-3 form-input"><input class="form-control" readonly  value="${employee.deptName}"  style="width:180px;"></div>
                <div class="col-md-1 form-label">时间:</div>
                <div class="col-md-3 form-input"><input class="form-control" readonly  value="${today?string("yyyy-MM-dd")}"  style="width:130px;"></div>
            </div>
            <div class="row">
                <div class="col-md-2 form-label">收款人姓名:</div>
                <div class="col-md-10 form-input"><input class="form-control" required="请填写收款人姓名"  style="width: 200px;"></div>
            </div>
            <div class="row">
                <div class="col-md-2 form-label">收款人银行名称:</div>
                <div class="col-md-10 form-input"><input class="form-control"  required="请填写收款人银行名称"  style="width: 200px;"></div>

            </div>
            <div class="row">
                <div class="col-md-2 form-label">收款人银行帐号:</div>
                <div class="col-md-10 form-input"><input class="form-control"  required="请填写银行帐号" style="width: 200px;"></div>
            </div>
            <div class="row">
                <div class="col-md-2 form-label">币种:</div>
                <div class="col-md-5 form-input" style="padding-bottom: 10px;padding-top: 10px;">
                    <input type="radio" value="人民币" name="moneyType" required="请选择币种">人民币
                    <input type="radio" value="美元" name="moneyType" required="请选择币种">美元
                    <input type="radio" value="其它" name="moneyType" required="请选择币种">其它
                </div>
                <div class="col-md-2 form-label">要求付款日期:</div>
                <div class="col-md-3 form-input">
                    <input class="form-control" id="payMentType" name="payMentType"  required="请选择时间"/>
                </div>
            </div>
            <div class="row" style="border-left: 1px solid #ccc;">
                <div class="col-md-2 form-label" style="border: 0">具体付款内容:</div>
                <div class="col-md-10 form-input">
                    <textarea rows="5" cols="100" required="请填写付款内容"></textarea></div>
            </div>
            <div class="row">
                <div class="col-md-12 form-label">
                    <button style="margin-left: 100px;">提交主管审批</button>
                </div>
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

    $("#payMentType").datetimepicker({
        format: 'yyyy-mm-dd',
        minView: 2,
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
</script>

</html>
