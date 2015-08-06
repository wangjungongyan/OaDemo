<!DOCTYPE html>
<html>
<head>
    <title>Bootstrap菜单</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <!-- Bootstrap -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/font-awesome.min.css" rel="stylesheet">
    <script src="/js/jquery-1.8.2.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <style type="text/css">
        #footer {
            position: fixed;
            right: 0;
            bottom: 0;
            left: 0;
            height: 100px;
        }

        #footer #copyright {
            font-size: 0.7em;
            color: #ADADAD;
            margin: 20px 0;
            text-align: center;
        }

        #footer #copyright p {
            margin: 1px;
        }

        body {
            margin: 0;
            color: #000;
            font-size: 12px;
            font-family: "Helvetica Neue", Helvetica, STheiti, 微 软 雅 黑, 宋 体, Arial, Tahoma, sans-serif, serif;
        }

        /*左侧菜单*/
        .sidebar-menu {
            border-right: 1px solid #c4c8cb;
        }

        /*一级菜单*/
        .menu-first {
            height: 45px;
            line-height: 45px;
            background-color: mintcream;
            border-top: 1px solid #efefef;
            border-bottom: 1px solid #e1e1e1;
            padding: 0;
            font-size: 14px;
            font-weight: normal;
            text-align: center;
        }

        /*一级菜单鼠标划过状态*/
        .menu-first:hover {
            text-decoration: none;
            background-color: #d6d4d5;
            border-top: 1px solid #b7b7b7;
            border-bottom: 1px solid #acacac;
        }

        /*二级菜单*/
        .menu-second li a {
            background-color: #f6f6f6;
            height: 31px;
            line-height: 31px;
            border-top: 1px solid #efefef;
            border-bottom: 1px solid #efefef;
            font-size: 12px;
            text-align: center;
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
            height: 31px;
            line-height: 31px;
            border-top: 1px solid #83ceed;
            border-bottom: 1px solid #83ceed;
            border-right: 3px solid #f8881c;
            border-left: 3px solid #66c3ec;
            text-align: center;
        }

        /*覆盖bootstrap的样式*/
        .nav-list, .nav-list li a {
            padding: 0px;
            margin: 0px;
        }
    </style>
    <sitemesh:write property='head'/>
</head>
<body>


<!--<div class="top-div" style="height: 90px;width: 100%;border: 0px solid #ccc">-->
<!--top-->
<!--</div>-->

<div style="background-color:powderblue;height: 90px;border-bottom: 1px solid #ccc">
    <img style="height: 55px;margin: 12px 0 18px 100px;" src="/image/logo.png"/>
    <h1 style="margin-left: 700px;line-height:1px;position: relative;top: -50px;">罗尔斯.罗伊斯 <small>OA自动化办公系统</small></h1>

</div>

name---------${name}

<div class="center-div" style="width: 100%;height: auto;min-height: 500px;border: 0px solid blue;">
    <div class="left-menu-div"
         style="background-color:#eee;width: 220px;border: 0px solid red;height: 100%;float: left;position: absolute;top: 91px;border-right: 1px solid #ccc;">
        <div class="span3" style="margin-left: 1px;">
            <!--Sidebar content-->
            <div class="sidebar-menu">
                <a href="#userMeun" class="nav-header menu-first collapsed" data-toggle="collapse">
                    <i class="icon-user-md icon-large"></i> 用户管理</a>
                <ul id="userMeun" class="nav nav-list collapse menu-second" style="height: auto;">
                    <li><a href="/apply"><i class="icon-user"></i> 增加用户</a></li>
                    <li><a href="#"><i class="icon-edit"></i> 修改用户</a></li>
                    <li><a href="#"><i class="icon-trash"></i> 删除用户</a></li>
                    <li><a href="#"><i class="icon-list"></i> 用户列表</a></li>

                </ul>
                <a href="#articleMenu" class="nav-header menu-first collapsed" data-toggle="collapse">
                    <i class="icon-book icon-large"></i> 出差申请</a>
                <ul id="articleMenu" class="nav nav-list collapse menu-second" style="height: auto;">
                    <li><a href="/apply"><i class="icon-apply"></i> 我要申请</a></li>
                    <li><a href="#"><i class="icon-myApply"></i> 我的申请</a></li>
                    <li><a href="#"><i class="icon-audit"></i> 我要审批</a></li>
                    <li><a href="#"><i class="icon-my-audit"></i> 我的审批</a></li>
                </ul>
                <a href="#baoxiaoMenu" class="nav-header menu-first collapsed" data-toggle="collapse">
                    <i class="icon-book icon-large"></i> 报销申请</a>
                <ul id="baoxiaoMenu" class="nav nav-list collapse menu-second" style="height: auto;">
                    <li><a href="#"><i class="icon-"></i> 我要报销</a></li>
                    <li><a href="#"><i class="icon-myApply"></i> 我的报销</a></li>
                    <li><a href="#"><i class="icon-audit"></i> 我要审批</a></li>
                    <li><a href="#"><i class="icon-my-audit"></i> 我的审批</a></li>
                </ul>
            </div>
        </div>
        <div class="mainBody-div" style="width: 980px;border: 0px solid green;margin-left: 240px;margin-top: 10px;">
            <sitemesh:write property='body'/>
        </div>

</body>
</html>
