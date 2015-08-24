<!DOCTYPE html>
<html>
<head>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/font-awesome.min.css" rel="stylesheet">
    <link href="/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <style>
        .form-horizontal .control-label {
            float: left;
            width: 80px;
            padding-top: 5px;
            text-align: right;
        }

        .modal {
            position: fixed;
            left: 50%;
            z-index: 1050;
            margin-left: -280px;
            background-color: #fff;
            border-radius: 6px;
            outline: 0;
            width: auto;
            box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);
            background-clip: padding-box;
        }

        .modal.fade.in {
            top: 10%;
            margin-left: -350px;
        }

    </style>
</head>
<body>

<div class="container">

    <form class="form-inline" id="queryForm" action="/sys/employeeHolidayList" method="post">
        <table>
            <tr>
                <div class="form-group">
                    <label>年份</label>
                    &nbsp;&nbsp;&nbsp;
                    <input name="employeeId" type="hidden" style="width: 150px;" value="${employee.id}"/>
                    <input name="year" type="text" class="form_datetime" style="width: 150px;" value="${year}"/>
                    &nbsp;&nbsp;&nbsp;
                    <button name="queryButton" type="submit" class="btn btn-default">查询</button>

                </div>
            </tr>
        </table>
    </form>

    <table class="table  table-striped table-bordered table-hover ">
        <thead>
        <tr style="background-color: #eee">
            <th>编号</th>
            <th>申请人</th>
            <th>年份</th>
            <th>假期类型</th>
            <th>拥有(天)</th>
            <th>编辑</th>
        </tr>
        </thead>
        <tbody>
        <#list employeeHolidays as employeeHoliday>
        <td>${employeeHoliday_index + 1}</td>
        <td>${employee.chineseName}</td>
        <td>${employeeHoliday.year}</td>
        <td>${employeeHoliday.name}</td>
        <td>${employeeHoliday.own}</td>
        <td>
            <a href="javascript:void(0)" data-toggle="modal" data-target="#editModal" name="edit"
               employId="${employee.id}" type="${employeeHoliday.type}" own="${employeeHoliday.own}" year = "${employeeHoliday.year}">编辑</a>
        </td>
        </tr>
        </#list>
        </tbody>
    </table>

</div>

<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" style="display: none">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" name="closeButton" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">请假详情</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <table class="table .table-condensed table-striped ">
                        <tr>
                            <td><label class="col-sm-8 control-label">年份</label></td>
                            <td>
                                <div class="col-sm-3">
                                    <input class="form-control" readonly name="selectedYear"/>
                                </div>
                            </td>
                            <td><label class="col-sm-8 control-label">类型</label></td>
                            <td>
                                <div class="col-sm-3">
                                    <input class="form-control" readonly name="type"/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label class="col-sm-8 control-label">
                                    天数
                                </label>
                            </td>
                            <td>
                                <div class="col-sm-3">
                                    <input class="form-control" name="days">
                                </div>
                            </td>
                            <td colspan="2" style="text-align:center">
                                <button type="button" name="updateButton" class="btn btn-success" value="1">提交修改
                                </button>
                            </td>
                        </tr>

                    </table>
                </form>
            </div>

            <div class="modal-footer">
                <div id="alertSucess" class='alert alert-sucess' role='alert' style='text-align: center;'></div>
            </div>

        </div>
    </div>
</div>

</body>

<script src="/js/jquery-1.8.2.min.js"></script>
<script src="/js/jquery.json.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript">

    $("input[name='year']").datetimepicker({
        format: 'yyyy',
        minView: 4,
        startView: 4,
        todayHighlight: true,
        todayBtn: true,
        autoclose: true
    });

    $(document).ready(function () {

        $("#alertSucess").hide();

        $("a[name='edit']").click(function () {
            var data = $(this);
            $("input[name='type']").val(data.attr("type"));
            $("input[name='selectedYear']").val(data.attr("year"));
            $("input[name='days']").val(data.attr("own"));
        });

        $("button[name='updateButton']").click(function () {
            var type = $("input[name='type']").val();
            var year = $("input[name='selectedYear']").val();
            var own = $("input[name='days']").val();
            var employeeId = $("input[name='employeeId']").val();
            var url = "/sys/employeeHolidayUpdate";
            var alertSucess = $("#alertSucess");

            $.ajax(url, {
                dataType: "text",
                contentType: "application/xml",
                method: "post",
                data: {
                    "type": type,
                    "year": year,
                    "own": own,
                    "employeeId": employeeId
                },
                success: function (result) {
                    alertSucess.text(result).show().delay(2000).hide(0);
                },
                error: function () {
                    alert("出错咯，稍后再试吧.");
                }
            });


        });

    });

</script>
</html>
