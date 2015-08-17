<!DOCTYPE html>
<html>
<head>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/font-awesome.min.css" rel="stylesheet">

    <link href="/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <form class="form-inline" id="queryForm">
        <div class="form-group">

            <label>申请类型</label>
            <select id="leaveType" name="leaveType">
            <#list employeeHolidays as holiday>
                <option value="${holiday.type}" <#if leaveType== holiday.type>
                        selected="selected" </#if>>${holiday.name}</option>
            </#list>
            </select>

            <label>申请时间</label>
            <input name="startTime" type="text" class="form_datetime" style="width: 150px;">

            <label>到</label>
            <input name="endTime" type="text" class="form_datetime" style="width: 150px;">

            <button name="queryButton" type="button" class="btn btn-default">查询</button>

        </div>
    </form>
    <table class="table  table-striped table-bordered table-hover ">
        <thead>
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
                <#if myApply.status == 0>
                <tr class="info">
                <#elseif myApply.status == 1>
                <tr class="success">
                <#else>
                <tr class="warning">
                </#if>
                <td>${myApply.applyTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                <td>${myApply.leaveName}</td>
                <td>
                    <#if myApply.audit??>
                        <#assign audit = myApply.audit/>
                        <#assign manager = audit.manager/>
                    ${manager.chineseName}
                        <#if audit.managerAuditStatus == 1>
                            审核通过
                        <#elseif audit.managerAuditStatus == 2 >
                            审核不通过
                        <#else>
                            未审核
                        </#if>
                        ->
                        <#if audit.hr??>
                            <#assign hr = audit.hr/>
                        ${hr.chineseName}(HR)
                            <#if audit.hrAuditStatus == 1>
                                审核通过
                            <#elseif audit.hrAuditStatus == 2 >
                                审核不通过
                            <#else>
                                未审核
                            </#if>
                        </#if>
                    <#else>
                        暂未审批
                    </#if>

                </td>
                <td>${myApply.leaveStartTime?string('yyyy-MM-dd HH:mm')}
                    至 ${myApply.leaveEndTime?string('yyyy-MM-dd HH:mm')}</td>
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

</body>
<script src="/js/jquery-1.8.2.min.js"></script>
<script src="/js/jquery.json.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript">

    $("input[name='startTime']").datetimepicker({
        format: 'yyyy-mm-dd hh:ii',
        minView: 0,
        todayHighlight: true,
        todayBtn: true,
        autoclose: true
    });

    $("input[name='endTime']").datetimepicker({
        format: 'yyyy-mm-dd hh:ii',
        minView: 0,
        todayHighlight: true,
        todayBtn: true,
        autoclose: true
    });

    function queryCondition(startTime, endTime, leaveType) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.leaveType = leaveType;
    }

    $(document).ready(function () {

        $("button[name='queryButton']").click(function () {

            var startTime = $("input[name='startTime']").val();
            var endTime = $("input[name='endTime']").val();
            var leaveType = $("#leaveType").find("option:selected").attr("value");

            var query = new queryCondition(startTime, endTime, leaveType);
            var href = "/leave/myLeaveApply/" + jQuery.toJSON(query);

            $("#queryForm").attr("action", href);
            $("#queryForm").attr("method", "get");
            $("#queryForm").submit();
        })

    });

</script>
</html>
