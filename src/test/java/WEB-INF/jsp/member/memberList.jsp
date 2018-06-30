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
            <div class="head layui-row">
                <h2 class="layui-col-md4">会员管理</h2>
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
                    <col width="150">
                    <col width="200">
                    <col width="300">
                    <col width="200">
                    <col width="200">
                    <col width="90">
                    <col width="200">
                </colgroup>
                <thead>
                <tr>
                    <th>姓名</th>
                    <th>电话</th>
                    <th>身份证号</th>
                    <th>开户行</th>
                    <th>银行账号</th>
                    <th>积分</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${memberList}" var="member">
                    <tr>
                        <td>${member.name}</td>
                        <td>${member.mobile}</td>
                        <td>${member.idNumber}</td>
                        <td>${member.bankName}</td>
                        <td>${member.bankAccount}</td>
                        <td>${member.integral }</td>
                        <td>
                            <div class="layui-table-cell laytable-cell-1-9">
                                <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail" href="${pageContext.request.contextPath}/${href}?menuId=${menuId}&memberId=${member.id}">查看</a>
                                <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
                                <%--<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>--%>
                            </div>
                        </td>
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
    layui.use(['element', 'laypage'], function () {
        var element = layui.element;
        var laypage = layui.laypage;
        laypage.render({
            elem: 'page', //注意，这里的 test1 是 ID，不用加 # 号
            count: ${counter}, //数据总数，从服务端得到
            limit:${pageShow},
            curr:${pageIndex},
            jump: function (obj, first) {
                if (!first) {
                    window.location.href = '${pageContext.request.contextPath}/member/memberList?pageIndex='+obj.curr + "&menuId=${menuId}";
                }
            }
        });
    });
</script>
</body>
</html>
