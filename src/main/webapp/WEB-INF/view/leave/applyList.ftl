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

    </style>
</head>
<#include "/WEB-INF/view/empty.ftl">
<body>

<#function cutString str,len>
    <#if str?length &gt; len>
        <#return str?substring(0,len?int) + "...">
    <#else>
        <#return str>
    </#if>
</#function>

<#macro pageNavigation pageModel args>

    <#if pageModel?exists && (pageModel.totalCount > 1)>
        <#assign curPage = pageModel.currentPage>
        <#assign pageCount = pageModel.totalCount>
        <#assign endPage = pageCount/pageModel.pageSize + 1>
    </#if>

    <#if (curPage-2 >0)>
        <#assign headPage = (curPage-2) >
    <#else>
        <#assign headPage = 1 >
    </#if>

    <#if (curPage+2 <=endPage)>
        <#assign tailPage = (curPage+2) >
    <#else>
        <#assign tailPage = endPage >
    </#if>

<#--curPage:${curPage}-->
<#--headPage:${headPage}-->
<#--headPage2:${headPage2}-->
<#--endPage:${endPage}-->

    <#if (curPage > 1) >
    <li><a href="?pageNo=${curPage - 1}${args!}" class="page-prev" title="上一页"><i class="p-prev"></i>上一页</a></li>
    </#if>

    <#list headPage..tailPage as page>
        <#if curPage == page>
        <span>${page}</span>
        <#else>
        <li><a href="?pageNo=${page}${args!}" title="${page}">${page}</a></li>
        </#if>
    </#list>

    <#if (curPage < endPage)>
    <li><a href="?pageNo=${curPage + 1}${args!}" class="page-next" title="下一页">下一页<i class="p-next"></a></li>
    </#if>
</#macro>

<div class="container">

    <form class="form-inline" id="queryForm" action="/leave/myLeaveApply" method="post">
        <table>
            <tr>
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
                    <input name="startTime" type="text" class="form_datetime" style="width: 150px;"
                           value="<#if queryDTO??>${queryDTO.startTime?string('yyyy-MM-dd HH:mm:ss')}</#if>">

                    <label>到</label>
                    <input name="endTime" type="text" class="form_datetime" style="width: 150px;"
                           value="<#if queryDTO??>${queryDTO.endTime?string('yyyy-MM-dd HH:mm:ss')}</#if>">

                    &nbsp;&nbsp;&nbsp;
                    <button name="queryButton" type="submit" class="btn btn-default">查询</button>

                </div>
            </tr>
        </table>
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
                <td><a href="javascript:void(0)" name="showApplyDetail" onclick="getSelectedApplyId(${myApply.id})"
                       data-toggle="modal" data-target="#editModal">查看详情</a>
                </td>
            </tr>
            </#list>
        </#if>
        </tbody>
    </table>
</div>

<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" style="display: none">
    <div class="modal-dialog">
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
                                    <input class="form-control" readonly name="applyUser"/>
                                </div>
                            </td>
                            <td><label class="col-sm-8 control-label">申请时间</label></td>
                            <td>
                                <div class="col-sm-3">
                                    <input class="form-control" readonly name="applyTime"/>
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
                                    <input class="form-control" readonly name="selectedType">
                                </div>
                            </td>
                            <td><label class="col-sm-8 control-label">审批结果</label></td>
                            <td>
                                <div class="col-sm-3">
                                    <input class="form-control" readonly name="auditResult"/>
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
                                    <input class="form-control" readonly name="leaveStartTime"/>
                                </div>
                            </td>
                            <td>
                                <label class="col-sm-8 control-label">
                                    结束时间
                                </label>
                            </td>
                            <td>
                                <div class="col-sm-3">
                                    <input class="form-control" readonly name="leaveEndTime"/>
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
                                    <textarea class="form-control" rows="3" cols="40" readonly
                                              id="leaveReason"></textarea>
                                </div>
                            </td>
                        </tr>
                    </table>
                </form>

                <div class="panel panel-default">
                    <table class="table" id="auditChainTable">
                        <tr>
                            <th>审批人</th>
                            <th>审批时间</th>
                            <th>审批意见</th>
                            <th>审批结果</th>
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

<div>
    <nav>
        <ul class="pager">
        <@pageNavigation pageModel "" />
        </ul>
    </nav>
</div>


</body>

<script src="/js/jquery-1.8.2.min.js"></script>
<script src="/js/jquery.json.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript">
    var selectedApplyId = 0;

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

    function getSelectedApplyId(id) {
        selectedApplyId = id;
    }

    function getNullValue(value) {
        if (value == null || value == "") {
            return "/";
        }
        return value;
    }

    function getStatusName(status) {
        if (status == 0) {
            return "审核中";
        }
        if (status == 1) {
            return "通过";
        }
        if (status == 2) {
            return "不通过";
        }
    }

    function formatDate(originDate) {
        if (originDate == null) {
            return "/";
        }

        return new Date(parseInt(originDate)).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
        ;
    }

    $(document).ready(function () {

        $("a[name='showApplyDetail']").click(function () {

            var url = "/leave/ajaxGetApplyDetail?applyId=" + selectedApplyId;

            $.ajax(url, {
                dataType: "json",
                contentType: "application/xml",
                method: "get",
                success: function (result) {
                    var applyUser = result.applicant.chineseName;
                    var applyTime = result.applyTime;
                    var selectedType = result.leaveName;
                    var auditResult = result.statusName;
                    var leaveStartTime = formatDate(result.leaveStartTime);
                    var leaveEndTime = formatDate(result.leaveEndTime);
                    var leaveReason = getNullValue(result.leaveReason);

                    $("input[name='applyUser']").val(applyUser);
                    $("input[name='applyTime']").val(applyTime);
                    $("input[name='selectedType']").val(selectedType);
                    $("input[name='auditResult']").val(auditResult);
                    $("input[name='leaveStartTime']").val(leaveStartTime);
                    $("input[name='leaveEndTime']").val(leaveEndTime);
                    $("#leaveReason").attr("value", leaveReason);

                    var audit = result.audit;
                    var manager = audit.manager;
                    var hr = audit.hr;

                    var auditChain = "<tr>"
                            + "<td>" + getNullValue(manager.chineseName) + "</td>"
                            + "<td>" + formatDate(audit.managerAuditTime) + "</td>"
                            + "<td>" + getNullValue(audit.managerAuditSuggest) + "</td>"
                            + "<td>" + getStatusName(audit.managerAuditStatus) + "</td>"
                            + "</tr>"
                            + "<tr>"
                            + "<td>" + getNullValue(hr.chineseName) + "</td>"
                            + "<td>" + formatDate(audit.hrAuditTime) + "</td>"
                            + "<td>" + getNullValue(audit.hrAuditSuggest) + "</td>"
                            + "<td>" + getStatusName(audit.hrAuditStatus) + "</td>"
                            + "</tr>";
                    $("#auditChainTable").append(auditChain);
                },
                error: function () {
                    alert("出错咯，稍后再试吧.");
                }
            });
        });
    });

</script>
</html>
