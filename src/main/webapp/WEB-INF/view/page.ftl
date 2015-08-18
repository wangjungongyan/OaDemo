
<#function cutString str,len>
    <#if str?length &gt; len>
        <#return str?substring(0,len?int) + "...">
    <#else>
        <#return str>
    </#if>
</#function>

<#macro pageNavigation pageModel args>

<#--
    TODO:第一页不需要传递参数pageno，后台处理的时候pageno默认为1
-->
    <#if pageModel?exists && (pageModel.totalCount > 1)>
        <#assign curPage = pageModel.currentPage>
        <#assign pageCount = pageModel.totalCount>
        <#assign endPage = pageCount/pageModel.pageSize + 1>
    </#if>

    <#if (curPage-2 >0)>
        <#assign headPage = (curPage-2) >
    <#else>
        <#assign headPage = 1 >
    </#if>

    <#if (curPage+2 <=endPage)>
        <#assign head2Page = (curPage+2) >
    <#else>
        <#assign head2Page = endPage >
    </#if>

<#--curPage:${curPage}-->
<#--headPage:${headPage}-->
<#--headPage2:${headPage2}-->
<#--endPage:${endPage}-->

    <#if (curPage > 1) >
    <li><a href="?pageNo=${curPage - 1}${args!}" class="page-prev" title="上一页"><i class="p-prev"></i>上一页</a></li>
    </#if>

    <#list headPage..head2Page as page>
        <#if curPage == page>
        <span>${page}</span>
        <#else>
        <li><a href="?pageNo=${page}${args!}" title="${page}">${page}</a></li>
        </#if>
    </#list>

    <#if (curPage < endPage)>
    <li><a href="?pageNo=${curPage + 1}${args!}" class="page-next" title="下一页">下一页<i class="p-next"></a></li>
    </#if>

</#macro>
