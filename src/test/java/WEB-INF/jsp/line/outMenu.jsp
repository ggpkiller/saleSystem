<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<jsp:include page="../common/top.jsp" />
<style>
.content {
	margin-top: 20px;
	display: flex;
	flex-direction: row;
	justify-content: space-around;
}

.item {
	width: 300px;
	height: 250px;
	border: 5px solid #fff;
	margin: 0 auto;
	border-radius: 10px;
	text-align: center;
	color: #fff;
	display: inline-block;
	cursor: pointer;
}

.item:hover {
	border-color: lightgray;
}

.info {
	margin-top: 80px;
}

.info h3 {
	font-size: 30px;
}

.info span {
	display: block;
	font-size: 20px;
}
</style>
</head>

<body>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <jsp:include page="../common/left.jsp"/>

    <div class="layui-body">
        <c:set var="colors" value="${['#56A36C','#5E8579','#77C34F','#56A36C','#5E8579','#77C34F','#56A36C','#5E8579','#77C34F','#56A36C','#5E8579','#77C34F','#56A36C','#5E8579','#77C34F','#56A36C','#5E8579','#77C34F','#56A36C','#5E8579','#77C34F','#56A36C','#5E8579','#77C34F','#56A36C','#5E8579','#77C34F','#56A36C','#5E8579','#77C34F','#56A36C','#5E8579','#77C34F','#56A36C','#5E8579','#77C34F']}"/>
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <h2>公排出局</h2>
            <div class="content">
                <c:forEach items="${rules}" var="rule" varStatus="status">
                <div class="item" style="background: ${colors[status.index]}" <c:if test="${rule.out != null }">onclick="window.location.href='${pageContext.request.contextPath}/form/outDetail?menuId=${menuId}&ruleId=${rule.id}'"</c:if>>
                    <div class="info">
                        <h3>${rule.ruleName}</h3>
                        <c:if test="${rule.out != null }"><span>下次出局：${rule.out.name }</span></c:if>
                        <c:if test="${rule.out == null }"><span>下次出局：无</span></c:if>
                    </div>
                </div>
                </c:forEach>
            </div>
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
</body>
</html>