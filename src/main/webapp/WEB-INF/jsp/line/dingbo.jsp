<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<jsp:include page="../common/top.jsp" />
<style type="text/css">
.layui-form-label {
	width: 180px;
}
</style>
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
				<form class="layui-form"
					action="${pageContext.request.contextPath}/form/regDingbo?menuId=${menuId}"
					method="post" id="subForm">
					<c:forEach items="${saleForms}" var="form" varStatus="index">
						<div class="layui-field-box">
							<fieldset class="layui-elem-field">
								<legend>报单基本信息</legend>
								<div class="layui-field-box">
									<div class="layui-form-item">
										<div class="layui-col-md12">
											<label class="layui-form-label">姓名</label> <span
												class="layui-form-label"
												style="text-align: left; width: auto;">${form.name}</span>
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-col-md12">
											<label class="layui-form-label">手机号</label> <span
												class="layui-form-label"
												style="text-align: left; width: auto;">${form.mobile}</span>
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-col-md12">
											<label class="layui-form-label">身份证号</label> <span
												class="layui-form-label"
												style="text-align: left; width: auto;">${form.idNumber}</span>
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-col-md12">
											<label class="layui-form-label">现居住地址</label> <span
												class="layui-form-label"
												style="text-align: left; width: auto;">${form.address}</span>

										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-col-md12">
											<label class="layui-form-label">开户行名称</label> <span
												class="layui-form-label"
												style="text-align: left; width: auto;">${form.bankName}</span>
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-col-md12">
											<label class="layui-form-label">银行卡号</label> <span
												class="layui-form-label"
												style="text-align: left; width: auto;">${form.bankAccount}</span>
										</div>
									</div>

								</div>
							</fieldset>


							<fieldset class="layui-elem-field">
								<legend>公排信息</legend>
								<div class="layui-field-box">
									<div class="layui-form-item">
										<div class="layui-col-md12">
											<label class="layui-form-label">上级推荐人</label> <span
												class="layui-form-label"
												style="text-align: left; width: auto;">${form.upperName}</span>
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-col-md12">
											<label class="layui-form-label">报单者</label> <span
												class="layui-form-label"
												style="text-align: left; width: auto;">${form.formType}</span>
										</div>
									</div>


									<div class="layui-form-item">
										<div class="layui-col-md12">
											<label class="layui-form-label">加入公排时间</label> <span
												class="layui-form-label"
												style="text-align: left; width: auto;"><fmt:formatDate
													value="${form.updateTime}" pattern="yyyy年M月d日  HH点mm分" /></span>
										</div>
									</div>
								</div>
							</fieldset>








							<fieldset class="layui-elem-field">
								<legend>定拨信息</legend>
								<div class="layui-field-box">
									<div class="layui-form-item">
										<div class="layui-col-md12">
											<label class="layui-form-label">奖励规则描述</label> <span
												class="layui-form-label"
												style="text-align: left; width: auto;"> <c:if
													test="${form.leaderFirst eq 'Y' }">领导分享奖励&nbsp;&nbsp;直推${rule.leaderReferee }人，&nbsp;&nbsp;总推荐${rule.leaderUnderNumber }人， &nbsp;&nbsp;排名达到${rule.leaderFirstRank }，&nbsp;&nbsp;奖励${rule.leaderFirstRebate }元</c:if>
												<c:if test="${form.leaderSecond eq 'Y' }">领导分享奖励&nbsp;&nbsp;直推${rule.leaderReferee }人，&nbsp;&nbsp;总推荐${rule.leaderUnderNumber }人， &nbsp;&nbsp;排名达到${rule.leaderSecondRank }，&nbsp;&nbsp;奖励${rule.leaderSecondRebate }元</c:if>
												<c:if test="${form.leaderThird eq 'Y' }">领导分享奖励&nbsp;&nbsp;直推${rule.leaderReferee }人，&nbsp;&nbsp;总推荐${rule.leaderUnderNumber }人， &nbsp;&nbsp;排名达到${rule.leaderThirdRank }，&nbsp;&nbsp;奖励${rule.leaderThirdRebate }元</c:if>
												<%-- <c:if test="${form.commonFirst eq 'Y' }">普通分享奖励&nbsp;&nbsp;直推${rule.commonFirstReferee }人， &nbsp;&nbsp;排名达到${rule.commonFirstRank }，&nbsp;&nbsp;奖励${rule.commonFirstRebate }元</c:if>
												<c:if test="${form.commonSecond eq 'Y' }">普通分享奖励&nbsp;&nbsp;直推${rule.commonSecondReferee }人， &nbsp;&nbsp;排名达到${rule.commonSecondRank }，&nbsp;&nbsp;奖励${rule.commonSecondRebate }元</c:if>
												<c:if test="${form.commonThird eq 'Y' }">普通分享奖励&nbsp;&nbsp;直推${rule.commonThirdReferee }人， &nbsp;&nbsp;排名达到${rule.commonThirdRank }，&nbsp;&nbsp;奖励${rule.commonThirdRebate }元</c:if>
												--%>
												<c:if test="${form.noshareFirst eq 'Y' }">无分享奖励&nbsp;&nbsp;无直接推荐人数，&nbsp;&nbsp;排名达到${rule.noshareFirstRank }，&nbsp;&nbsp;奖励${rule.noshareFirstRebate }元</c:if>
												<c:if test="${form.noshareSecond eq 'Y' }">无分享奖励&nbsp;&nbsp;无直接推荐人数，&nbsp;&nbsp;排名达到${rule.noshareSecondRank }，&nbsp;&nbsp;奖励${rule.noshareSecondRebate }元</c:if>
												<c:if test="${form.noshareThird eq 'Y' }">无分享奖励&nbsp;&nbsp;无直接推荐人数，&nbsp;&nbsp;排名达到${rule.noshareThirdRank }，&nbsp;&nbsp;奖励${rule.noshareThirdRebate }元</c:if>
											</span>
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-col-md12">
											<label class="layui-form-label">当前排名</label> <span
												class="layui-form-label"
												style="text-align: left; width: auto;">${form.rank}</span>
										</div>
										<div class="layui-form-item">
											<div class="layui-col-md12">
												<label class="layui-form-label">直推人数</label> <span
													class="layui-form-label"
													style="text-align: left; width: auto;">${form.refereeCount}人</span>
											</div>
											<div class="layui-form-item">
												<div class="layui-col-md12">
													<label class="layui-form-label">推荐总人数（含间接）</label> <span
														class="layui-form-label"
														style="text-align: left; width: auto;">${form.totalRefereCount}人</span>
												</div>
											</div>
											
										</div>
									</div>
								</div>
							</fieldset>
						</div>
						<input type="hidden" name="id" value="${form.id }">
					<input type="hidden" name="ruleId" value="${form.ruleId }">
					<input type="hidden" name="leaderFirst" value="${form.leaderFirst }">
					<input type="hidden" name="leaderSecond" value="${form.leaderSecond }">
					<input type="hidden" name="leaderThird" value="${form.leaderThird }">
					<input type="hidden" name="commonFirst" value="${form.commonFirst }">
					<input type="hidden" name="commonSecond" value="${form.commonSecond }">
					<input type="hidden" name="commonThird" value="${form.commonThird }">
					<input type="hidden" name="noshareFirst" value="${form.noshareFirst }">
					<input type="hidden" name="noshareSecond" value="${form.noshareSecond }">
					<input type="hidden" name="noshareThird" value="${form.noshareThird }">
					</c:forEach>
					
					<div class="layui-col-md12"><button class="layui-btn" onclick="subDengji();return false">登记奖励</button></div>
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
		function subDengji(){
			var ok = confirm('奖励会在最终奖励扣除，确定登记该定拨的奖励吗');
			if(ok){
				$('#subForm').submit()
			}
		}
	</script>
</body>
</html>
