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
        .modal{
            position: fixed;
            left: 50%;
            z-index: 1050;
            margin-left: -280px;
            background-color: #fff;
            border-radius: 6px;
            outline: 0;
            width: auto;
            box-shadow: 0 3px 7px rgba(0,0,0,0.3);
            background-clip: padding-box;
        }
        textarea{
            width: 200px;
        }
    </style>
</head>
<body>
<div class="container">

    <form class="form-inline" id="queryForm" action="/leave/myLeaveApply" method="post">
        <div class="form-group">

            <label>申请类型</label>
            <select id="leaveType" name="leaveType">
            <#list employeeHolidays as holiday>
                <option value="${holiday.type}" <#if queryDTO?? && queryDTO.leaveType== holiday.type>
                        selected="selected" </#if>>${holiday.name}</option>
            </#list>
            </select>

            &nbsp;&nbsp;&nbsp;
            <label>申请时间从</label>
            <input name="startTime" type="text" class="form_datetime" style="width: 150px;" value="<#if queryDTO??>${queryDTO.startTime?string('yyyy-MM-dd HH:mm:ss')}</#if>">

            <label>到</label>
            <input name="endTime" type="text" class="form_datetime" style="width: 150px;"  value="<#if queryDTO??>${queryDTO.endTime?string('yyyy-MM-dd HH:mm:ss')}</#if>">

            &nbsp;&nbsp;&nbsp;
            <button name="queryButton" type="submit" class="btn btn-default">查询</button>

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
        <#if pageModel?? && pageModel.records?? && (pageModel.records?size>0)>
            <#list pageModel.records as myApply>
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
                <td><a href="javascript:void(0)" name="showApplyDetail" onclick="setWith4Modal()" data-toggle="modal"
                       data-target="#editModal">查看详情</a></td>
            </tr>
            </#list>
        </#if>
        </tbody>
    </table>

    <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true" >
        <div class="modal-dialog" >
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">请假详情</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal">
                        <table class="table .table-condensed table-striped ">
                            <tr>
                                <td><label class="col-sm-8 control-label">申请人</label></td>
                                <td>
                                    <div class="col-sm-3">
                                        <input class="form-control" readonly value="">
                                    </div>
                                </td>
                                <td><label class="col-sm-8 control-label">申请时间</label></td>
                                <td>
                                    <div class="col-sm-3">
                                        <input class="form-control" readonly value="">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="col-sm-8 control-label">
                                        请假类型
                                    </label>
                                </td>
                                <td>
                                    <div class="col-sm-3">
                                        <input class="form-control" readonly value="">
                                    </div>
                                </td>
                                <td><label class="col-sm-8 control-label">审批结果</label></td>
                                <td>
                                    <div class="col-sm-3">
                                        <input class="form-control" readonly value="">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="col-sm-8 control-label">
                                        开始时间
                                    </label>
                                </td>
                                <td>
                                    <div class="col-sm-3">
                                        <input class="form-control" name="leaveStartTime" value="" readonly/>
                                    </div>
                                </td>
                                <td>
                                    <label class="col-sm-8 control-label">
                                        结束时间
                                    </label>
                                </td>
                                <td>
                                    <div class="col-sm-3">
                                        <input class="form-control" name="leaveEndTime" value="" readonly>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="col-sm-8 control-label">
                                        请假事由
                                    </label>
                                </td>
                                <td>
                                    <div class="col-sm-3">
                                        <textarea class="form-control" rows="3" cols="40" name="leaveReason" value="" readonly></textarea>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </form>

                    <div class="panel panel-default">
                        <table class="table">
                            <tr>
                                <th>审批人</th>
                                <th>审批时间</th>
                                <th>审批结果</th>
                            </tr>
                            <tr>
                                <td>
                                    樊帅
                                </td>
                                <td>
                                    <div class="col-sm-3">
                                        2015-09-01 10:01:09
                                    </div>
                                </td>
                                <td>
                                    审批通过
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" id="closeEditButton" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>

    </div>
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
        format: 'yyyy-mm-dd hh:ii:ss',
        minView: 0,
        todayHighlight: true,
        todayBtn: true,
        autoclose: true
    });

    $("input[name='endTime']").datetimepicker({
        format: 'yyyy-mm-dd hh:ii:ss',
        minView: 0,
        todayHighlight: true,
        todayBtn: true,
        autoclose: true
    });

    $(document).ready(function () {

//        $("button[name='queryButton']").click(function () {
//
//            var startTime = $("input[name='startTime']").val();
//            var endTime = $("input[name='endTime']").val();
//            var leaveType = $("#leaveType").find("option:selected").attr("value");
//
//            var query = new queryCondition(startTime, endTime, leaveType);
//            var href = "/leave/myLeaveApply/" + jQuery.toJSON(query);
//
//            $("#queryForm").attr("action", href);
//            $("#queryForm").attr("method", "get");
//            $("#queryForm").submit();
//        })

    });

</script>
</html>
