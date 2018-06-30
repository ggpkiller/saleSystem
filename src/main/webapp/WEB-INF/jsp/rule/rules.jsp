<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="../common/top.jsp"/>
    <style>
        td input {
            border: none;
            background: transparent;
        }
    </style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <jsp:include page="../common/left.jsp"/>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <h2>网络规则制定</h2>
            <table class="layui-table">
                <%-- <colgroup>
                    <col width="150">
                    <col width="200">
                    <col width="200">
                    <col width="200">
                    <col width="200">
                </colgroup> --%>
                <thead>
                <tr>
                    <th>规则名称</th>
                    <th>消费价格（元）</th>
                    <th>出局人数（人）</th>
                    <th>无分享总返利金额（元）</th>
                    <th>有分享直推人数</th>
                    <th>有分享下线人数</th>
                    <th>有分享总返利金额（元）</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${saleRules}" var="saleRule">
                <tr>
                    <td>${saleRule.ruleName}</td>
                    <td>${saleRule.formPrice}</td>
                    <td>${saleRule.outNumber}</td>
                    <td>${saleRule.rebate}</td>
                    <td>${saleRule.shareReferee }</td>
                    <td>${saleRule.shareUnderNumber }</td>
                    <td>${saleRule.shareRebate }</td>
                    <td>
                        <div class="layui-table-cell laytable-cell-1-9">
                         	<a class="layui-btn layui-btn-xs" lay-event="edit" href="${pageContext.request.contextPath}/sale/toDetail?ruleId=${saleRule.id}&menuId=${menuId}">查看</a>
                            <a class="layui-btn layui-btn-xs" lay-event="edit" href="${pageContext.request.contextPath}/sale/toEdit?ruleId=${saleRule.id}&menuId=${menuId}">编辑</a>
                            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" href="${pageContext.request.contextPath}/sale/del?ruleId=${saleRule.id}&menuId=${menuId}">删除</a>
                        </div>
                    </td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
            <button class="layui-btn" onclick="window.location.href='${pageContext.request.contextPath}/sale/toAddRule?menuId=${menuId}'">创建新规则</button>
        </div>
    </div>
</div>
<script src="/static/js/layui.all.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;
    });
</script>
</body>
</html>
