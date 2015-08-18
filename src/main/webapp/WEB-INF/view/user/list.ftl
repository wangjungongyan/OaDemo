<!DOCTYPE html>
<html>
<head>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/font-awesome.min.css" rel="stylesheet">

    <link href="/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
</head>
<body >
<div class="container" style="width:100%;">
    <form class="form-inline" action="/user/list">
        <div class="form-group">

            <label>中文姓名</label>
            <input size="10" name="chineseName" value="${userDTO.chineseName}" type="text"  style="width: 80px;">
            <label>firstName</label>
            <input size="10" name="firstName" value="${userDTO.firstName}" type="text"  style="width: 80px;">
            <label>middleName</label>
            <input size="10" name="middleName" value="${userDTO.middleName}" type="text"  style="width: 80px;">
            <label>lastName</label>
            <input size="10" name="lastName" value="${userDTO.lastName}" type="text"  style="width: 80px;">
            <label>入职时间</label>
            <input size="15" id="startTime" name="startTime" value="${startTime?string('yyyy-MM-dd')}" type="text"  readonly class="form_datetime" style="width: 80px;">

            <label>到</label>
            <input size="15" name="endTime" id="endTime" type="text" value="${endTime?string('yyyy-MM-dd')}"  readonly class="form_datetime" style="width: 80px;">

            <button type="submit" class="btn btn-default">查询</button>

        </div>
    </form>
    <table class="table  table-striped table-bordered table-hover ">
        <thead >
            <tr style="background-color: #eee">
                <th>ID</th>
                <th>中文名</th>
                <th>英文名</th>
                <th>入职时间</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
            <#if pageModel??>
                <#list pageModel.records as employee>
                    <#if employee.status == 1>
                        <tr class="warning">
                    <#elseif employee.status == 0>
                        <tr class="success">
                    <#else>
                        <tr class="warning">
                    </#if>
                    <td>${employee.id}</td>
                    <td>${employee.chineseName}</td>
                    <td>${employee.firstName}.${employee.middleName}.${employee.lastName}</td>
                    <td>
                        ${employee.joinDate?string('yyyy-MM-dd')}

                    </td>
                    <td>
                        <#if employee.status == 1>
                            离职
                        <#elseif employee.status == 0>
                            在职
                        </#if>
                    </td>
                    <td><a href="/user/updateUserIndex?id=${employee.id}">修改</a></td>
                </tr>
                </#list>
            </#if>
        </tbody>
    </table>
</div>
<div>
<#if pageModel??>
    <nav>
        <ul class="pager">
            <li>
                <a href="/user/list?pageNum=${pageModel.currentPage-1}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;上一页</span>
                </a>
            </li>
            <!--
                <li><a href="/user/list?pageNum=${pageModel.currentPage+1}">1</a></li>
                <li><a href="/user/list?pageNum=${pageModel.currentPage+2}">2</a></li>
                <li><a href="/user/list?pageNum=${pageModel.currentPage+3}">3</a></li>
            -->
            <li>
                <a href="/user/list?pageNum=${pageModel.currentPage+1}" aria-label="Next">
                    <span aria-hidden="true">下一页&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
</#if>
</div>
<script src="/js/jquery-1.8.2.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
    <script src="/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript">

        $("#startTime").datetimepicker({
            format: 'yyyy-mm-dd',
            minView:2,
            todayHighlight:true,
            todayBtn:true,
            autoclose:true
        });

        $("#endTime").datetimepicker({
            format: 'yyyy-mm-dd',
            minView:2,
            todayHighlight:true,
            todayBtn:true,
            autoclose:true
        });

    </script>
</body>
</html>
