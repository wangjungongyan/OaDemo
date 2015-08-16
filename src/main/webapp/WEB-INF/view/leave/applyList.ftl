<!DOCTYPE html>
<html>
<head>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/font-awesome.min.css" rel="stylesheet">

    <link href="/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
</head>
<body >
<div class="container">
    <form class="form-inline">
        <div class="form-group">

            <label>申请类型</label>
            <select name ="leaveType">
                <#list employeeHolidays as holiday>
                    <option value ="${holiday.type}">${holiday.name}</option>
                </#list>
            </select>

            <label>申请时间</label>
            <input size="15" id="startTime" type="text"  readonly class="form_datetime" style="width: 100px;">

            <label>到</label>
            <input size="15" id="endTime" type="text"  readonly class="form_datetime" style="width: 100px;">

            <button type="submit" class="btn btn-default">查询</button>

        </div>
    </form>
    <table class="table  table-striped table-bordered table-hover ">
        <thead >
            <tr style="background-color: #eee">
                <th>申请时间</th>
                <th>请假类型</th>
                <th>审批流</th>
                <th>请假时间段</th>
                <th>状态</th>
                <th>查看详情</th>
            </tr>
        </thead>
        <tbody>
            <#if myApplys??>
                <#list myApplys as myApply>
                    <#if myApply.status != 2>
                        <tr class="success">
                        <#else>
                        <tr class="warning">
                    </#if>
                    <td>${myApply.applyTime?string('yyyy-MM-dd HH:mm')}</td>
                    <td>${myApply.leaveName}</td>
                    <td>审批流</td>
                    <td>${myApply.leaveStartTime?string('yyyy-MM-dd HH:mm')} 至 ${myApply.leaveEndTime?string('yyyy-MM-dd HH:mm')}</td>
                    <td>${myApply.statusName}</td>
                    <td><a href="/leaveApplyDetail">查看详情</a></td>
                </tr>
                </#list>
            </#if>
        </tbody>
    </table>
</div>
<div>
<nav>
    <ul class="pager">
        <li>
            <a href="#" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>
        <li><a href="#">1</a></li>
        <li><a href="#">2</a></li>
        <li><a href="#">3</a></li>
        <li><a href="#">4</a></li>
        <li><a href="#">5</a></li>
        <li>
            <a href="#" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>
    </ul>
</nav>
</div>
<script src="/js/jquery-1.8.2.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
    <script src="/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript">

        $("#startTime").datetimepicker({
            format: 'yyyy-mm-dd hh:ii',
            minView:0,
            todayHighlight:true,
            todayBtn:true,
            autoclose:true
        });

        $("#endTime").datetimepicker({
            format: 'yyyy-mm-dd hh:ii',
            minView:0,
            todayHighlight:true,
            todayBtn:true,
            autoclose:true
        });

    </script>
</body>
</html>
