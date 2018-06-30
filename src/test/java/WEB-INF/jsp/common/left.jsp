<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <div class="layui-header">
        <div class="layui-logo" style="font-size: x-large;font-weight: bolder;margin-left: 4em;letter-spacing: 4px;width:30%;text-align: left">中谷久福-新经济管理系统</div>
        <ul class="layui-nav layui-layout-right"><span style="font-size: small;">欢迎管理员【${manager.name}】</span>
        <li class="layui-nav-item">
            <a href="${pageContext.request.contextPath}/signOut">退出登录</a>
        </li>
    </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">

            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <c:if test="${ sessionScope.menuList != null }">
                <!-- 侧边导航: <ul class="layui-nav layui-nav-tree layui-nav-side"> -->
                    <c:forEach items="${sessionScope.menuList}" var="menu">
                    <li class="layui-nav-item <c:if test='${menu.id == menuId}'>layui-nav-itemed</c:if>">
                        <a href="javascript:;">${menu.name}</a>
                        <dl class="layui-nav-child">
                        <c:forEach items="${menu.children}" var="child">
                            <dd><a href="${pageContext.request.contextPath}/${child.href}?menuId=${menu.id}">${child.name}</a></dd>
                        </c:forEach>
                        </dl>
                    </li>
                    </c:forEach>
                </c:if>
            </ul>
        </div>
    </div>

<script>
    //JavaScript代码区域
    layui.use(['element','jquery','form'], function () {
        var element = layui.element;
        
    });
    
    $(document).ready(function(){
    		$('.layui-nav-item').click(function(){
    			var thisCLass = $(this).attr("class")
    			$(this).attr("class","layui-nav-item layui-nav-itemed")
            $(this).siblings().attr("class","layui-nav-item")
        })
    })
</script>

