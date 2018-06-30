<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="../common/top.jsp"/>
    <style type="text/css">

        .relate {
            position: absolute;
            background: #fff;
            border: 1px solid #666;
            height: 200px;
            z-index: 100;
            width: 100%;
            box-sizing: border-box;
            display: none;
            overflow-y: scroll;
        }

        .relate li {
            padding: 10px;
            border-bottom: 1px solid #ededed;
        }

        .relate li:hover {
            background: #ededed;
            cursor: pointer;
        }

        .relate .userName {
            font-weight: bold;
            display: inline-block;
            margin-right: 50px;
        }

        .relate .userPhone {
            color: #666;
        }

        .relate .userId{
            display: none;
        }
    </style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <jsp:include page="../common/left.jsp"/>
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <div class="layui-row">
                <h2 class="layui-col-md4">添加报单</h2>
            </div>
            <form class="layui-form" action="${pageContext.request.contextPath}/member/createMember" method="post" autocomplete="off" style="margin-left: 100px;margin-top: 50px ">
                <div class="layui-field-box">
                    <div class="layui-form-item">
                        <div class="layui-col-md4">
                            <label class="layui-form-label">推荐人</label>
                            <div id="relateInput" class="layui-input-block">
                                <input name="refereeId" type="text" placeholder="请准确输入推荐人姓名"
                                       autocomplete="off" class="layui-input">
                                <ul class="relate">

                                </ul>
                            </div>
                        </div>
                        <datalist id="referee_association">

                        </datalist>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-col-md4">
                            <label class="layui-form-label">姓名</label>
                            <div class="layui-input-block">
                                <input id="keyword" type="text" name="name" required lay-verify="required" placeholder="请输入会员姓名"
                                       autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-col-md4">
                            <label class="layui-form-label">手机号码</label>
                            <div class="layui-input-block">
                                <input id="phone" type="number" name="mobile" required lay-verify="required|phone"
                                       placeholder="请输入会员电话"
                                       autocomplete="off" class="layui-input"
                                       maxlength="11">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-col-md4">
                            <label class="layui-form-label">身份证号</label>
                            <div class="layui-input-block">
                                <input type="text" name="idNumber" required lay-verify="required|identity" placeholder="请输入会员身份证号"
                                       autocomplete="off" class="layui-input" maxlength="18">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-col-md4">
                            <label class="layui-form-label">现居住地址</label>
                            <div class="layui-input-block">
                                <input type="text" name="address" required lay-verify="required"
                                       placeholder="请输入会员现居住地址"
                                       autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-col-md4">
                            <label class="layui-form-label">开户行名称</label>
                            <div class="layui-input-block">
                                <input type="text" name="bankName" required lay-verify="required"
                                       placeholder="请输入开户银行名称，例如工商银行XX支行"
                                       autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-col-md4">
                            <label class="layui-form-label">银行卡号</label>
                            <div class="layui-input-block">
                                <input type="number" name="bankAccount" required lay-verify="required|number"
                                       placeholder="请输入银行卡号"
                                       autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-col-md4">
                            <label class="layui-form-label">消费产品</label>
                            <div class="layui-input-block">
                                <select lay-verify="required" name="ruleId">
                                		
                                    <c:forEach items="${saleRules}" var="sr" varStatus="st">
                                        <option value="${sr.id}" <c:if test="${st.index == 0}">selected="selected"</c:if>>${sr.ruleName}&nbsp;&nbsp;${sr.formPrice}元</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-col-md4">
                            <label class="layui-form-label">会员账号</label>
                            <div class="layui-input-block">
                                <input id="account" type="text" name="account" required lay-verify="required"
                                       placeholder="账号同手机号"
                                       autocomplete="off" class="layui-input" readonly="readonly">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-col-md4">
                            <label class="layui-form-label">账号密码</label>
                            <div class="layui-input-block">
                                <input type="text" name="password" required lay-verify="required"
                                       placeholder="请输入会员账号密码"
                                       value="123456"
                                       autocomplete="off" class="layui-input" readonly="readonly">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                    <span style="color:red;margin-left:35px" id="notice_text"></span>
                    </div>
                    <div class="layui-form-item" style="margin-left: 240px;margin-top: 100px ">
                        <button class="layui-btn" lay-submit lay-filter="yes">保存</button>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>
<script src="/static/js/layui.all.js"></script>
<script>
    //JavaScript代码区域
    layui.use(['element', 'jquery','form'], function () {
        var element = layui.element;
        var form = layui.form;
        form.on('submit(yes)',function(data){
        		var ruleId = data.field.ruleId
        		var keyword = data.field.name
        		var refereeId = data.field.refereeId
        		var ok = true
        		$.ajax({
        			  type: "GET",
        			  url: '${pageContext.request.contextPath}/rest/associationMember?keyword='+keyword+"&ruleId="+ruleId,
        			  dataType: "json",
        			  async:false,
        			  success:function(data){
        				  console.log(data)
        				  if(data.data.length > 0){
              				var text = "["+keyword + "]已存在，建议名字改为别名再提交"
              				$('#notice_text').text(text)
              				ok = false
              			}
        			  }
        			});
        		if(keyword == refereeId){
        			var text = "["+keyword + "]推荐人与保单姓名相同，请修改"
      				$('#notice_text').text(text)
      				ok = false
        		}
        		return ok;
        })
    });
    
    
    
    
    $(document).ready(function(){
    		$("#phone").on('input',function(){
    			var phone = $(this).val();
    			$('#account').val(phone);
    		})
    		
    		
    		//$('.layui-btn').click()
    		
    		
    })
    
</script>
</body>
</html>
