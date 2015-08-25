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

    <form class="form-inline" id="queryForm" action="/sys/initYearHoliday/setting">
        <table>
            <tr>
                <div class="form-group">

                    <label>年份</label>
                    <input name="year" value="${year}" type="text" required="" class="form_datetime" style="width: 150px;">
                    &nbsp;&nbsp;&nbsp;
                    <button name="queryButton" type="submit" class="btn btn-default">初始化</button>
                </div>
            </tr>
        </table>
    </form>
</div>

<script src="/js/jquery-1.8.2.min.js"></script>
<script src="/js/jquery.json.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/bootstrap-datetimepicker.min.js"></script>
<script>
    $("input[name='year']").datetimepicker({
        format: 'yyyy',
        minView: 4,
        startView:4,
        todayHighlight: true,
        todayBtn: true,
        autoclose: true
    });
</script>
</body>


</html>
