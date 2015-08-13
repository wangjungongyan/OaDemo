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
            <div class="body-top-login-user-div">褰������诲����ㄦ��:妯�甯�</div>
        </div>
        <div class="body-center-div" >
            <div class="left-menu-div" >
                    <!--Sidebar content-->
                <div class="sidebar-menu">
                    <a href="#oneMenuId_001" class="nav-header menu-first collapsed" data-toggle="collapse">缃�绔�</a>
                    <ul id="oneMenuId_001" class="nav nav-list collapse menu-second" >
                        <li><a href="javascript:void(0)" onclick="openMenu('menuTabsContent','��惧害','/apply','1005');">��惧害</a></li>
                        <li><a href="javascript:void(0)" onclick="openMenu('menuTabsContent','126���绠�','/applyList','1006');">126���绠�</a></li>
                        <li><a href="javascript:void(0)" onclick="openMenu('menuTabsContent','126���绠�','/audit','1006');">126���绠�</a></li>
                    </ul>
                    <a href="#oneMenuId_002" class="nav-header menu-first collapsed" data-toggle="collapse">渚�瀛�</a>
                    <ul id="oneMenuId_002" class="nav nav-list collapse menu-second" >
                        <li><a href="javascript:void(0)" onclick="openMenu('menuTabsContent','���浠剁�＄��','http://localhost:8080/weixin/uploadMediaIndex.action','1007');">���瑕���宠��</a></li>
                        <li><a href="javascript:void(0)" onclick="openMenu('menuTabsContent','绱����绠＄��','http://localhost:8080/weixin/uploadMaterialIndex.action','1008');">��������宠��</a></li>
                        <li><a href="javascript:void(0)" onclick="openMenu('menuTabsContent','���瑕�瀹℃��','http://localhost:8080/weixin/uploadMediaIndex.action','1009');">���瑕�瀹℃��</a></li>
                    </ul>
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
                        <ul class="tabs-title-ul" >
                        </ul>
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

