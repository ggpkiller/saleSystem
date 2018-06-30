<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<jsp:include page="../common/top.jsp" />
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<jsp:include page="../common/left.jsp" />

		<div class="layui-body">
			<!-- 内容主体区域 -->
			<div style="padding: 15px;">
				<div class="layui-row">
					<a style="cursor: pointer; margin-right: 2em"
						onclick="history.back()">[--返回--]</a><br>
					<br>
					<h2 class="layui-col-md4">通知查看</h2>
				</div>

				<div class="layui-field-box">
					<fieldset class="layui-elem-field">
						<div class="layui-field-box">
							<div class="layui-form-item">
								<div class="layui-col-md4">
									<label class="layui-form-label">标题</label> <span
										class="layui-form-label"
										style="text-align: left; width: auto;">${notice.title}</span>
								</div>
							</div>
							<div class="layui-form-item">
								<div class="layui-col-md4">
									<label class="layui-form-label">内容</label>
									<pre class="layui-col-md8 " style="margin-top:50px;margin-left:1em;font-family: Microsoft YaHei" >${notice.content }</pre>
								</div>
							</div>
							</div>
					</fieldset>
				</div>
			</div>
		</div>
	</div>
	<script src="js/layui.all.js"></script>
	<script>
		//JavaScript代码区域
		layui.use([ 'element', 'jquery' ], function() {
			var element = layui.element;
			var $ = layui.jquery;
			$("#phone").on("input", function(a) {
				$("#account").val(a.currentTarget.value)
			})
		});
	</script>
</body>
</html>
