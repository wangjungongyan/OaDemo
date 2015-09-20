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
    <form>
        <div class="container">
            <div class="row" style="padding: 10px 0 10px 0">
                <div class="col-md-2">申请时间:</div>
                <div class="col-md-5">
                    <input class="form-input" id="startDate">
                    <input class="form-input" id="endDate">
                </div>
                <div class="col-md-2">
                    <input class="btn-success" type="submit" value="查询">
                </div>
            </div>
        </div>
    </form>
    <table class="table  table-striped table-bordered table-hover ">
        <thead >
        <tr style="background-color: #eee">
            <th>申请人</th>
            <th>部门</th>
            <th>申请时间</th>
            <th>付款金额</th>
            <th>主管审批状态</th>
            <th>财务审批状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <#if pageModel??>
            <#list pageModel.records as dto>
            <tr>
                <td>${dto.applicantName}</td>
                <td>${dto.dept}</td>
                <td>${dto.applyTime?string("yyyy-MM-dd")}</td>
                <td>${dto.payAccount}</td>
                <td>${dto.mngApproveStatusName}</td>
                <td>${dto.finnaceApproveStatusName}</td>
                <td>

                </td>
            </tr>
            </#list>
        </#if>
        </tbody>
    </table>
</div>
</body>

<script src="/js/jquery-1.8.2.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/bootstrap-datetimepicker.min.js"></script>
<script>
    $("#startDate").datetimepicker({
        format: 'yyyy-mm-dd',
        minView: 2,
        todayHighlight: true,
        todayBtn: true,
        autoclose: true
    });
    $("#endDate").datetimepicker({
        format: 'yyyy-mm-dd',
        minView: 2,
        todayHighlight: true,
        todayBtn: true,
        autoclose: true
    });
</script>
</html>