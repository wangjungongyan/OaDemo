<!DOCTYPE html>
<html>
<head>
    <meta http-equiv=”content-type” content=”text/html; charset=UTF-8″/>
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
            width: 500px;
            box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);
            background-clip: padding-box;
        }

        .modal.fade.in {
            top: 10%;
            margin-left: -250px;
        }

    </style>
</head>
<body>

<#macro pageNavigation pageModel args>

    <#if pageModel?exists && (pageModel.totalCount > 1)>
        <#assign curPage = pageModel.currentPage>
        <#assign pageCount = pageModel.totalCount>
        <#if (pageCount%pageModel.pageSize > 0)>
            <#assign endPage = (pageCount/pageModel.pageSize + 1)>
        <#else>
            <#assign endPage = (pageCount/pageModel.pageSize)>
        </#if>

    </#if>

    <#if (curPage-4 >0)>
        <#assign headPage = (curPage-4) >
    <#else>
        <#assign headPage = 1 >
    </#if>

    <#if (curPage+4 <=endPage)>
        <#assign tailPage = (curPage+4) >
    <#else>
        <#assign tailPage = endPage >
    </#if>

    <#if (curPage > 1) >
    <li><a href="?pageNo=${curPage - 1}${args!}" class="page-prev" title="上一页"><i class="p-prev"></i>上一页</a></li>
    </#if>

    <#if (tailPage > headPage)>
        <#list headPage..tailPage as page>
            <#if curPage == page>
            <span>${page}</span>
            <#else>
            <li><a href="?pageNo=${page}${args!}" title="${page}">${page}</a></li>
            </#if>
        </#list>
    </#if>

    <#if (curPage < endPage)>
    <li><a href="?pageNo=${curPage + 1}${args!}" class="page-next" title="下一页">下一页<i class="p-next"></a></li>
    </#if>
</#macro>

<div class="container">

    <form class="form-inline" id="queryForm" action="/leave/wait2Audit" method="post">
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
                           value="<#if (queryDTO??) && queryDTO.startTime ??>${queryDTO.startTime?string('yyyy-MM-dd HH:mm:ss')}</#if>">

                    <label>到</label>
                    <input name="endTime" type="text" class="form_datetime" style="width: 150px;"
                           value="<#if (queryDTO??) && queryDTO.endTime>${queryDTO.endTime?string('yyyy-MM-dd HH:mm:ss')}</#if>">

                    &nbsp;&nbsp;&nbsp;
                    <button name="queryButton" type="submit" class="btn btn-default">查询</button>

                </div>
            </tr>
        </table>
    </form>

    <table class="table  table-striped table-bordered table-hover ">
        <thead>
        <tr style="background-color: #eee">
            <th>申请人</th>
            <th>申请时间</th>
            <th>类型</th>
            <th>开始时间</th>
            <th>结束时间</th>
            <th>天数(天)</th>
            <th>请假事由</th>
            <th>审批</th>
        </tr>
        </thead>
        <tbody>
        <#if pageModel?? && pageModel.records?? && (pageModel.records?size>0)>
            <#list pageModel.records as apply>
            <tr class="info" id="apply${apply.id}">
                <td>${apply.applicant.chineseName}</td>
                <td>${apply.applyTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                <td>${apply.leaveName}</td>
                <td>${apply.leaveStartTime?string('yyyy-MM-dd HH:mm')}</td>
                <td>${apply.leaveEndTime?string('yyyy-MM-dd HH:mm')}</td>
                <td>${apply.leaveDays}</td>
                <td>${apply.leaveReason}</td>
                <td><a href="javascript:void(0)" name="showApplyDetail" onclick="getSelectedApplyId(${apply.id})"
                       data-toggle="modal" data-target="#editModal">审批</a>
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
                <button type="button" name="closeButton" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">开始审批</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <table class="table .table-condensed table-striped ">
                        <tr>
                            <td>
                                <label class="col-sm-8 control-label">
                                    审批意见
                                </label>
                            </td>
                            <td>
                                <div class="col-sm-3">
                                    <textarea class="form-control" rows="3" cols="40" id="suggest"></textarea>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" style="text-align:center">
                                <button type="button" name="auditButton" class="btn btn-info" value="1">审批通过</button>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <button type="button" name="auditButton" class="btn btn-danger" value="2">审批不通过</button>
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

<div>
    <nav>
        <ul class="pager">
        <@pageNavigation pageModel "${queryDTO.queryCondition}" />
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

    $(document).ready(function () {

        $("#alertSucess").hide();

        $("button[name='auditButton']").click(function () {
            var auditStatus = $(this).val();
            var suggest = $("#suggest").val();
            var url = "/leave/ajaxAudit";
            var alertSucess = $("#alertSucess");

            $.ajax(url, {
                dataType: "json",
                contentType: "application/x-www-form-urlencoded;charset=utf-8",
                type: "post",
                data: {
                    "applyId": selectedApplyId,
                    "auditStatus": auditStatus,
                    "auditSuggest": suggest
                },
                success: function (result) {
                    alertSucess.text(result.msg).show().delay(2000).hide(0);
                    var selelctedTr = "#apply" + selectedApplyId;
                    $(selelctedTr).remove();
                },
                error: function () {
                    alertSucess.text("操作失败，稍后再试吧.").show().delay(2000).hide(0);
                }
            });

        });

    });

</script>
</html>
