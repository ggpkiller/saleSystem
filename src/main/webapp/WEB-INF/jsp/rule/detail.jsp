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
            <a style="cursor: pointer;margin-right: 2em" onclick="history.back()">[--返回--]</a><br><br><h2 style="display: inline">${rule.ruleName}</h2>
            
                <div class="layui-field-box">
                    <fieldset class="layui-elem-field">
                        <legend>主要规则</legend>
                        <div class="layui-field-box">
                            <div class="layui-row layui-form-item">
                                <div class="layui-col-md6 long-font">
                                    <label class="layui-form-label">网络名称</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="ruleName" readonly="readonly" value="${rule.ruleName}" required lay-verify="required"
                                               
                                                class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md6 long-font">
                                    <label class="layui-form-label">消费金额</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="formPrice" readonly="readonly" value="${rule.formPrice} 元"
                                                class="layui-input">
                                    </div>
                                </div>
                            </div>
                            
                            
                            <div class="layui-row layui-form-item">
                                <div class="layui-col-md6 long-font">
                                    <label class="layui-form-label">公排总人数</label>
                                    <div class="layui-input-block">
                                        <input readonly="readonly" value="${rule.outNumber } 人" required lay-verify="required|number"
                                               placeholder="请输入报单价格，单位：元"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md6 long-font">
                                    <label class="layui-form-label">无分享奖励金额</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="rebate" value="${rule.rebate } 元" readonly="readonly" required lay-verify="required|number"
                                               placeholder="请输入无分享奖励金额，单位：元"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                            </div>
                            
                            
                            <div class="layui-row layui-form-item">
                                <div class="layui-col-md6 long-font">
                                    <label class="layui-form-label">有分享奖励直推人数</label>
                                    <div class="layui-input-block">
                                        <input readonly="readonly" value="${rule.shareReferee }人" required lay-verify="required|number"
                                               placeholder="请输入报单价格，单位：元"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md6 long-font">
                                    <label class="layui-form-label">有分享奖励下线总人数</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="shareUnderNumber" value="${rule.shareUnderNumber } 人" readonly="readonly" required lay-verify="required|number"
                                               placeholder="请输入分享奖励下线需要达到的总人数，单位：人"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                            </div>
                            
                            
                            <div class="layui-row layui-form-item">
                                <div class="layui-col-md6 long-font">
                                    <label class="layui-form-label">有分享奖励总金额</label>
                                    <div class="layui-input-block">
                                         <input type="text" name="shareRebate" value="${rule.shareRebate } 元" readonly="readonly" required lay-verify="required|number"
                                               placeholder="请输入有分享奖励总金额，单位：元"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                    
                    
                    <fieldset class="layui-elem-field">
                    	<legend>无分享奖励定拨</legend> 
                    	<div class="layui-field-box">
                            <div class="layui-row layui-form-item">
                            <div class="layui-col-md1">
                            <label class="layui-form-label">第一个定拨</label></div>
                                <div class="layui-col-md5 long-font">
                                    <label class="layui-form-label">到达排名</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="noshareFirstRank" value="${rule.noshareFirstRank }" readonly="readonly" required lay-verify="required|number"
                                               placeholder="输入到达的排名"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md6 long-font">
                                    <label class="layui-form-label">奖励金额</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="noshareFirstRebate" value="${rule.noshareFirstRebate} 元" readonly="readonly" required lay-verify="required|number"
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
                                        <input type="text" name="noshareSecondRank" value="${rule.noshareSecondRank }" readonly="readonly" required lay-verify="required|number"
                                               placeholder="输入到达的排名"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md6 long-font">
                                    <label class="layui-form-label">奖励金额</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="noshareSecondRebate" value="${rule.noshareSecondRebate } 元" readonly="readonly" required lay-verify="required|number"
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
                                        <input type="text" name="noshareThirdRank" value="${rule.noshareThirdRank }" readonly="readonly" required lay-verify="required|number"
                                               placeholder="输入到达的排名"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md6 long-font">
                                    <label class="layui-form-label">奖励金额</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="noshareThirdRebate" value="${rule.noshareThirdRebate } 元" readonly="readonly" required lay-verify="required|number"
                                               placeholder="请输入奖励金额，单位：元"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                            </div> 
                        </div>
                        
                        
                    </fieldset>
                    
                    
                    <%-- 
                    <fieldset class="layui-elem-field">
                    	<legend>普通分享奖励定拨</legend>
                    	<div class="layui-field-box">
                            <div class="layui-row layui-form-item">
                            <div class="layui-col-md1">
                            <label class="layui-form-label">第一个定拨</label></div>
                                <div class="layui-col-md3 long-font">
                                    <label class="layui-form-label">直推人数</label>
                                    <div class="layui-input-block">
                                        
                                        <input readonly="readonly" value="${rule.commonFirstReferee } 人" required lay-verify="required|number"
                                               placeholder="请输入报单价格，单位：元"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md4 long-font">
                                    <label class="layui-form-label">到达排名</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="commonFirstRank" value="${rule.commonFirstRank }" readonly="readonly" required lay-verify="required|number"
                                               placeholder="输入到达的排名"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md4 long-font">
                                    <label class="layui-form-label">奖励金额</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="commonFirstRebate" value="${rule.commonFirstRebate } 元" readonly="readonly" required lay-verify="required|number"
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
                                        
                                        <input readonly="readonly" value="${rule.commonSecondReferee } 人" required lay-verify="required|number"
                                               placeholder="请输入报单价格，单位：元"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md4 long-font">
                                    <label class="layui-form-label">到达排名</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="commonSecondRank" value="${rule.commonSecondRank }" readonly="readonly" required lay-verify="required|number"
                                               placeholder="输入到达的排名"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md4 long-font">
                                    <label class="layui-form-label">奖励金额</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="commonSecondRebate" value="${rule.commonSecondRebate } 元" readonly="readonly" required lay-verify="required|number"
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
                                       
                                        <input readonly="readonly" value="${rule.commonThirdReferee } 人" required lay-verify="required|number"
                                               placeholder="请输入报单价格，单位：元"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md4 long-font">
                                    <label class="layui-form-label">到达排名</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="commonThirdRank" value="${rule.commonThirdRank }" readonly="readonly" required lay-verify="required|number"
                                               placeholder="输入到达的排名"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md4 long-font">
                                    <label class="layui-form-label">奖励金额</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="commonThirdRebate" value="${rule.commonThirdRebate } 元" readonly="readonly" required lay-verify="required|number"
                                               placeholder="请输入奖励金额，单位：元"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                            </div> 
                        </div>
                        
                        
                    </fieldset>
                    --%>
                    
                    
                    <fieldset class="layui-elem-field">
                    	<legend>领导奖励定拨</legend>
                    	<div class="layui-field-box">
                    		<div class="layui-row layui-form-item">
                    		<div class="layui-col-md1">
                            <label class="layui-form-label">领导条件</label></div>
                    			<div class="layui-col-md5 long-font">
                    				<label class="layui-form-label">直推人数</label>
                                    <div class="layui-input-block">
                                       
                                        <input readonly="readonly" value="${rule.leaderReferee } 人" required lay-verify="required|number"
                                               placeholder="请输入报单价格，单位：元"
                                               autocomplete="off" class="layui-input">
                                    </div>
                    			</div>
                    			
                    			<div class="layui-col-md6 long-font">
                    				<label class="layui-form-label">下线人数</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="leaderUnderNumber" value="${rule.leaderUnderNumber } 人" readonly="readonly" required lay-verify="required|number"
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
                                        <input type="text" name="leaderFirstRank" value="${rule.leaderFirstRank}" readonly="readonly" required lay-verify="required|number"
                                               placeholder="输入到达的排名"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md6 long-font">
                                    <label class="layui-form-label">奖励金额</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="leaderFirstRebate" value="${rule.leaderFirstRebate } 元" readonly="readonly" required lay-verify="required|number"
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
                                        <input type="text" name="leaderSecondRank" value="${rule.leaderSecondRank}" readonly="readonly" required lay-verify="required|number"
                                               placeholder="输入到达的排名"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md6 long-font">
                                    <label class="layui-form-label">奖励金额</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="leaderSecondRebate" value="${rule.leaderSecondRebate } 元" readonly="readonly" required lay-verify="required|number"
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
                                        <input type="text" name="leaderThirdRank" value="${rule.leaderThirdRank }" readonly="readonly" required lay-verify="required|number"
                                               placeholder="输入到达的排名"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-col-md6 long-font">
                                    <label class="layui-form-label">奖励金额</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="leaderThirdRebate" value="${rule.leaderThirdRebate } 元" readonly="readonly" required lay-verify="required|number"
                                               placeholder="请输入奖励金额，单位：元"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                            </div> 
                        </div>
                        
                        
                    </fieldset>
                    
                    
                    
                    
                   
                </div>
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
