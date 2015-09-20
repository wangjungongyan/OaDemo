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

    <#if (curPage lt endPage)>
    <li><a href="?pageNo=${curPage + 1}${args!}" class="page-next" title="下一页">下一页<i class="p-next"></a></li>
    </#if>
</#macro>

<div class="container">

    <form class="form-inline" id="queryForm" action="/purchase/myHisAudits" method="post">
        <table>
            <tr>
                <div class="form-group">

                    <label>购买类型</label>
                    <select id="buyType" name="buyType">
                        <option value="0">全部</option>
                        <option value="1" <#if queryDTO.buyType== 1> selected="selected" </#if>>自己购买</option>
                        <option value="2" <#if queryDTO.buyType== 2> selected="selected" </#if>>IT购买</option>
                    </select>

                    &nbsp;&nbsp;&nbsp;
                    <label>申请时间从</label>
                    <input name="startTime" type="text" class="form_datetime" style="width: 150px;"
                           value="<#if (queryDTO??) && queryDTO.startTime ??>${queryDTO.startTime?string('yyyy-MM-dd')}</#if>">

                    <label>到</label>
                    <input name="endTime" type="text" class="form_datetime" style="width: 150px;"
                           value="<#if (queryDTO??) && queryDTO.endTime>${queryDTO.endTime?string('yyyy-MM-dd')}</#if>">

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
            <th>购买类型</th>
            <th>状态</th>
            <th>查看详情</th>
        </tr>
        </thead>
        <tbody>
        <#if pageModel?? && pageModel.records?? && (pageModel.records?size>0)>
            <#list pageModel.records as myApply>
            <td>${myApply.applyTime?string('yyyy-MM-dd')}</td>
            <td>${myApply.buyTypeName}</td>
            <td>${myApply.mngApproveStatusName}</td>
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
                <button type="button" name="closeButton" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">请 购 详 情</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <table class="table .table-condensed table-striped ">
                        <tr>
                            <td><label class="col-sm-8 control-label">申请人</label></td>
                            <td>
                                <div class="col-sm-3">
                                    <input class="form-control" readonly name="applicantName"/>
                                </div>
                            </td>
                            <td><label class="col-sm-8 control-label">部门</label></td>
                            <td>
                                <div class="col-sm-3">
                                    <input class="form-control" readonly name="dept"/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label class="col-sm-8 control-label">请购类型</label>
                            </td>
                            <td>
                                <div class="col-sm-3">
                                    <input class="form-control" readonly name="buyTypeName">
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
                                <label class="col-sm-8 control-label">审批结果</label>
                            </td>
                            <td>
                                <div class="col-sm-3">
                                    <input class="form-control" readonly name="auditResult"/>
                                </div>
                            </td>
                        </tr>
                    </table>
                </form>

                <div class="panel panel-default">
                    <table class="table" id="itemsTable">
                        <tr>
                            <th>物品名称</th>
                            <th>数量</th>
                            <th>单价</th>
                            <th>币种</th>
                            <th>总价</th>
                            <th>预计交货时间</th>
                        </tr>
                    </table>
                </div>

                <div class="panel panel-default">
                    <table class="table" id="attasTable">
                        <tr>
                            <th>附件列表</th>
                        </tr>
                    </table>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" name="closeButton" class="btn btn-default" id="closeEditButton"
                        data-dismiss="modal">关闭
                </button>
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
        format: 'yyyy-mm-dd',
        startView: 2,
        minView: 2,
        todayHighlight: true,
        todayBtn: true,
        autoclose: true
    });

    $("input[name='endTime']").datetimepicker({
        format: 'yyyy-mm-dd',
        startView: 2,
        minView: 2,
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

    function formatDate(originDate) {
        if (originDate == null) {
            return "/";
        }

        return new Date(parseInt(originDate)).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
    }

    $(document).ready(function () {

        $("a[name='showApplyDetail']").click(function () {

            var url = "/purchase/ajaxGetPurchaseDetail?purchaseId=" + selectedApplyId;

            $.ajax(url, {
                dataType: "json",
                contentType: "application/xml",
                method: "get",
                success: function (result) {
                    var applicantName = result.applicantName;
                    var applyTime = formatDate(result.applyTime);
                    var auditResult = result.mngApproveStatusName;
                    var buyTypeName = result.buyTypeName;
                    var dept = result.dept;

                    $("input[name='applicantName']").val(applicantName);
                    $("input[name='applyTime']").val(applyTime);
                    $("input[name='auditResult']").val(auditResult);
                    $("input[name='buyTypeName']").val(buyTypeName);
                    $("input[name='dept']").val(dept);

                    var purchaseAttaDTOs = result.purchaseAttaDTOs;
                    var purchaseItemDTOs = result.purchaseItemDTOs;

                    if(purchaseItemDTOs!= ""){
                        var items ="";
                        $.each(purchaseItemDTOs,function(){
                            var dto = this;
                            var item = "<tr name='item'>"
                                    + "<td>" + dto.itemName + "</td>"
                                    + "<td>" + dto.quantity + "</td>"
                                    + "<td>" + dto.unitPrice + "</td>"
                                    + "<td>" + dto.currency + "</td>"
                                    + "<td>" + dto.extendedPrice + "</td>"
                                    + "<td>" + formatDate(dto.expDelDate) + "</td>"
                                    + "</tr>";
                            items = items + item;
                        });
                        $("#itemsTable").append(items);
                    }

                    if(purchaseAttaDTOs!= ""){
                        var attas ="";
                        $.each(purchaseAttaDTOs,function(){
                            var dto = this;
                            var atta = "<tr>"
                                    + "<td><a href='" + dto.filePath + "'>" + dto.fileName + "</a></td>"
                                    + "</tr>";
                            attas = attas + atta;
                        });
                        $("#attasTable").append(attas);
                    }

                },
                error: function () {
                    alert("出错咯，稍后再试吧.");
                }
            });
        });

        $("button[name='closeButton']").click(function () {
            $.each($("#attasTable").children().children(), function () {
                var tdlength = $(this).find("td").length;
                if (tdlength > 0) {
                    this.remove();
                }
            });

            $.each($("#itemsTable").children().children(), function () {
                var tdlength = $(this).find("td").length;
                if (tdlength > 0) {
                    this.remove();
                }
            })
        });
    });

</script>
</html>
