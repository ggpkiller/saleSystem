<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<jsp:include page="../common/top.jsp" />
</head>

<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<jsp:include page="../common/left.jsp" />

		<div class="layui-body">
			<!-- 内容主体区域 -->
			<div style="padding: 15px;">
				<div class="head layui-row">
					<h2 class="layui-col-md4">会员通知</h2>
					<div class="layui-col-md4 layui-col-md-offset4">
						<div class="layui-input-inline" style="width: 80%;">
							<input type="text" name="price_min" placeholder="请输入要搜索的内容"
								autocomplete="off" class="layui-input">
						</div>
						<div class="layui-input-inline" style="width: 10%;">
							<button class="layui-btn">搜索</button>
						</div>
					</div>
				</div>
				<table class="layui-table">
					<colgroup>
						<col width="300">
						<col width="300">
						<col width="150">
					</colgroup>
					<thead>
						<tr>
							<th>标题</th>
							<th>发布时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${notices}" var="notice">
							<tr>
								<td>${notice.title}</td>
								<td><fmt:formatDate value="${notice.insertTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<td>
									
									<div class="layui-table-cell laytable-cell-1-9">
										<a class="layui-btn layui-btn-primary layui-btn-xs"
											lay-event="detail"
											href="${pageContext.request.contextPath}/member/noticeDetail?menuId=${menuId}&noticeId=${notice.noticeId}">查看</a>
										<a class="layui-btn layui-btn-danger layui-btn-xs"
											lay-event="del" href="${pageContext.request.contextPath}/member/delNotice?noticeId=${notice.noticeId }&menuId=${menuId}" onclick="if(confirm('确定删除?')==false)return false;">删除</a>
									</div>
									</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div id="page" style="text-align: center"></div>
			</div>




			<div style="margin-top:30px">
				<form class="layui-form" method="post" action="${pageContext.request.contextPath}/member/addNotice">

					<fieldset class="layui-elem-field">

						<legend>添加新通知</legend>
						<div class="layui-field-box">
							<div class="layui-row layui-form-item item">
								<div class="layui-col-md5">
									<label class="layui-form-label">标题</label>
									<div class="layui-input-block">
										<input type="text" name="title" placeholder="通知标题"
											autocomplete="off" class="layui-input" lay-verify="required">
									</div>



								</div>


								
							</div>
							
							
							
							
							
							
							<div class="layui-row layui-form-item item">
								


								<div class="layui-col-md5">
									<label class="layui-form-label">内容</label>
									<div class="layui-input-block">
										<textarea name="content" placeholder="请输入内容"
											class="layui-textarea" lay-verify="required"
											style="resize: none;" rows="10" maxlength="800"></textarea>
									</div>

								</div>
							</div>
						</div>
					</fieldset>
					<button class="layui-btn" lay-submit lay-filter="formDemo" style="display: block;margin-left:20%;margin-top:60px">立即发布</button>
				</form>
				
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
                    window.location.href = '${pageContext.request.contextPath}/member/notice?pageIndex='+obj.curr;
                }
            }
        });
    });
</script>
</body>
</html>