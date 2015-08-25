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
                        class="col-sm-40 control-label">添加新员工</label>

                        <#if errorMsg??>
                            <label style="color: red;float: right;padding-right: 350px;">${errorMsg}</label>
                        </#if>
                    </td>
                </tr>
                <tr>
                    <td><label class="col-sm-8 control-label">FirstName</label></td>
                    <td>
                        <div class="col-sm-3">
                            <input name="firstName" required="FirstName" value="${user.firstName}" type="gongzuocantime" class="form-control" id="inputgongzuocantime" placeholder="FirstName">
                        </div>
                    </td>
                    <td><label class="col-sm-8 control-label">MiddleName</label></td>
                    <td>
                        <div class="col-sm-3">
                            <input name="middleName" required="" value="${user.middleName}" type="gongzuocantime" class="form-control" id="inputgongzuocantime" placeholder="MiddleName">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td><label class="col-sm-8 control-label">LastName</label></td>
                    <td>
                        <div class="col-sm-3">
                            <input name="lastName" required="" value="${user.lastName}" type="gongzuocantime" class="form-control" id="inputgongzuocantime" placeholder="LastName">
                        </div>
                    </td>
                    <td><label class="col-sm-8 control-label">email</label></td>
                    <td>
                        <div class="col-sm-3">
                            <input name="email" required="" value="${user.email}" type="gongzuocantime" class="form-control" id="inputgongzuocantime" placeholder="email">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td><label class="col-sm-8 control-label">中文名</label></td>
                    <td>
                        <div class="col-sm-3">
                            <input name="chineseName" required="" value="${user.chineseName}" type="gongzuocantime" class="form-control" id="inputgongzuocantime" placeholder="请输入员工中文名">
                        </div>
                    </td>
                    <td><label class="col-sm-8 control-label">初始密码</label></td>
                    <td>
                        <div class="col-sm-3">
                            <input name="password" required="" type="gongzuorijiaotongfeitime" class="form-control" id="inputgongzuorijiaotongfeitime" placeholder="请设置初始密码">
                        </div>
                    </td>
                </tr>

                <tr>
                    <td><label class="col-sm-8 control-label">汇报对象</label></td>
                    <td>
                        <div class="col-sm-3">
                            <select name="manager.id">
                                <option value="0">
                                    无
                                </option>
                                <#list manageList as manage>
                                    <option value="${manage.id}"
                                            <#if manage.id == user.manager.id> selected </#if>
                                            >
                                        ${manage.chineseName}
                                    </option>
                                </#list>
                            </select>
                        </div>
                    </td>
                    <td><label class="col-sm-8 control-label">入职日期</label></td>
                    <td>
                        <div class="col-sm-3">
                            <input name="joinDate" required="" value="${user.joinDate?string('yyyy-MM-dd')}" size="10" id="joinDate" type="text"   class="form_datetime" style="width: 100px;">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td><label class="col-sm-8 control-label">部门</label></td>
                    <td>
                        <div class="col-sm-3">
                            <select name="departMent" class="form-control">
                                <#list departments as depart>
                                <option value="${depart.type}"
                                        <#if depart.type == user.departMent>
                                            selected
                                        </#if>
                                        >
                                    ${depart.departmentName}
                                </option>
                                </#list>
                            </select>
                        </div>
                    </td>
                    <td><label class="col-sm-8 control-label">角色</label></td>
                    <td>
                        <div class="col-sm-3">
                            <select name="role">
                                <#list roles as role>
                                    <option  value="${role.type}"
                                            <#if role.type == user.role >
                                                selected
                                            </#if>
                                            >${role.roleName}</option>
                                </#list>
                            </select>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td><label class="col-sm-8 control-label">手机号</label></td>
                    <td>
                        <div class="col-sm-3">
                            <input name="phone" required="" value="${user.phone}" type="gongzuorijiaotongfeitime" class="form-control icon-mobile-phone" id="inputgongzuorijiaotongfeitime" placeholder="请输入员工手机号">
                        </div>
                    </td>
                    <td>

                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td ></td>
                    <td>
                    </td>
                    <td>
                        <p>
                                <button type="submit" class="btn btn-info">
                                    确认添加员工
                                </button>
                        </p>
                    </td>
                    <td></td>
                </tr>
                </p>

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
