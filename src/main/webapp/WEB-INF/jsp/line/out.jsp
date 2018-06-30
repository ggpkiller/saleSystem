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
.select-zhandian{
	height:30px;
	width:160px;
	margin-left:10px;
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
				<form action="${pageContext.request.contextPath}/form/regOut?menuId=${menuId}" method="post" id="subForm">
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
								<legend>出局信息</legend>
								<div class="layui-field-box">
									<div class="layui-form-item">
										<div class="layui-col-md12">
											<label class="layui-form-label">奖励规则描述</label> <span
												class="layui-form-label"
												style="text-align: left; width: auto;"> <c:if
													test="${form.refereeCount >= rule.shareReferee && form.totalRefereCount >= rule.shareUnderNumber}">
												领导奖出局奖励：直推必须达到${ rule.shareReferee}人,总推荐人数（含间接）达到${rule.shareUnderNumber }人,排名达到${subordinate },奖励${rule.shareRebate}元
												</c:if> <c:if
													test="${form.refereeCount < rule.shareReferee || form.totalRefereCount < rule.shareUnderNumber}">
												普通出局奖励：排名达到${subordinate },奖励${rule.rebate }元
												</c:if>
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



							<fieldset class="layui-elem-field">
								<legend>出局清算</legend>
								<c:forEach items="${rules}" var='r'>
									<div class="layui-row layui-form-item">
										<div class="layui-col-md6 long-font">
											<label class="layui-form-label">${r.ruleName }</label>
											<div class="layui-input-block">
												<select class="select-zhandian">
													<option>不占点位</option>
													<option value="1">占1个点位</option>
													<option value="2">占2个点位</option>
													<option value="3">占3个点位</option>
													<option value="4">占4个点位</option>
													<option value="5">占5个点位</option>
												</select>
												<input type="hidden" class="zhandian-id" value="${r.id }">
												<input type="hidden" class="zhandian-form-price" value="${r.formPrice }">
											</div>
										</div>
									</div>
								</c:forEach>
								<div class="layui-form-item">
										<div class="layui-col-md12">
											<label class="layui-form-label">奖励总额</label> <span
												class="layui-form-label"
												style="text-align: left; width: auto;" id="totalAmount">
												<c:if
													test="${form.refereeCount >= rule.shareReferee && form.totalRefereCount >= rule.shareUnderNumber}">
													${rule.shareRebate}元
													</c:if>
													<c:if
													test="${form.refereeCount < rule.shareReferee || form.totalRefereCount < rule.shareUnderNumber}">
													${rule.rebate}元
													</c:if>
												</span>
										</div>
										<div class="layui-col-md12">
											<label class="layui-form-label">扣税（奖励总金额的10%）</label> <span
												class="layui-form-label dingbo-price"
												style="text-align: left; width: auto;">
												<c:if
													test="${form.refereeCount >= rule.shareReferee && form.totalRefereCount >= rule.shareUnderNumber}">
													${rule.shareRebate * 0.1 }元
													</c:if>
													<c:if
													test="${form.refereeCount < rule.shareReferee || form.totalRefereCount < rule.shareUnderNumber}">
													${rule.rebate * 0.1 }元
													</c:if>
												</span>
										</div>
										<div class="layui-form-item">
											<div class="layui-col-md12">
												<label class="layui-form-label">积分扣除（奖励总金额的20%）</label> <span
													class="layui-form-label dingbo-price"
													style="text-align: left; width: auto;">
													<c:if
													test="${form.refereeCount >= rule.shareReferee && form.totalRefereCount >= rule.shareUnderNumber}">
													${rule.shareRebate * 0.2 }元
													</c:if>
													<c:if
													test="${form.refereeCount < rule.shareReferee || form.totalRefereCount < rule.shareUnderNumber}">
													${rule.rebate * 0.2 }元
													</c:if>
													</span>
											</div>
											<c:if test="${form.leaderFirst == 'Y' }">
											<div class="layui-form-item">
												<div class="layui-col-md12">
													<label class="layui-form-label">扣除领导奖励定拨-${rule.leaderFirstRank }</label> <span
														class="layui-form-label dingbo-price"
														style="text-align: left; width: auto;">${rule.leaderFirstRebate}元</span>
												</div>
											</div>
											</c:if>
											
											<c:if test="${form.leaderSecond == 'Y' }">
											<div class="layui-form-item">
												<div class="layui-col-md12">
													<label class="layui-form-label">扣除领导奖励定拨-${rule.leaderSecondRank }</label> <span
														class="layui-form-label dingbo-price"
														style="text-align: left; width: auto;">${rule.leaderSecondRebate}元</span>
												</div>
											</div>
											</c:if>
											
											<c:if test="${form.leaderThird == 'Y' }">
											<div class="layui-form-item">
												<div class="layui-col-md12">
													<label class="layui-form-label">扣除领导奖励定拨-${rule.leaderThirdRank }</label> <span
														class="layui-form-label dingbo-price"
														style="text-align: left; width: auto;">${rule.leaderThirdRebate}元</span>
												</div>
											</div>
											</c:if>
											
											<c:if test="${form.commonFirst == 'Y' }">
											<div class="layui-form-item">
												<div class="layui-col-md12">
													<label class="layui-form-label">扣除普通分享奖励定拨-${rule.commonFirstRank }</label> <span
														class="layui-form-label dingbo-price"
														style="text-align: left; width: auto;">${rule.commonFirstRebate}元</span>
												</div>
											</div>
											</c:if>
											
											<c:if test="${form.commonSecond == 'Y' }">
											<div class="layui-form-item">
												<div class="layui-col-md12">
													<label class="layui-form-label">扣除普通分享奖励定拨-${rule.commonSecondRank }</label> <span
														class="layui-form-label dingbo-price"
														style="text-align: left; width: auto;">${rule.commonSecondRebate}元</span>
												</div>
											</div>
											</c:if>
											
											<c:if test="${form.commonThird == 'Y' }">
											<div class="layui-form-item">
												<div class="layui-col-md12">
													<label class="layui-form-label">扣除普通分享奖励定拨-${rule.commonThirdRank }</label> <span
														class="layui-form-label dingbo-price"
														style="text-align: left; width: auto;">${rule.commonThirdRebate}元</span>
												</div>
											</div>
											</c:if>
											
											<c:if test="${form.noshareFirst == 'Y' }">
											<div class="layui-form-item">
												<div class="layui-col-md12">
													<label class="layui-form-label">扣除无分享奖励定拨-${rule.noshareFirstRank }</label> <span
														class="layui-form-label dingbo-price"
														style="text-align: left; width: auto;">${rule.noshareFirstRebate}元</span>
												</div>
											</div>
											</c:if>
											
											<c:if test="${form.noshareSecond == 'Y' }">
											<div class="layui-form-item">
												<div class="layui-col-md12">
													<label class="layui-form-label">扣除无分享奖励定拨-${rule.noshareSecondRank }</label> <span
														class="layui-form-label dingbo-price"
														style="text-align: left; width: auto;">${rule.noshareSecondRebate}元</span>
												</div>
											</div>
											</c:if>
											
											<c:if test="${form.noshareThird == 'Y' }">
											<div class="layui-form-item">
												<div class="layui-col-md12">
													<label class="layui-form-label">扣除无分享奖励定拨-${rule.noshareThirdRank }</label> <span
														class="layui-form-label dingbo-price"
														style="text-align: left; width: auto;">${rule.noshareThirdRebate}元</span>
												</div>
											</div>
											</c:if>
											
											<div class="layui-form-item">
												<div class="layui-col-md12">
													<label class="layui-form-label">继续公排占点扣除</label> <span
														class="layui-form-label" id="zhandianAmount"
														style="text-align: left; width: auto;">0元</span>
												</div>
											</div>
											
											<div class="layui-form-item">
												<div class="layui-col-md12">
													<label class="layui-form-label">最终应获得奖励金额</label> <span
														class="layui-form-label" id="finalAmount"
														style="text-align: left; width: auto;">0元</span>
												</div>
											</div>
										</div>
									</div>
							</fieldset>


						</div>
						<input type="hidden" name="id" value="${form.id }">
						<input type="hidden" name="ruleId" id="ruleIds">
					</c:forEach>

					<div class="layui-col-md12">
						<button class="layui-btn" onclick="subDengji();return false">登记奖励</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script src="/static/js/layui.all.js"></script>
	<script>
	
	var totalAmount = 0
	var elseAmount = 0
	var zhanAmount = 0
	var ruleIds = ''
		//JavaScript代码区域
		layui.use([ 'element', 'jquery', 'form' ], function() {
			var element = layui.element;
			var form = layui.form;
			var $ = layui.jquery;
			
		});
		function subDengji() {
			var ok = confirm('奖励会在最终奖励扣除，确定登记该定拨的奖励吗');
			if (ok) {
				$('#ruleIds').val(ruleIds);
				$('#subForm').submit()
			}
		}
		
		$(document).ready(function(){
			totalAmount = $('#totalAmount').text()
			totalAmount = totalAmount.trim()
			totalAmount = totalAmount.replace('元','');
			totalAmount = parseInt(totalAmount)
			
			
			var dingboPrice = $('.dingbo-price')
			
			dingboPrice.each(function(i, dp) {
			    var dpp = $(dp).text().trim()
			    if(dpp != ''){
			    	dpp = dpp.replace('元','');
				    dpp = parseInt(dpp)
				    elseAmount += dpp
			    }
			});
			$('#finalAmount').text((totalAmount-elseAmount)+"元")
			$('.select-zhandian').change(function(){
				zhanAmount = 0
				ruleIds = ''
				$('.select-zhandian').each(function(i,zd){
					var zdCount = $(zd).val()
					if(zdCount != '不占点位'){
						zdCount = parseInt(zdCount)
						var formPrice = parseInt($(zd).siblings('.zhandian-form-price').eq(0).val().trim())
						var rule_id = $(zd).siblings('.zhandian-id').eq(0).val().trim()
						zhanAmount += formPrice*zdCount
						for(var i=0;i<zdCount;i++){
							ruleIds += rule_id
							ruleIds += ';'
						}
					}
				})
				$('#finalAmount').text((totalAmount-elseAmount-zhanAmount)+"元")
				$('#zhandianAmount').text(zhanAmount+"元")
			})
			
		});
	</script>
</body>
</html>
