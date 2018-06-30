<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="../common/top.jsp"/>
    <style>
        input[type=checkbox] {
            width: 25px;
            height: 25px;
            vertical-align: middle;
        }
    </style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <jsp:include page="../common/left.jsp"/>
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">

                <div class="head layui-row">
                    <h2 class="layui-col-md4">管理员账号列表</h2>
                </div>
                <table class="layui-table">
                    <colgroup>
                        <col width="100">
                        <col width="100">
                        <col width="100">
                        
                    </colgroup>
                    <thead>
                    <tr>
                        <th>姓名</th>
                        <th>账号</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="tt">
                    <c:forEach items="${managers}" var="m">
                        <tr id="t1">
                            <td>${m.name}</td>
                            <td>${m.account}</td>
                            <td><a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" href="${pageContext.request.contextPath}/delManager?managerId=${m.id }&menuId=${menuId}" onclick="if(confirm('确定删除?')==false)return false;">删除</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            
        </div>
    </div>
</div>
<script src="/static/js/layui.all.js"></script>
<script>
    //JavaScript代码区域
    layui.use(['element', 'laypage', 'jquery', 'form'], function () {
        var element = layui.element;
        var laypage = layui.laypage;
        var $ = layui.jquery;
        var form = layui.form;

        



    });
</script>
</body>
</html>
