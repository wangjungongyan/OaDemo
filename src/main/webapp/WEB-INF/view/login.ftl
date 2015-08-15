<!DOCTYPE html>
<html>
<head>

</head>
<body style="margin: 0px">
<div style="background-color:mintcream;height: 90px;border-bottom: 1px solid #ccc">
    <img style="height: 55px;margin: 12px 0 18px 100px;" src="/image/logo.png"/>
    <h1 style="margin-left: 700px;line-height:1px;position: relative;top: -50px;">罗尔斯.罗伊斯 <small>OA自动化办公系统</small></h1>
</div>
<div style="position: fixed;
    bottom: 0;
    top: 91px;">
    <img name="background" src="/image/background.png" style="width: 100%;height: 100%;">
    <div style="border: 1px solid #fff;
    border-radius: 5px;
    background-color: rgba(235, 235, 235, 0.4);
    width: 350px;
    position: fixed;
    top: 22%;
    right: 50px;
    height: 300px;">
        <div style=" margin-top: 30px;
                margin-left: 50px;
                font-size: 18px;
                font-weight: bold;">
            快速登录
        </div>
        <form action="loginVerify" method="post">
            <div>
                <input name="loginName" style="height: 25px;
                                width: 290px;
                                margin-left: 25px;
                                margin-top: 35px;
                                border-radius: 3px;
                                border: 1px solid #ccc;" placeholder="请输入用户名" value="vali@ll.com">
            </div>
            <div>
                <input name="passWord" type="password" style="height: 25px;
                                width: 290px;
                                margin-left: 25px;
                                margin-top: 35px;
                                border-radius: 3px;
                                border: 1px solid #ccc;" placeholder="请输入密码" value="123456">
            </div>
            <div>
                    <input type="submit" style="height: 35px;
                            width: 290px;
                            margin-left: 25px;
                            margin-top: 35px;
                            border-radius: 3px;
                            border: 1px solid #ccc;
                            background-color: red;
                            line-height: 31px;cursor: pointer" value="登录">
            </div>
        </form>
    </div>
</div>
msg: '${msg}'

</body>
</html>
