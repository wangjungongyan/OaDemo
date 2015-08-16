<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/font-awesome.min.css" rel="stylesheet">
    <script src="/js/jquery-1.8.2.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <style type="text/css">
        .form-horizontal .control-label {
            padding-top: 7px;
            margin-bottom: 0;
            text-align: left;}
    </style>
</head>

<body>

<div style="margin-top:10px;margin-left:10px;visibility:visible">
    <form class="form-horizontal form-inline">
        <div class="col-sm-10">
            <table class="table .table-condensed table-striped ">
                <tr>
                    <td colspan="4"><label id="cishu" style="margin-left: 300px;" class="col-sm-40 control-label">出 差 申 请 详 细 信 息</label></td>
                </tr>
                <tr>
                    <td><label class="col-sm-8 control-label">申请人</label></td>
                    <td>
                        <div class="col-sm-3">
                            <input type="gongzuocantime" class="form-control" id="inputgongzuocantime" placeholder="">
                        </div>
                    </td>
                    <td><label class="col-sm-8 control-label">申请时间</label></td>
                    <td>
                        <div class="col-sm-3">
                            <input type="gongzuocantime" class="form-control" id="inputgongzuocantime" placeholder="2015-09-01">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td><label class="col-sm-8 control-label">类  型</label></td>
                    <td>
                        <div class="col-sm-3">
                            <input type="gongzuorijiaotongfeitime" class="form-control" id="inputgongzuorijiaotongfeitime" placeholder="">
                        </div>
                    </td>
                    <td><label class="col-sm-8 control-label">目的地</label></td>
                    <td>
                        <div class="col-sm-3">
                            <input type="gongzuorijiaotongfeitime" class="form-control" id="inputgongzuorijiaotongfeitime" placeholder="">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td><label class="col-sm-8 control-label">出差时间</label></td>
                    <td>
                        <div class="col-sm-3">
                            <input type="gongzuocantime" class="form-control" id="inputgongzuocantime" placeholder="2015-08-09">
                        </div>
                    </td>
                    <td><label class="col-sm-8 control-label">仓 位</label></td>
                    <td>
                        <div class="col-sm-3">
                            <input type="gongzuocantime" class="form-control" id="inputgongzuocantime" placeholder="0">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td><label class="col-sm-8 control-label">行 程</label></td>
                    <td>
                        <div class="col-sm-3">
                            <input type="gongzuorijiaotongfeitime" class="form-control" id="inputgongzuorijiaotongfeitime" placeholder="">
                        </div>
                    </td>
                    <td><label class="col-sm-8 control-label">目的地</label></td>
                    <td>
                        <div class="col-sm-3">
                            <input type="gongzuorijiaotongfeitime" class="form-control" id="inputgongzuorijiaotongfeitime" placeholder="">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td><label class="col-sm-10 control-label">机票预算(元)</label></td>
                    <td>
                        <div class="col-sm-3">
                            <input type="gongzuocantime" class="form-control" id="inputgongzuocantime" placeholder="0.00">
                        </div>
                    </td>
                    <td><label class="col-sm-8 control-label">宾馆房价(元)</label></td>
                    <td>
                        <div class="col-sm-3">
                            <input type="gongzuocantime" class="form-control" id="inputgongzuocantime" placeholder="0.00">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td><label class="col-sm-8 control-label">天 数</label></td>
                    <td>
                        <div class="col-sm-3">
                            <input type="gongzuorijiaotongfeitime" class="form-control" id="inputgongzuorijiaotongfeitime" placeholder="0.00">
                        </div>
                    </td>
                    <td><label class="col-sm-8 control-label">住宿预算(元)</label></td>
                    <td>
                        <div class="col-sm-3">
                            <input type="gongzuorijiaotongfeitime" class="form-control" id="inputgongzuorijiaotongfeitime" placeholder="0.00">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td><label class="col-sm-10 control-label">其它费用(元)</label></td>
                    <td>
                        <div class="col-sm-3">
                            <a href="applyList">
                            <input type="gongzuocantime" class="form-control" id="inputgongzuocantime" placeholder="0.00">
                            </a>
                        </div>
                    </td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td ><label style="margin-left: 1px;" class="col-sm-40 control-label">总计(元)</label></td>
                    <td>
                        <div class="col-sm-10">
                            <input type="total" class="form-control" id="inputtotal" placeholder="0.00">
                        </div>
                    </td>
                    <td>
                        <p>
                            <a href="applyList">
                            <button type="button" class="btn btn-info">
                                提 交 报 销 申 请
                            </button>
                            </a>
                        </p>
                    </td>
                    <td></td>
                </tr>
                </p>

            </table>
        </div>
    </form>
</div>
</body>
<script type="text/javascript">
</script>

</html>
