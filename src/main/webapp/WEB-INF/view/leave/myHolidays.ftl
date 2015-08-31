<!DOCTYPE html>
<html>
<head>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/font-awesome.min.css" rel="stylesheet">
    <link href="/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
</head>
<body>

<div class="container">

    <table class="table  table-striped table-bordered table-hover ">
        <thead>
        <tr style="background-color: #eee">
            <th>假期类型</th>
            <th>年份</th>
            <th>拥有天数</th>
            <th>已用天数</th>
            <th>剩余天数</th>
        </tr>
        </thead>
        <tbody>
        <#list employeeHolidays as holiday>
            <td>${holiday.englishName}</td>
            <td>${holiday.year}</td>
            <td>${holiday.own}</td>
            <td>${holiday.used}</td>
            <td>${holiday.surplus}</td>
        </tr>
        </#list>
        </tbody>
    </table>
</div>

</body>

<script src="/js/jquery-1.8.2.min.js"></script>
<script src="/js/jquery.json.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/bootstrap-datetimepicker.min.js"></script>
</html>
