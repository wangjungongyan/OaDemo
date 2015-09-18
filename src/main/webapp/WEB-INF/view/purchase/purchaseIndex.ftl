<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta http-equiv=”content-type” content=”text/html; charset=UTF-8″/>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/font-awesome.min.css" rel="stylesheet">
    <link href="/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <style type="text/css">
        .form-horizontal .control-label {
            padding-top: 7px;
            margin-bottom: 0;
            text-align: left;
            margin-left: 50px
        }

        .form-horizontal input, .form-search textarea, .form-inline textarea, .form-horizontal textarea, .form-search select, .form-inline select, .form-horizontal select, .form-search .help-inline, .form-inline .help-inline, .form-horizontal .help-inline, .form-search .uneditable-input, .form-inline .uneditable-input, .form-horizontal .uneditable-input, .form-search .input-prepend, .form-inline .input-prepend, .form-horizontal .input-prepend, .form-search .input-append, .form-inline .input-append, .form-horizontal .input-append {
            display: inline-block;
            margin-bottom: 0;
            vertical-align: middle;
        }
    </style>
</head>

<body>

<div style="margin-top:10px;margin-left:10px;visibility:visible">
    <form class="form-horizontal form-inline" method="post" enctype="multipart/form-data" name="purchaseForm">
        <div class="col-sm-10">
            <table class="table .table-condensed table-striped ">
                <tr>
                    <td colspan="4"><label style="margin-left: 450px;" class="col-sm-40 control-label">请 购 申
                        请 详 细 信 息</label></td>
                </tr>
                <tr>
                    <td><label class="col-sm-8 control-label">申请人</label></td>
                    <td>
                        <div class="col-sm-3">
                            <input class="form-control" readonly placeholder="${employee.chineseName}">
                        </div>
                    </td>
                    <td><label class="col-sm-8 control-label">部 门</label></td>
                    <td>
                        <div class="col-sm-3">
                            <input class="form-control" readonly placeholder="${employee.deptName}">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label class="col-sm-8 control-label">
                            <span style="color:#F00">*</span>请购类型
                        </label>
                    </td>
                    <td>
                        <div class="col-sm-3">
                            <select name="buyType" id="buyType">
                                <option value="0">自己采购</option>
                                <option value="1">IT采购</option>
                            </select>
                        </div>
                    </td>
                    <td>
                        <label class="col-sm-8 control-label">
                            <span style="color:#F00">*</span>申请日期
                        </label>
                    </td>
                    <td>
                        <div class="col-sm-3">
                            <input class="form-control" placeholder="" name="applyTime" required="required">
                        </div>
                    </td>
                </tr>
            </table>
        </div>

        <table class="table  table-striped table-bordered table-hover ">
            <tr style="background-color: #eee">
                <th>编号</th>
                <th>物品名称</th>
                <th>数量</th>
                <th>单价</th>
                <th>币种</th>
                <th>总价</th>
                <th>预计交货时间</th>
            </tr>
        <#list 1..10 as i>
            <tr>
                <td>${i}</td>
                <td><input name="itemNames" id="itemName${i}"></td>
                <td><input name="quantitys" id="quantity${i}" style="width: 50px;" placeholder="1" type="number"></td>
                <td><input name="unitPrices" id="unitPrice${i}" style="width: 50px;" placeholder="0.00"></td>
                <td><input name="currencys" id="currency${i}" style="width: 50px;" placeholder="人民币"></td>
                <td><input name="extendedPrices" id="extendedPrice${i}" style="width: 70px;" placeholder="0.00"></td>
                <td><input name="expDelDates" id="expDelDate${i}"></td>
            </tr>
        </#list>
        </table>

        <div class="col-sm-10">
            <table class="table .table-condensed table-striped">
                <tr>
                    <td colspan="2"><label id="cishu" style="margin-left: 450px;" class="col-sm-40 control-label">附 件 上
                        传</label></td>
                </tr>
                <tr>
                    <td><input type="file" name="purchaseAttachments"></td>
                    <td><input class="btn btn-primary btn-lg" type="button" name="addNewPurchaseAttachment"
                               value="继续添加附件"></td>
                </tr>

            </table>
        </div>

        <p style="margin-left: 450px;">
            <button id="purchaseSubmitButton" type="submit" class="btn btn-info">
                提 交 请 购 申 请
            </button>
        </p>
    </form>

    <div class="modal fade" id="promptModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true" style="display: none">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" name="closeButton" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" style="text-align: center"></h4>
                </div>
            </div>
        </div>
    </div>

</div>
</body>

<script src="/js/jquery-1.8.2.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/bootstrap-datetimepicker.min.js"></script>
<script src="/js/date.js"></script>

<script type="text/javascript">

    $("input[name='applyTime']").datetimepicker({
        format: 'yyyy-mm-dd',
        startView: 2,
        minView: 2,
        todayHighlight: true,
        todayBtn: true,
        autoclose: true
    }).val(new Date().Format("yyyy-MM-dd"));

    $("input[name='expDelDates']").datetimepicker({
        format: 'yyyy-mm-dd',
        startView: 2,
        minView: 2,
        todayHighlight: true,
        todayBtn: true,
        autoclose: true
    });

    $(document).ready(function () {

        $("input[name='unitPrices']").keyup(function () {
            var v = $(this).val();
            if (!(/(^\d+$)/.test(v) || /(^\d+(\.)(\d+))|(^\d+)$/.test(v))) {
                alert("单价只能请输入数字哦");
                $(this).val("");
            }
        });

        $("input[name='extendedPrices']").keyup(function () {
            var v = $(this).val();
            if (!(/(^\d+$)/.test(v) || /(^\d+(\.)(\d+))|(^\d+)$/.test(v))) {
                alert("总价只能请输入数字哦");
                $(this).val("");
            }
        });

        $("input[name='addNewPurchaseAttachment']").live("click", function () {
            var newPurchaseAttachment = $(this).parent().parent();
            newPurchaseAttachment.after(newPurchaseAttachment.clone());
        });

        $("#purchaseSubmitButton").click(function (e) {
            $(this).attr("action", "/purchase/apply");

            for (var i = 1; i <= 10; i++) {
                var itemName = $("#itemName" + i).val();
                var quantity = $("#quantity" + i).val();
                var unitPrice = $("#unitPrice" + i).val();
                var currency = $("#currency" + i).val();
                var extendedPrice = $("#extendedPrice" + i).val();

                if ((itemName == "" && quantity == "" && unitPrice == "" && currency == "" && extendedPrice == "") || (itemName != "" && quantity != "" && unitPrice != "" && currency != "" && extendedPrice != "")) {
                    continue;
                } else {
                    e.preventDefault();
                    $(".modal-title").html("第" + i + "行有未完善的数据");
                    $("#promptModal").modal('show');
                }
            }

            $(this).submit();

        });
    });

</script>

</html>
