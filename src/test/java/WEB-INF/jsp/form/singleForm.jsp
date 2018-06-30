<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
   <jsp:include page="../common/top.jsp"/>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <jsp:include page="../common/left.jsp"/>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <div class="layui-row">
                <a style="cursor: pointer;margin-right: 2em" onclick="history.back()">[--返回--]</a><br><br><h2 class="layui-col-md4">报单审核</h2>
            </div>
            <form class="layui-form" action="${pageContext.request.contextPath}/form/verifyForm?menuId=${menuId}" method="post">
                <div class="layui-field-box">
                    <fieldset class="layui-elem-field">
                        <legend>基本信息</legend>
                        <div class="layui-field-box">
                            <div class="layui-form-item">
                                <div class="layui-col-md4">
                                    <label class="layui-form-label">姓名</label>
                                    <span class="layui-form-label" style="text-align: left;width: auto;">${form.name}</span>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <div class="layui-col-md4">
                                    <label class="layui-form-label">手机号</label>
                                    <span class="layui-form-label"
                                          style="text-align: left;width: auto;">${form.mobile}</span>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <div class="layui-col-md4">
                                    <label class="layui-form-label">身份证号</label>
                                    <span class="layui-form-label"
                                          style="text-align: left;width: auto;">${form.idNumber}</span>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <div class="layui-col-md4">
                                    <label class="layui-form-label">现居住地址</label>
                                    <span class="layui-form-label" style="text-align: left;width: auto;">${form.address}</span>

                                </div>
                            </div>
                            <div class="layui-form-item">
                                <div class="layui-col-md4">
                                    <label class="layui-form-label">开户行名称</label>
                                    <span class="layui-form-label" style="text-align: left;width: auto;">${form.bankName}</span>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <div class="layui-col-md4">
                                    <label class="layui-form-label">银行卡号</label>
                                    <span class="layui-form-label"
                                          style="text-align: left;width: auto;">${form.bankAccount}</span>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                    <c:if test="${form.isMember == 'N'}">
                    <fieldset class="layui-elem-field">

                        <legend>新会员账号</legend><label style="color: red;margin-left: 4em">该手机号未注册，审核通过将分配账号</label>
                        <div class="layui-field-box">
                            <div class="layui-form-item">
                                <div class="layui-col-md4">
                                    <label class="layui-form-label">会员账号</label>
                                    <span class="layui-form-label"
                                          style="text-align: left;width: auto;">${form.mobile}</span>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <div class="layui-col-md4">
                                    <label class="layui-form-label">账号密码</label>
                                    <span class="layui-form-label" style="text-align: left;width: auto;">123456</span>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                </div>
                </c:if>
                <c:if test="${form.isMember == 'Y'}">
                    <span style="color: red;margin-left: 2em">该手机号已经是系统会员，不分配账号</span>
                </c:if>
                <input type="hidden" name="id" value="${form.id}">
                <div class="layui-form-item">
                    <button class="layui-btn" lay-submit lay-filter="formDemo">审核通过</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="js/layui.all.js"></script>
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
