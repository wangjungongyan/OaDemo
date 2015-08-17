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
                <th>中文名</th>
                <th>英文名</th>
                <th>入职时间</th>
                <th>汇报人</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
            <#if employeeList??>
                <#list employeeList as employee>
                    <#if employee.status == 1>
                        <tr class="warning">
                    <#elseif employee.status == 0>
                        <tr class="success">
                    <#else>
                        <tr class="warning">
                    </#if>
                    <td>${employee.chineseName}</td>
                    <td>${employee.firstName}.${employee.middleName}.${employee.lastName}</td>
                    <td>
                        ${employee.joinDate?string('yyyy-MM-dd')}

                    </td>
                    <td><a href="/user/detail?id=${employee.manger.id}">${employee.manger.chineseName}</a> </td>
                    <td>
                        <#if employee.status == 1>
                            离职
                        <#elseif employee.status == 0>
                            在职
                        </#if>
                    </td>
                    <td><a href="/user/detail?id=${employee.id}">修改</a></td>
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
            <a href="/user/list?page=1" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>
        <li><a href="/user/list?page=1">1</a></li>
        <li><a href="/user/list?page=2">2</a></li>
        <li><a href="/user/list?page=3">3</a></li>
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
