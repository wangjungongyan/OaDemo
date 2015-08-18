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
    <form action="/user/update" class="form-horizontal form-inline" method="post">
        <input type="hidden" name="id" value="${user.id}">
        <div class="col-sm-10">
            <table class="table .table-condensed table-striped ">
                <tr>
                    <td colspan="4"><label id="cishu" style="margin-left: 30px;
    text-align: left;
    font-weight: bold;
    font-size: 16px;
    color: red;" class="col-sm-40 control-label">编辑员工信息</label></td>
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
                            <input name="joinDate" value="${user.joinDate?string('yyyy-MM-dd')}" size="10" id="joinDate" type="text"  readonly class="form_datetime" style="width: 100px;">
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
                            <input name="phone" value="${user.phone}" type="gongzuorijiaotongfeitime" class="form-control" id="inputgongzuorijiaotongfeitime" placeholder="请输入员工手机号">
                        </div>
                    </td>
                    <td>
                        在职状态
                    </td>
                    <td>
                        <select name="status">
                            <option value="0" <#if user.status == 0>selected="selected" </#if>>
                                在职
                            </option>
                            <option value="1" <#if user.status == 1>selected="selected" </#if>>
                                离职
                            </option>
                        </select>

                    </td>
                </tr>
                <tr>
                    <td ></td>
                    <td>
                    </td>
                    <td>
                        <p>
                            <a href="/user/list">
                                <button type="button" class="btn btn-info">
                                    返回用户列表
                                </button>
                            </a>
                            <button type="submit" class="btn btn-info">
                                修改
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
