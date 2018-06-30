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
						onclick="history.back()">[--返回--]</a><br> <br>
					<h2 class="layui-col-md4">出局名单</h2>
				</div>
				<form class="layui-form" action="${pageContext.request.contextPath}/form/verifyForm?menuId=${menuId}"
					method="post">
					<div class="layui-field-box">
						<fieldset class="layui-elem-field">
							<legend>基本信息</legend>
							<div class="layui-field-box">
								<div class="layui-form-item">
									<div class="layui-col-md4">
										<label class="layui-form-label">姓名</label> <span
											class="layui-form-label"
											style="text-align: left; width: auto;">${out.name}</span>
									</div>
								</div>
								<div class="layui-form-item">
									<div class="layui-col-md4">
										<label class="layui-form-label">手机号</label> <span
											class="layui-form-label"
											style="text-align: left; width: auto;">${out.mobile}</span>
									</div>
								</div>
								<div class="layui-form-item">
									<div class="layui-col-md4">
										<label class="layui-form-label">身份证号</label> <span
											class="layui-form-label"
											style="text-align: left; width: auto;">${out.idNumber}</span>
									</div>
								</div>
								<div class="layui-form-item">
									<div class="layui-col-md4">
										<label class="layui-form-label">现居住地址</label> <span
											class="layui-form-label"
											style="text-align: left; width: auto;">${out.address}</span>

									</div>
								</div>
								<div class="layui-form-item">
									<div class="layui-col-md4">
										<label class="layui-form-label">开户行名称</label> <span
											class="layui-form-label"
											style="text-align: left; width: auto;">${out.bankName}</span>
									</div>
								</div>
								<div class="layui-form-item">
									<div class="layui-col-md4">
										<label class="layui-form-label">银行卡号</label> <span
											class="layui-form-label"
											style="text-align: left; width: auto;">${out.bankAccount}</span>
									</div>
								</div>
								<div class="layui-form-item">
									<div class="layui-col-md4">
										<label class="layui-form-label">推荐人</label> <span
											class="layui-form-label"
											style="text-align: left; width: auto;">${out.refereeId}</span>
									</div>
								</div>
							</div>
						</fieldset>


						<fieldset class="layui-elem-field">
							<legend>公排信息</legend>
							<div class="layui-field-box">
								<div class="layui-form-item">
									<div class="layui-col-md4">
										<label class="layui-form-label">是否出局</label> <span
											class="layui-form-label"
											style="text-align: left; width: auto;">
											<c:if test="${out.isOut == 'Y' }">
												已满足出局条件，可以出局 
											</c:if>
											<c:if test="${out.isOut == 'N' }">
												未满足出局条件，无法出局 
											</c:if>
											</span>
									</div>
								</div>
								<div class="layui-form-item">
									<div class="layui-col-md4">
										<label class="layui-form-label">当前排名</label> <span
											class="layui-form-label"
											style="text-align: left; width: auto;">${out.currentNo}</span>
									</div>
								</div>
								<div class="layui-form-item">
									<div class="layui-col-md4">
										<label class="layui-form-label">推荐他人数</label> <span
											class="layui-form-label"
											style="text-align: left; width: auto;">${out.refereeCount}人</span>
									</div>
								</div>
								<div class="layui-form-item">
									<div class="layui-col-md4">
										<label class="layui-form-label">公排总人数</label> <span
											class="layui-form-label"
											style="text-align: left; width: auto;">${out.underCount}人</span>
									</div>
								</div>
								
							</div>
						</fieldset>
					</div>
				</form>
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
