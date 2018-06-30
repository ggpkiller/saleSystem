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
                    <a style="cursor: pointer;margin-right: 2em" onclick="history.back()">[--返回--]</a><br><br><h2 class="layui-col-md4">公排队列--${rule.ruleName}</h2>
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
                    <%-- <colgroup>
                        <col width="80">
                        <col width="80">
                        <col width="80">
                        <col width="80">
                        <col width="120">
                        <col width="80">
                        <col width="80">
                        <col width="100">
                        <col width="200">
                        <col width="200">
                        
                    </colgroup> --%>
                    <thead>
                    <tr>
                    	<th>出局顺序</th>
                        <th>公排排名(以个人为顶点)</th>
                        <th>姓名</th>
                        <th>上级推荐人</th>
                        <th>报单者</th>
                        <th>直推人数</th>
                        <th>推荐总人数(含间接)</th>
                        <th>加入公排时间</th>
                        <th>定拨相关</th>
                        <th>出局相关</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="tt">
                    <c:forEach items="${saleForms}" var="line">
                        <tr id="t1">
                        	<td>${line.currentNo}</td>
                            <td>${line.rank}</td>
                            <td>${line.name}</td>
                            <td>${line.upperName }</td>
                            <td>${line.formType}</td>
                            <td>${line.refereeCount}&nbsp;&nbsp;&nbsp;&nbsp;人</td>
                            <td>${line.totalRefereCount }&nbsp;&nbsp;&nbsp;&nbsp;人</td>
                            <td><fmt:formatDate value="${line.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td>
                            <c:if test="${line.findDingboCondition eq 'Y' }"><i class="layui-icon layui-icon-tips" style="color:red;"></i>已满足定拨条件<a href="${pageContext.request.contextPath}/form/lineList?ruleId=${rule.id}&menuId=${menuId}&formId=${line.id}" class="layui-btn layui-btn-xs" style="margin-left:10px">定拨登记</a></c:if>
                            </td>
                            <td><c:if test="${line.isOut eq 'Y' }"><i class="layui-icon layui-icon-tips" style="color:red;"></i>已满足出局条件<a href="${pageContext.request.contextPath}/form/lineList?ruleId=${rule.id}&menuId=${menuId}&formId=${line.id}&type=out" class="layui-btn layui-btn-xs" style="margin-left:10px">出局登记</a></c:if></td>
                            <td><a class="layui-btn layui-btn-danger layui-btn-xs"
											lay-event="del" href="${pageContext.request.contextPath}/form/delSaleForm?formId=${line.id }&menuId=${menuId}" onclick="if(confirm('确定删除?')==false)return false;">删除</a></td>
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
                    window.location.href = '${pageContext.request.contextPath}/form/lineList?pageIndex='+obj.curr +'&menuId=${menuId}&ruleId=${rule.id}';
                }
            }
        });



    });
</script>
</body>
</html>
