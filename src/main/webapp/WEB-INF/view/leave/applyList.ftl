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
            <label for="exampleInputName2">申请人</label>
            <input type="text" class="form-control" id="exampleInputName2" placeholder="张三">
            <label for="exampleInputEmail2">申请时间</label>
            <input size="10" id="startTime" type="text"  readonly class="form_datetime" style="width: 100px;">
            <label for="exampleInputEmail2">到</label>
            <input size="10" id="endTime" type="text"  readonly class="form_datetime" style="width: 100px;">
            <button type="submit" class="btn btn-default">查询</button>
        </div>
    </form>
    <table class="table  table-striped table-bordered table-hover ">
        <thead >
            <tr style="background-color: #eee">
                <th>申请人</th>
                <th>申请时间</th>
                <th>目的地</th>
                <th>天数</th>
                <th>总费用</th>
                <th>状态</th>
                <th>查看</th>
            </tr>
        </thead>
        <tbody>
            <tr class="success">
                <td>张三</td>
                <td>2015-01-01</td>
                <td>北京</td>
                <td>5天</td>
                <td>32425.00</td>
                <td>审批通过</td>
                <td><a href="applyDetail">查看详情</a></td>
            </tr>
            <tr class="danger" style="background-color: red">
                <td>李四</td>
                <td>2015-01-01</td>
                <td>北京</td>
                <td>5天</td>
                <td>32425.00</td>
                <td>已拒绝</td>
                <td><a href="applyDetail">查看详情</a></td>
            </tr>
            <tr>
                <td>王五</td>
                <td>2015-01-01</td>
                <td>北京</td>
                <td>5天</td>
                <td>32425.00</td>
                <td>未审批</td>
                <td><a href="applyDetail">查看详情</a></td>
            </tr>
            <tr>
                <td>刘七</td>
                <td>2015-01-01</td>
                <td>北京</td>
                <td>5天</td>
                <td>32425.00</td>
                <td>未审批</td>
                <td><a href="applyDetail">查看详情</a></td>
            </tr>
            <tr>
                <td>吴八</td>
                <td>2013-01-01</td>
                <td>北京</td>
                <td>5天</td>
                <td>32425.00</td>
                <td>未审批</td>
                <td><a href="applyDetail">查看详情</a></td>
            </tr>
            <tr>
                <td>张三</td>
                <td>2015-01-01</td>
                <td>北京</td>
                <td>5天</td>
                <td>32425.00</td>
                <td>未审批</td>
                <td><a href="applyDetail">查看详情</a></td>
            </tr>
            <tr>
                <td>张三</td>
                <td>2015-01-01</td>
                <td>北京</td>
                <td>5天</td>
                <td>32425.00</td>
                <td>未审批</td>
                <td><a href="applyDetail">查看详情</a></td>
            </tr>
            <tr>
                <td>张三</td>
                <td>2015-01-01</td>
                <td>北京</td>
                <td>5天</td>
                <td>32425.00</td>
                <td>未审批</td>
                <td><a href="applyDetail">查看详情</a></td>
            </tr>
            <tr>
                <td>张三</td>
                <td>2015-01-01</td>
                <td>北京</td>
                <td>5天</td>
                <td>32425.00</td>
                <td>未审批</td>
                <td><a href="applyDetail">查看详情</a></td>
            </tr>
            <tr>
                <td>张三</td>
                <td>2015-01-01</td>
                <td>北京</td>
                <td>5天</td>
                <td>32425.00</td>
                <td>未审批</td>
                <td><a href="applyDetail">查看详情</a></td>
            </tr>
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
     $("#startTime").datetimepicker({format: 'yyyy-mm-dd',minView:2,todayHighlight:true,initialDate:new Date()});
     $("#endTime").datetimepicker({format: 'yyyy-mm-dd',minView:2,todayHighlight:true,initialDate:new Date()});
    </script>
</body>
</html>