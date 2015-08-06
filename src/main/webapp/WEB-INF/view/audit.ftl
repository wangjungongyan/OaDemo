<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body>

<div>
    <form class="form-inline">
        <div class="form-group">
            <label for="exampleInputName2">审批人</label>&nbsp;&nbsp;
            <input type="text" class="form-control" id="exampleInputName2" placeholder="王俊">&nbsp;&nbsp;
            <label for="exampleInputEmail2">申请时间</label>&nbsp;&nbsp;
            <input type="email" class="form-control" id="exampleInputEmail2" placeholder="2015-08-09">&nbsp;&nbsp;
            <button type="submit" class="btn btn-default">过  滤</button>
        </div>
        <div class="form-group">

        </div>

    </form>
</div>

<table class="table table-bordered table-hover">
    <tr style="background-color: #dff0d8">
        <td><label class="col-sm-5 control-label">申请人</label></td>
        <td><label class="col-sm-10 control-label">申请时间</label></td>
        <td><label class="col-sm-5 control-label">类型</label></td>
        <td><label class="col-sm-5 control-label">费用总计(元)</label></td>
        <td><label class="col-sm-5 control-label">查看审批</label></td>
    </tr>
    <tr>
        <td><label class="col-sm-5 control-label">王俊</label></td>
        <td><label class="col-sm-10 control-label">2015-08-01 02:09:00</label></td>
        <td><label class="col-sm-5 control-label">国际</label></td>
        <td><label class="col-sm-5 control-label">20000.00</label></td>
        <td>
            <a href="javascript:void(0)" data-toggle="modal"
               data-target="#editModal">点击审批</a></td>
        </td>
    </tr>
    <tr>
        <td><label class="col-sm-5 control-label">王俊</label></td>
        <td><label class="col-sm-10 control-label">2015-08-01 02:09:00</label></td>
        <td><label class="col-sm-5 control-label">国际</label></td>
        <td><label class="col-sm-5 control-label">20000.00</label></td>
        <td>
            <a href="javascript:void(0)" onclick="auditApply()" data-toggle="modal"
               data-target="#editModal">点击审批</a></td>
        </td>
    </tr>
    <tr>
        <td><label class="col-sm-5 control-label">王俊</label></td>
        <td><label class="col-sm-10 control-label">2015-08-01 02:09:00</label></td>
        <td><label class="col-sm-5 control-label">国际</label></td>
        <td><label class="col-sm-5 control-label">20000.00</label></td>
        <td>
            <button type="button" class="btn btn-info">点击审批</button>
        </td>
    </tr>
    <tr>
        <td><label class="col-sm-5 control-label">王俊</label></td>
        <td><label class="col-sm-10 control-label">2015-08-01 02:09:00</label></td>
        <td><label class="col-sm-5 control-label">国际</label></td>
        <td><label class="col-sm-5 control-label">20000.00</label></td>
        <td>
            <button type="button" class="btn btn-info">点击审批</button>
        </td>
    </tr>
    <tr>
        <td><label class="col-sm-5 control-label">王俊</label></td>
        <td><label class="col-sm-10 control-label">2015-08-01 02:09:00</label></td>
        <td><label class="col-sm-5 control-label">国际</label></td>
        <td><label class="col-sm-5 control-label">20000.00</label></td>
        <td>
            <button type="button" class="btn btn-info">点击审批</button>
        </td>
    </tr>
    <tr>
        <td><label class="col-sm-5 control-label">王俊</label></td>
        <td><label class="col-sm-10 control-label">2015-08-01 02:09:00</label></td>
        <td><label class="col-sm-5 control-label">国际</label></td>
        <td><label class="col-sm-5 control-label">20000.00</label></td>
        <td>
            <button type="button" class="btn btn-info">点击审批</button>
        </td>
    </tr>
    <tr>
        <td><label class="col-sm-5 control-label">王俊</label></td>
        <td><label class="col-sm-10 control-label">2015-08-01 02:09:00</label></td>
        <td><label class="col-sm-5 control-label">国际</label></td>
        <td><label class="col-sm-5 control-label">20000.00</label></td>
        <td>
            <button type="button" class="btn btn-info">点击审批</button>
        </td>
    </tr>
    <tr>
        <td><label class="col-sm-5 control-label">王俊</label></td>
        <td><label class="col-sm-10 control-label">2015-08-01 02:09:00</label></td>
        <td><label class="col-sm-5 control-label">国际</label></td>
        <td><label class="col-sm-5 control-label">20000.00</label></td>
        <td>
            <button type="button" class="btn btn-info">点击审批</button>
        </td>
    </tr>
    <tr>
        <td><label class="col-sm-5 control-label">王俊</label></td>
        <td><label class="col-sm-10 control-label">2015-08-01 02:09:00</label></td>
        <td><label class="col-sm-5 control-label">国际</label></td>
        <td><label class="col-sm-5 control-label">20000.00</label></td>
        <td>
            <a href="javascript:void(0)" onclick="auditApply()" data-toggle="modal"
               data-target="#editModal">点击审批</a></td>
        </td>
    </tr>
</table>

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

<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="height: auto; width: 700px; margin-left: -350px;">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">开始审批</h4>
            </div>
            <div class="modal-body">
                <table class="table .table-condensed table-striped ">
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
                                <input type="gongzuocantime" class="form-control" id="inputgongzuocantime" placeholder="0.00">
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
                        </td>
                        <td></td>
                    </tr>
                    </p>

                </table>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-info" id="addButton">审核通过</button>
                <button type="button" class="btn btn-danger" id="addButton">打回重填</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>


<script src="/js/jquery-1.8.2.min.js"></script>
<script src="/js/bootstrap.min.js"></script>

</body>

<script type="text/javascript">
//    $(document).ready(function(){
//        $('#editModal').css(width: 'auto',"width","700px");
//    });

</script>

</html>
