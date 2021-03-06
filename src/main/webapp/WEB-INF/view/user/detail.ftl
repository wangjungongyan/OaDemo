<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/font-awesome.min.css" rel="stylesheet">
    <link href="/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <style type="text/css">
        .form-horizontal .control-label {
            padding-top: 7px;
            margin-bottom: 0;
            text-align: left;}
    </style>
</head>

<body>

<div style="margin-top:10px;margin-left:10px;visibility:visible">
    <form action="/user/addUser" class="form-horizontal form-inline" method="post">
        <div class="col-sm-10">
            <table class="table .table-condensed table-striped ">
                <tr>
                    <td colspan="4"><label id="cishu" style="margin-left: 30px;
    text-align: left;
    font-weight: bold;
    font-size: 16px;
    color: red;" class="col-sm-40 control-label">员工详情</label></td>
                </tr>
                <tr>
                    <td><label class="col-sm-8 control-label">FirstName</label></td>
                    <td>
                        <div class="col-sm-3">
                            <input name="firstName" value="${user.firstName}" type="gongzuocantime" class="form-control" id="inputgongzuocantime" placeholder="FirstName">
                        </div>
                    </td>
                    <td><label class="col-sm-8 control-label">MiddleName</label></td>
                    <td>
                        <div class="col-sm-3">
                            <input name="middleName"  value="${user.middleName}" type="gongzuocantime" class="form-control" id="inputgongzuocantime" placeholder="MiddleName">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td><label class="col-sm-8 control-label">LastName</label></td>
                    <td>
                        <div class="col-sm-3">
                            <input name="lastName" value="${user.lastName}" type="gongzuocantime" class="form-control" id="inputgongzuocantime" placeholder="LastName">
                        </div>
                    </td>
                    <td><label class="col-sm-8 control-label">email</label></td>
                    <td>
                        <div class="col-sm-3">
                            <input name="email"  value="${user.email}" type="gongzuocantime" class="form-control" id="inputgongzuocantime" placeholder="email">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td><label class="col-sm-8 control-label">中文名</label></td>
                    <td>
                        <div class="col-sm-3">
                            <input name="chineseName" value="${user.chineseName}" type="gongzuocantime" class="form-control" id="inputgongzuocantime" placeholder="请输入员工中文名">
                        </div>
                    </td>
                    <td><label class="col-sm-8 control-label">初始密码</label></td>
                    <td>
                        <div class="col-sm-3">
                            <input name="password" type="gongzuorijiaotongfeitime" class="form-control" id="inputgongzuorijiaotongfeitime" placeholder="请设置初始密码">
                        </div>
                    </td>
                </tr>

                <tr>
                    <td><label class="col-sm-8 control-label">汇报对象</label></td>
                    <td>
                        <div class="col-sm-3">
                            <input name="manger.id"  value="${user.manager.id}" type="gongzuocantime" class="form-control" id="inputgongzuocantime" placeholder="">
                        </div>
                    </td>
                    <td><label class="col-sm-8 control-label">入职日期</label></td>
                    <td>
                        <div class="col-sm-3">
                            <input name="joinDate" size="10" id="joinDate" type="text"  readonly class="form_datetime" style="width: 100px;">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td><label class="col-sm-8 control-label">部门</label></td>
                    <td>
                        <div class="col-sm-3">
                            <input name="departMent"  value="${user.departMent}" type="gongzuorijiaotongfeitime" class="form-control" id="inputgongzuorijiaotongfeitime" placeholder="">
                        </div>
                    </td>
                    <td><label class="col-sm-8 control-label">角色</label></td>
                    <td>
                        <div class="col-sm-3">
                            <input name="role" value="${user.role}" type="gongzuorijiaotongfeitime" class="form-control" id="inputgongzuorijiaotongfeitime" placeholder="">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td><label class="col-sm-8 control-label">手机号</label></td>
                    <td>
                        <div class="col-sm-3">
                            <input name="phone" value="${user.phone}" type="gongzuorijiaotongfeitime" class="form-control" id="inputgongzuorijiaotongfeitime" placeholder="请输入员工手机号">
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </form>
</div>
<script src="/js/jquery-1.8.2.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript">
    $("#joinDate").datetimepicker({format: 'yyyy-mm-dd',minView:2,todayHighlight:true,initialDate:new Date()});
</script>
</body>
</html>
