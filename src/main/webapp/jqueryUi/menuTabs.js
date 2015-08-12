var dynamicTabs = function(tabsId){
    var obj = new Object();
    var tabs = $( "#"+tabsId ).tabs();

    function addTab(title,url,id) {
        if(tabs.find("#"+id).length>0){
            $("#"+id+"_a").click();
            return false;
        }
        var label = title;
        var tabContentHtml =  "<iframe id='"+id+"-frameContext' scrolling='yes' frameborder='0' width='100%' height='100%' src='"+url+"' onload=iFrameHeight('"+id+"-frameContext')></iframe>";
        var li = $("<li id='"+id+"_li'><a id='"+id+"_a' href='#"+id+"'>"+label+"</a> <span class='ui-icon ui-icon-close' style='float: left'>Close</span></li>");
        tabs.find( ".ui-tabs-nav" ).append( li );
        tabs.append( "<div id='" + id + "'  style='height: 100%;'>" + tabContentHtml + "</div>" );
        tabs.tabs( "refresh" );
        $("#"+id+"_a").click();
    }
    tabs.on( "click",'span.ui-icon-close', function() {
        var panelId = $( this ).closest( "li" ).remove().attr( "aria-controls" );
        $( "#" + panelId ).remove();
        tabs.tabs( "refresh" );
    });
    obj.addTab = addTab;
    return obj;
}
var openMenu = function(tabContextId,title,url,id){
    var ttt = dynamicTabs(tabContextId);
    ttt.addTab(title,url,id);
}

