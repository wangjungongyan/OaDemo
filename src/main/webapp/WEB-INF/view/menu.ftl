<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />

    <link href="css/bootstrap.css" rel="stylesheet">
    <style type="text/css">
        .bs-docs-sidenav.affix {
            top: 40px;
        }

        /*  >表示第一个子元素  */
        .bs-docs-sidenav > li:first-child > a {
            -webkit-border-radius: 6px 6px 0 0;
            -moz-border-radius: 6px 6px 0 0;
            border-radius: 6px 6px 0 0;
        }
        .bs-docs-sidenav > li > a {
            display: block;
            width: 190px \9;
            margin: 0 0 -1px;
            padding: 8px 14px;
            border: 1px solid #e5e5e5;
        }

        .bs-docs-sidenav {
            width: 228px;
            margin: 30px 0 0;
            padding: 0;
            background-color: #fff;
            -webkit-border-radius: 6px;
            -moz-border-radius: 6px;
            border-radius: 6px;
            -webkit-box-shadow: 0 1px 4px rgba(0,0,0,.065);
            -moz-box-shadow: 0 1px 4px rgba(0,0,0,.065);
            box-shadow: 0 1px 4px rgba(0,0,0,.065);
        }

        .bs-docs-sidenav .icon-chevron-right {
            float: right;
            margin-top: 2px;
            margin-right: -6px;
            opacity: .25;
        }
    </style>
    <script src="../../js/jquery-1.8.0.min.js" type="text/javascript"></script>
    <script src="js/bootstrap.js" type="text/javascript"></script>
</head>
<body>

<ul class="nav nav-list bs-docs-sidenav affix-top">
    <li class="active">
        <a href="#gailang"><i class="icon-chevron-right"></i> 概览</a>
    </li>
    <li class="">
        <a href="#transitions"><i class="icon-chevron-right"></i> 过渡效果</a>
    </li>
    <li class="">
        <a href="#modals"><i class="icon-chevron-right"></i> 模态对话框</a>
    </li>
</ul>

<script type="text/javascript">
    $(function() {
        $('.nav li').click(function(e) {
            $('.nav li').removeClass('active');
            $(this).addClass('active');
        });
    });

</script>

</body>
</html>
