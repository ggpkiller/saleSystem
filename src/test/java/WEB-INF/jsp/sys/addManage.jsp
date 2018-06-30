<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="../common/top.jsp"/>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
   	<jsp:include page="../common/left.jsp" />
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <div class="layui-row">
                <h2 class="layui-col-md4">添加管理员</h2>
            </div>
            <form class="layui-form" action="${pageContext.request.contextPath}/addManager?menuId=${menuId }" method="post">
                <div class="layui-field-box">
                	<div class="layui-form-item">
                        <div class="layui-col-md4">
                            <label class="layui-form-label">姓名</label>
                            <div class="layui-input-block">
                                <input type="text" name="name" required lay-verify="required" placeholder="请输入管理员姓名"
                                       autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    </div>
                
                    <div class="layui-form-item">
                        <div class="layui-col-md4">
                            <label class="layui-form-label">账号</label>
                            <div class="layui-input-block">
                                <input type="text" name="account" required lay-verify="required" placeholder="请输入管理员登录账号"
                                       autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-col-md4">
                            <label class="layui-form-label">密码</label>
                            <div class="layui-input-block">
                                <input id="phone" type="text" name="password" required lay-verify="required"
                                       placeholder="请输入管理员登录密码"
                                       autocomplete="off" class="layui-input"
                                       maxlength="20">
                            </div>
                        </div>
                    </div>
                    <fieldset class="layui-elem-field">
                        <legend>管理员权限</legend>
                        <div class="layui-field-box">
                            <c:forEach items="${rights }" var="right">
                            	<input type="checkbox" name="rightArry" title="${right.name }" checked value="${right.id }">
                            </c:forEach>
                        </div>
                    </fieldset>
                    <div class="layui-form-item" style="margin-top:20px">
                        <button class="layui-btn" lay-submit lay-filter="formDemo">提交</button>
                        
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>
<script src="/static/js/layui.all.js"></script>

<script>
    //JavaScript代码区域
    layui.use(['element', 'jquery'], function () {
        var element = layui.element;
        var $ = layui.jquery;
        $("#phone").on("input", function (a) {
            $("#account").val(a.currentTarget.value)
        })
    });
</script>
</body>
</html>
