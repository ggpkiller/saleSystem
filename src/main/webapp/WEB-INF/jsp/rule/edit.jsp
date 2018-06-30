<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="../common/top.jsp"/>
    <style>
        .operation-icon {
            display: inline-block;
            width: 30px;
            height: 30px;
            margin-top: 3px;
            margin-left: 10px;
        }
        .operation-icon:hover{
            cursor: pointer;
        }
        .add {
            background: url("/static/img/add.png") no-repeat center center;
            background-size: contain;
        }

        .sub {
            background: url("/static/img/sub.png") no-repeat center center;
            background-size: contain;
        }
    </style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <jsp:include page="../common/left.jsp"/>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <a style="cursor: pointer;margin-right: 2em" onclick="history.back()">[--返回--]</a><br><br><h2 style="display: inline">编辑</h2>
            <form class="layui-form" action="${pageContext.request.contextPath}/sale/edit" method="post">
            <input type="hidden" name="id" value="${rule.id }">
                <div class="layui-field-box">
                    <fieldset class="layui-elem-field">
                        <legend>主要规则设置</legend>
                        <div class="layui-field-box">
                            <div class="layui-row layui-form-item">
                                <div class="layui-col-md6 long-font">
                                    <label class="layui-form-label">网络名称</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="ruleName"  value="${rule.ruleName}" required lay-verify="required"
                                               placeholder="请输入新规则名称"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md6 long-font">
                                    <label class="layui-form-label">消费金额</label>
                                    <div class="layui-input-block">
                                        <input type="number" name="formPrice" value="${rule.formPrice }" required lay-verify="required|number"
                                               placeholder="请输入报单价格，单位：元"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                            </div>
                            
                            
                            <div class="layui-row layui-form-item">
                                <div class="layui-col-md6 long-font">
                                    <label class="layui-form-label">公排总人数</label>
                                    <div class="layui-input-block">
                                        <select name="outNumber" lay-verify="required">
                                            <optgroup label="[三三结构，达到5层关系]"><option value="5" <c:if test="${rule.outNumber == 5 }">selected</c:if>>364人</option></optgroup>
                                            <optgroup label="[三三结构，达到6层关系]"><option value="6" <c:if test="${rule.outNumber == 6 }">selected</c:if>>1093人</option></optgroup>
                                            <optgroup label="[三三结构，达到7层关系]"><option value="7" <c:if test="${rule.outNumber == 7 }">selected</c:if>>3280人</option></optgroup>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-col-md6 long-font">
                                    <label class="layui-form-label">无分享奖励金额</label>
                                    <div class="layui-input-block">
                                        <input type="number" name="rebate" value="${rule.rebate }" required lay-verify="required|number"
                                               placeholder="请输入无分享奖励金额，单位：元"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                            </div>
                            
                            
                            <div class="layui-row layui-form-item">
                                <div class="layui-col-md6 long-font">
                                    <label class="layui-form-label">有分享奖励直推人数</label>
                                    <div class="layui-input-block">
                                        <select name="shareReferee" lay-verify="required">
                                            <option></option>
                                            <option value="1" <c:if test="${rule.shareReferee eq 1 }">selected</c:if>>1人</option>
                                            <option value="2" <c:if test="${rule.shareReferee eq 2 }">selected</c:if>>2人</option>
                                            <option value="3" <c:if test="${rule.shareReferee eq 3 }">selected</c:if>>3人</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-col-md6 long-font">
                                    <label class="layui-form-label">有分享奖励下线总人数</label>
                                    <div class="layui-input-block">
                                        <input type="number" name="shareUnderNumber" value="${rule.shareUnderNumber }" required lay-verify="required|number"
                                               placeholder="请输入分享奖励下线需要达到的总人数，单位：人"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                            </div>
                            
                            
                            <div class="layui-row layui-form-item">
                                <div class="layui-col-md6 long-font">
                                    <label class="layui-form-label">有分享奖励总金额</label>
                                    <div class="layui-input-block">
                                         <input type="number" name="shareRebate" value="${rule.shareRebate }" required lay-verify="required|number"
                                               placeholder="请输入有分享奖励总金额，单位：元"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                    
                    
                    <fieldset class="layui-elem-field">
                    	<legend>无分享奖励定拨设置</legend> 
                    	<div class="layui-field-box">
                            <div class="layui-row layui-form-item">
                            <div class="layui-col-md1">
                            <label class="layui-form-label">第一个定拨</label></div>
                                <div class="layui-col-md5 long-font">
                                    <label class="layui-form-label">到达排名</label>
                                    <div class="layui-input-block">
                                        <input type="number" name="noshareFirstRank" value="${rule.noshareFirstRank }" required lay-verify="required|number"
                                               placeholder="输入到达的排名"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md6 long-font">
                                    <label class="layui-form-label">奖励金额</label>
                                    <div class="layui-input-block">
                                        <input type="number" name="noshareFirstRebate" value="${rule.noshareFirstRebate}" required lay-verify="required|number"
                                               placeholder="请输入奖励金额，单位：元"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                            </div> 
                        </div>
                        
                        
                        
                        <div class="layui-field-box">
                            <div class="layui-row layui-form-item">
                            <div class="layui-col-md1">
                            <label class="layui-form-label">第二个定拨</label></div>
                                <div class="layui-col-md5 long-font">
                                    <label class="layui-form-label">到达排名</label>
                                    <div class="layui-input-block">
                                        <input type="number" name="noshareSecondRank" value="${rule.noshareSecondRank }" required lay-verify="required|number"
                                               placeholder="输入到达的排名"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md6 long-font">
                                    <label class="layui-form-label">奖励金额</label>
                                    <div class="layui-input-block">
                                        <input type="number" name="noshareSecondRebate" value="${rule.noshareSecondRebate }" required lay-verify="required|number"
                                               placeholder="请输入奖励金额，单位：元"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                            </div> 
                        </div>
                        
                        
                        <div class="layui-field-box">
                            <div class="layui-row layui-form-item">
                            <div class="layui-col-md1">
                            <label class="layui-form-label">第三个定拨</label></div>
                                <div class="layui-col-md5 long-font">
                                    <label class="layui-form-label">到达排名</label>
                                    <div class="layui-input-block">
                                        <input type="number" name="noshareThirdRank" value="${rule.noshareThirdRank }" required lay-verify="required|number"
                                               placeholder="输入到达的排名"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md6 long-font">
                                    <label class="layui-form-label">奖励金额</label>
                                    <div class="layui-input-block">
                                        <input type="number" name="noshareThirdRebate" value="${rule.noshareThirdRebate }" required lay-verify="required|number"
                                               placeholder="请输入奖励金额，单位：元"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                            </div> 
                        </div>
                        
                        
                    </fieldset>
                    
                    
                    <%-- 
                    <fieldset class="layui-elem-field">
                    	<legend>普通分享奖励定拨设置</legend>
                    	<div class="layui-field-box">
                            <div class="layui-row layui-form-item">
                            <div class="layui-col-md1">
                            <label class="layui-form-label">第一个定拨</label></div>
                                <div class="layui-col-md3 long-font">
                                    <label class="layui-form-label">直推人数</label>
                                    <div class="layui-input-block">
                                        <select name="commonFirstReferee" lay-verify="required">
                                            <option></option>
                                            <option value="1" <c:if test="${rule.commonFirstReferee eq 1 }">selected</c:if>>1人</option>
                                            <option value="2" <c:if test="${rule.commonFirstReferee eq 2 }">selected</c:if>>2人</option>
                                            <option value="3" <c:if test="${rule.commonFirstReferee eq 3 }">selected</c:if>>3人</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-col-md4 long-font">
                                    <label class="layui-form-label">到达排名</label>
                                    <div class="layui-input-block">
                                        <input type="number" name="commonFirstRank" value="${rule.commonFirstRank }" required lay-verify="required|number"
                                               placeholder="输入到达的排名"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md4 long-font">
                                    <label class="layui-form-label">奖励金额</label>
                                    <div class="layui-input-block">
                                        <input type="number" name="commonFirstRebate" value="${rule.commonFirstRebate }" required lay-verify="required|number"
                                               placeholder="请输入奖励金额，单位：元"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                            </div> 
                        </div>
                        
                        
                        
                        <div class="layui-field-box">
                            <div class="layui-row layui-form-item">
                            <div class="layui-col-md1">
                            <label class="layui-form-label">第二个定拨</label></div>
                                <div class="layui-col-md3 long-font">
                                    <label class="layui-form-label">直推人数</label>
                                    <div class="layui-input-block">
                                        <select name="commonSecondReferee" lay-verify="required">
                                            <option></option>
                                            <option value="1" <c:if test="${rule.commonSecondReferee eq 1 }">selected</c:if>>1人</option>
                                            <option value="2" <c:if test="${rule.commonSecondReferee eq 2 }">selected</c:if>>2人</option>
                                            <option value="3" <c:if test="${rule.commonSecondReferee eq 3 }">selected</c:if>>3人</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-col-md4 long-font">
                                    <label class="layui-form-label">到达排名</label>
                                    <div class="layui-input-block">
                                        <input type="number" name="commonSecondRank" value="${rule.commonSecondRank }" required lay-verify="required|number"
                                               placeholder="输入到达的排名"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md4 long-font">
                                    <label class="layui-form-label">奖励金额</label>
                                    <div class="layui-input-block">
                                        <input type="number" name="commonSecondRebate" value="${rule.commonSecondRebate }" required lay-verify="required|number"
                                               placeholder="请输入奖励金额，单位：元"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                            </div> 
                        </div>
                        
                        
                        <div class="layui-field-box">
                            <div class="layui-row layui-form-item">
                            <div class="layui-col-md1">
                            <label class="layui-form-label">第三个定拨</label></div>
                                <div class="layui-col-md3 long-font">
                                    <label class="layui-form-label">直推人数</label>
                                    <div class="layui-input-block">
                                        <select name="commonThirdReferee" lay-verify="required">
                                            <option></option>
                                            <option value="1" <c:if test="${rule.commonThirdReferee eq 1 }">selected</c:if>>1人</option>
                                            <option value="2" <c:if test="${rule.commonThirdReferee eq 2 }">selected</c:if>>2人</option>
                                            <option value="3" <c:if test="${rule.commonThirdReferee eq 3 }">selected</c:if>>3人</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-col-md4 long-font">
                                    <label class="layui-form-label">到达排名</label>
                                    <div class="layui-input-block">
                                        <input type="number" name="commonThirdRank" value="${rule.commonThirdRank }" required lay-verify="required|number"
                                               placeholder="输入到达的排名"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md4 long-font">
                                    <label class="layui-form-label">奖励金额</label>
                                    <div class="layui-input-block">
                                        <input type="number" name="commonThirdRebate" value="${rule.commonThirdRebate }" required lay-verify="required|number"
                                               placeholder="请输入奖励金额，单位：元"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                            </div> 
                        </div>
                        
                        
                    </fieldset>
                    --%>
                    
                    
                    <fieldset class="layui-elem-field">
                    	<legend>领导奖励定拨设置</legend>
                    	<div class="layui-field-box">
                    		<div class="layui-row layui-form-item">
                    		<div class="layui-col-md1">
                            <label class="layui-form-label">领导条件</label></div>
                    			<div class="layui-col-md5 long-font">
                    				<label class="layui-form-label">直推人数</label>
                                    <div class="layui-input-block">
                                        <select name="leaderReferee" lay-verify="required">
                                            <option></option>
                                            <option value="1" <c:if test="${rule.leaderReferee eq 1 }">selected</c:if>>1人</option>
                                            <option value="2" <c:if test="${rule.leaderReferee eq 2 }">selected</c:if>>2人</option>
                                            <option value="3" <c:if test="${rule.leaderReferee eq 3 }">selected</c:if>>3人</option>
                                        </select>
                                    </div>
                    			</div>
                    			
                    			<div class="layui-col-md6 long-font">
                    				<label class="layui-form-label">下线人数</label>
                                    <div class="layui-input-block">
                                        <input type="number" name="leaderUnderNumber" value="${rule.leaderUnderNumber }" required lay-verify="required|number"
                                               placeholder="请输入下线人数"
                                               autocomplete="off" class="layui-input">
                                    </div>
                    			</div>
                    		</div>
                    	
                    	</div>
                    	
                    	<div class="layui-field-box">
                            <div class="layui-row layui-form-item">
                            <div class="layui-col-md1">
                            <label class="layui-form-label">第一个定拨</label></div>
                                <div class="layui-col-md5 long-font">
                                    <label class="layui-form-label">到达排名</label>
                                    <div class="layui-input-block">
                                        <input type="number" name="leaderFirstRank" value="${rule.leaderFirstRank}" required lay-verify="required|number"
                                               placeholder="输入到达的排名"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md6 long-font">
                                    <label class="layui-form-label">奖励金额</label>
                                    <div class="layui-input-block">
                                        <input type="number" name="leaderFirstRebate" value="${rule.leaderFirstRebate }" required lay-verify="required|number"
                                               placeholder="请输入奖励金额，单位：元"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                            </div> 
                        </div>
                        
                        
                        
                        <div class="layui-field-box">
                            <div class="layui-row layui-form-item">
                            <div class="layui-col-md1">
                            <label class="layui-form-label">第二个定拨</label></div>
                                <div class="layui-col-md5 long-font">
                                    <label class="layui-form-label">到达排名</label>
                                    <div class="layui-input-block">
                                        <input type="number" name="leaderSecondRank" value="${rule.leaderSecondRank}" required lay-verify="required|number"
                                               placeholder="输入到达的排名"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md6 long-font">
                                    <label class="layui-form-label">奖励金额</label>
                                    <div class="layui-input-block">
                                        <input type="number" name="leaderSecondRebate" value="${rule.leaderSecondRebate }" required lay-verify="required|number"
                                               placeholder="请输入奖励金额，单位：元"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                            </div> 
                        </div>
                        
                        
                        <div class="layui-field-box">
                            <div class="layui-row layui-form-item">
                            <div class="layui-col-md1">
                            <label class="layui-form-label">第三个定拨</label></div>
                                <div class="layui-col-md5 long-font">
                                    <label class="layui-form-label">到达排名</label>
                                    <div class="layui-input-block">
                                        <input type="number" name="leaderThirdRank" value="${rule.leaderThirdRank }" required lay-verify="required|number"
                                               placeholder="输入到达的排名"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md6 long-font">
                                    <label class="layui-form-label">奖励金额</label>
                                    <div class="layui-input-block">
                                        <input type="number" name="leaderThirdRebate" value="${rule.leaderThirdRebate }" required lay-verify="required|number"
                                               placeholder="请输入奖励金额，单位：元"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                            </div> 
                        </div>
                        
                        
                    </fieldset>
                    
                    
                    
                    
                    <div class="layui-form-item">
                        <button class="layui-btn" lay-submit>提交</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="/static/js/layui.all.js"></script>
<script>
    //JavaScript代码区域
    layui.use(['element', 'jquery', 'form'], function () {
        var element = layui.element;
    });
</script>
</body>
</html>
