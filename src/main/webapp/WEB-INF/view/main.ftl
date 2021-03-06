<!DOCTYPE html>
<html>
    <head>
        <title>main menu</title>
        <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
        <link href="/css/bootstrap.min.css" rel="stylesheet">
        <link href="/css/font-awesome.min.css" rel="stylesheet">
        <link href="/jqueryUi/jquery-ui-1.10.0.custom.css" rel="stylesheet">
        <link href="/css/mainFrame.css" rel="stylesheet">
    </head>
    <body>
        <div class="body-top-content-div">
            <img style="height: 55px;margin: 12px 0 18px 100px;" src="/image/logo.png"/>
            <div class="body-top-login-user-div">
                当前登录用户【${loginUser.chineseName}】
                <a href="/logOut">我要退出</a>
            </div>
        </div>
        <div class="body-center-div" >
            <div class="left-menu-div" >
                    <!--Sidebar content-->
                <div class="sidebar-menu">
                    <#list menus as menu>
                        <a href="#${menu.href}" class="nav-header menu-first collapsed" data-toggle="collapse">${menu.name}</a>
                        <ul id="${menu.href}" class="nav nav-list collapse menu-second" >
                            <#if menu.secondMenus??>
                                <#list menu.secondMenus as secondMenu>
                                    <li><a href="javascript:void(0)" onclick="openMenu('menuTabsContent','${secondMenu.name}','${secondMenu.href}','${secondMenu.index}');">${secondMenu.name}</a></li>
                                </#list>
                            </#if>
                        </ul>
                    </#list>
                </div>
            </div>
            <div class="mainBody-div"  >
                <div class="mainBody-div-main" >
                    <div class="menuTabsContent" id="menuTabsContent" >
                        <ul class="tabs-title-ul"/>
                    </div>
                </div>
            </div>
        </div>
        <script src="/js/jquery-1.8.2.min.js"></script>
        <script src="/js/bootstrap.min.js"></script>
        <script src="/jqueryUi/jquery-ui-1.10.0.custom.min.js"></script>
        <script src="/jqueryUi/menuTabs.js"></script>

        <script type="text/javascript" language="javascript">

            function iFrameHeight(id) {

                var ifm= document.getElementById("id");
                var subWeb = document.frames ? document.frames["afasfasfasf"].document : ifm.contentDocument;
                if(ifm != null && subWeb != null) {
                    ifm.height = subWeb.body.scrollHeight;
                }
                /*
                 var frame = $("#"+id);
                 frame.height(frame.contents().find("body").height() + 40);
                 */
            }
        </script>
    </body>
</html>

