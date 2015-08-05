<!DOCTYPE html>
<html>
<head>
    <title>Bootstrap菜单</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <script src="js/jquery-1.8.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">

        body {
            margin: 0;
            color: #000;
            font-size: 12px;
            font-family: "Helvetica Neue",Helvetica,STheiti,微软雅黑,宋体,Arial,Tahoma,sans-serif,serif;
        }
        /*左侧菜单*/
        .sidebar-menu{
            border-right: 1px solid #c4c8cb;
        }
        /*一级菜单*/
        .menu-first{
            height:45px;
            line-height:45px;
            background-color: mintcream;
            border-top: 1px solid #efefef;
            border-bottom: 1px solid #e1e1e1;
            padding: 0;
            font-size: 14px;
            font-weight: normal;
            text-align: center;
        }
        /*一级菜单鼠标划过状态*/
        .menu-first:hover{
            text-decoration: none;
            background-color: #d6d4d5;
            border-top: 1px solid #b7b7b7;
            border-bottom: 1px solid #acacac;
        }
        /*二级菜单*/
        .menu-second li a{
            background-color: #f6f6f6;
            height:31px;
            line-height:31px;
            border-top: 1px solid #efefef;
            border-bottom: 1px solid #efefef;
            font-size: 12px;
            text-align:center;
        }
        /*二级菜单鼠标划过样式*/
        .menu-second li a:hover {
            text-decoration: none;
            background-color: #66c3ec;
            border-top: 1px solid #83ceed;
            border-bottom: 1px solid #83ceed;
            border-right: 3px solid #f8881c;
            border-left: 3px solid #66c3ec;
        }
        /*二级菜单选中状态*/
        .menu-second-selected {
            background-color: #66c3ec;
            height:31px;
            line-height:31px;
            border-top: 1px solid #83ceed;
            border-bottom: 1px solid #83ceed;
            border-right: 3px solid #f8881c;
            border-left: 3px solid #66c3ec;
            text-align:center;
        }
        /*覆盖bootstrap的样式*/
        .nav-list,.nav-list li a{
            padding: 0px;
            margin: 0px;
        }
    </style>
</head>
<body>

<div class="center-div" style="width: 100%;height: auto;min-height: 500px;border: 0px solid blue;">
    <div class="left-menu-div" style="background-color:#eee;width: 220px;border: 0px solid red;height: 100%;float: left;position: absolute;top: 91px;border-right: 1px solid #ccc;">
        <div class="span3" style="margin-left: 1px;">
            <!--Sidebar content-->
            <div class="sidebar-menu">
                <a href="#userMeun" class="nav-header menu-first collapsed" data-toggle="collapse">
                    <i class="icon-user-md icon-large"></i> 用户管理</a>
                <ul id="userMeun" class="nav nav-list collapse menu-second">
                    <li><a href="#"><i class="icon-user"></i> 增加用户</a></li>
                    <li><a href="#"><i class="icon-edit"></i> 修改用户</a></li>
                    <li><a href="#"><i class="icon-trash"></i> 删除用户</a></li>
                    <li><a href="#"><i class="icon-list"></i> 用户列表</a></li>

                </ul>
                <a href="#articleMenu" class="nav-header menu-first collapsed" data-toggle="collapse">
                    <i class="icon-book icon-large"></i> 文章管理</a>
                <ul id="articleMenu" class="nav nav-list collapse menu-second">
                    <li><a href="#"><i class="icon-pencil"></i> 添加文章</a></li>
                    <li><a href="#"><i class="icon-list-alt"></i> 文章列表</a></li>
                </ul>
            </div>

        </div>
    </div>
</div>

</body>
</html>
