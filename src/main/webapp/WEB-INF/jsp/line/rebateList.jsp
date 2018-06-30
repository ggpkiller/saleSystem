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
                    <a style="cursor: pointer;margin-right: 2em" onclick="history.back()">[--返回--]</a><br><br><h2 class="layui-col-md4">定拨队列--${rule.ruleName}</h2>
                    <div class="layui-col-md4 layui-col-md-offset4">
                        <div class="layui-input-inline" style="width: 80%;">
                            <input type="text" name="price_min" placeholder="请输入要搜索的内容" autocomplete="off"
                                   class="layui-input">
                        </div>
                        <div class="layui-input-inline" style="width: 10%;">
                            <button class="layui-btn">搜索</button>
                        </div>
                    </div>
                </div>
                <table class="layui-table">
                    <colgroup>
                        <col width="80">
                        <col width="100">
                        <col width="250">
                        <col width="200">
                        <col width="200">
                        <col width="200">
                    </colgroup>
                    <thead>
                    <tr>
                        <th>排名</th>
                        <th>姓名</th>
                        <th>已推荐人数</th>
                        <th>满足条件</th>
                    </tr>
                    </thead>
                    <tbody id="tt">
                    <c:forEach items="${list}" var="line">
                        <tr id="t1">
                            <td>${line.currentNumber}</td>
                            <td>${line.name}</td>
                            <td>${line.refereeCount}&nbsp;&nbsp;&nbsp;&nbsp;人</td>
                            <td>${line.condition}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            <div id="page" style="text-align: center"></div>
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

        laypage.render({
            elem: 'page', //注意，这里的 test1 是 ID，不用加 # 号
            count: <c:if test="${counter != null}">${counter}</c:if><c:if test="${counter == null}">0</c:if> ,
            limit: <c:if test="${pageShow != null}">${pageShow}</c:if><c:if test="${pageShow == null}">0</c:if> ,
            curr: <c:if test="${pageIndex != null}">${pageIndex}</c:if><c:if test="${pageIndex == null}">1</c:if> ,
            jump: function (obj, first) {
                if (!first) {
                    window.location.href = '${pageContext.request.contextPath}/form/verifyList?pageIndex='+obj.curr;
                }
            }
        });



    });
</script>
</body>
</html>
