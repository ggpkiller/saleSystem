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
            <form class="layui-form" action="${pageContext.request.contextPath}/form/verifyForms?menuId=${menuId}" method="post" id="mainForm">
                <div class="head layui-row">
                    <h2 class="layui-col-md4">报单审核</h2>
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
                        <col width="50">
                        <col width="100">
                        <col width="150">
                        <col width="150">
                        <col width="250">
                        <col width="300">
                        <col width="200">
                    </colgroup>
                    <thead>
                    <tr>
                        <th><input id="checkAll" lay-filter="checkAll" type="checkbox" lay-skin="primary"></th>
                        <th>产品名称</th>
                        <th>姓名</th>
                        <th>电话</th>
                        <th>身份证号</th>
                        <th>开户行</th>
                        <th>银行卡号</th>
                        <th>推荐人</th>
                        <th>时间</th>
                        <th>操作<button disabled class="layui-btn layui-btn-disabled layui-btn-xs"
                                      style="margin-left: 10px">
                            批量通过
                        </button>
                        </th>
                    </tr>
                    </thead>
                    <tbody id="tt">
                    <c:forEach items="${list}" var="sf">
                        <tr id="t1">
                            <td><input lay-filter="checkItem" type="checkbox" name="formIds" lay-skin="primary" value="${sf.id}"></td>
                            <td>${sf.ruleName}</td>
                            <td>${sf.name}</td>
                            <td>${sf.mobile}</td>
                            <td>${sf.idNumber}</td>
                            <td>${sf.bankName}</td>
                            <td>${sf.bankAccount}</td>
                            <td>${sf.memberName}</td>
                            <td><fmt:formatDate value="${sf.insertTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td>
                                <div class="layui-table-cell laytable-cell-1-9">
                                    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail" href="${pageContext.request.contextPath}/form/singleForm?menuId=${menuId}&formId=${sf.id}">查看</a>
                                    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" href="${pageContext.request.contextPath}/form/delSaleForm?menuId=${menuId}&formId=${sf.id}">删除</a>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </form>
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

        form.on("checkbox(checkAll)", function (data) {
            if (data.elem.checked) {
                $("td input[type=checkbox]").attr("checked", true);
            } else {
                $("td input[type=checkbox]").attr("checked", false);
            }
            form.render("checkbox");
            if (checkOne()) {
                $("th button").removeAttr("disabled").addClass("layui-btn-normal").removeClass("layui-btn-disabled");
            } else {
                $("th button").attr("disabled", false).removeClass("layui-btn-normal").addClass("layui-btn-disabled");
            }
        });

        form.on("checkbox(checkItem)", function (data) {
            $("input[lay-filter=checkAll]").attr("checked", checkAll());
            form.render("checkbox");
            if (checkOne()) {
                $("th button").removeAttr("disabled").addClass("layui-btn-normal").removeClass("layui-btn-disabled");
            } else {
                $("th button").attr("disabled", false).removeClass("layui-btn-normal").addClass("layui-btn-disabled");
            }
        });

        function checkAll() {
            var allcheck = true;
            var items = $("input[lay-filter=checkItem]");
            for (var i in items) {
                if (items[i].checked == false) {
                    allcheck = false;
                    break;
                }
            }
            return allcheck;
        }

        function checkOne() {
            var oneCheck = false;
            var items = $("input[lay-filter=checkItem]");
            for (var i in items) {
                if (items[i].checked == true) {
                    oneCheck = true;
                    break;
                }
            }
            return oneCheck;
        }

    });
</script>
</body>
</html>
