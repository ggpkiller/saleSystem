<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                <h2 class="layui-col-md4">App管理</h2>
            </div>
            <form class="layui-form" action="${pageContext.request.contextPath}/sys/addApp?menuId=${menuId}" method="post">
                <div class="layui-field-box">
                    <fieldset class="layui-elem-field">
                        <legend>最新版本</legend>
                        <div class="layui-field-box">
                            <div class="layui-form-item">
                                <div class="layui-col-md4">
                                    <label class="layui-form-label">版本号</label>
                                    <span class="layui-form-label" style="text-align: left;width: auto;">${app.version}</span>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <div class="layui-col-md4">
                                    <label class="layui-form-label">下载地址</label>
                                    <span class="layui-form-label"
                                          style="text-align: left;width: auto;">${app.url}</span>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <div class="layui-col-md4">
                                    <label class="layui-form-label">描述</label>
                                    <div class="layui-input-block">
                                        <textarea name="desc" class="layui-textarea" readonly="readonly" style="resize: none">${app.introduce}</textarea>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <div class="layui-col-md4">
                                    <label class="layui-form-label">是否强制</label>

                                    <span class="layui-form-label"
                                          style="text-align: left;width: auto;">
                                        <c:if test="${app.forceStatus == 'Y'}">是</c:if>
                                        <c:if test="${app.forceStatus == 'N'}">否</c:if>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                    <fieldset class="layui-elem-field">
                        <legend>版本登记</legend>
                        <div class="layui-field-box">
                            <div class="layui-row layui-form-item">
                                <div class="layui-col-md6 long-font">
                                    <label class="layui-form-label">下载地址</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="url" required lay-verify="required"
                                               placeholder="以http://或https://开头的全地址"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md6 long-font">
                                    <label class="layui-form-label">强制升级</label>
                                    <div class="layui-input-block">
                                        <input type="radio" name="forceStatus" value="N" title="否" checked>
                                        <input type="radio" name="forceStatus" value="Y" title="是">
                                    </div>
                                </div>
                            </div>
                            <div class="layui-row layui-form-item">
                                <div class="layui-col-md6 long-font">
                                    <label class="layui-form-label">版本描述</label>
                                    <div class="layui-input-block">
                                        <textarea name="introduce" class="layui-textarea" placeholder="请填写版本描述" style="resize: none"></textarea>
                                    </div>
                                </div>
                                <div class="layui-col-md6 long-font">
                                    <label class="layui-form-label">版本号</label>
                                    <div class="layui-input-block">
                                        <input name="version" required lay-verify="required|number" class="layui-input" type="number">
                                    </div>
                                </div>
                            </div>
                            
                        </div>
                    </fieldset>


                    <button class="layui-btn" style="display:block;margin-left: auto;margin-right: auto">提交</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="/static/js/layui.all.js"></script>

</body>
</html>
