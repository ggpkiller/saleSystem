<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="../common/top.jsp"/>
    <style>
        #chart {
            width: 100%;
            height: 1600px;
        }

        .recommend span {
            color: #1E9FFF;
            text-decoration: underline;
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
                <h2 class="layui-col-md4">会员信息</h2>
            </div>
            <form class="layui-form" action="">
                <div class="layui-field-box">
                    <a style="cursor: pointer;margin-right: 2em" onclick="history.back()">[--返回--]</a><br><br>
                    <fieldset class="layui-elem-field">
                        <h2>基本信息</h2>
                        <div class="layui-field-box">
                            <div class="layui-form-item">
                                <div class="layui-col-md4">
                                    <label class="layui-form-label">姓名</label>
                                    <span class="layui-form-label" style="text-align: left;width: auto;">${member.name}</span>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <div class="layui-col-md4">
                                    <label class="layui-form-label">电话</label>
                                    <span class="layui-form-label"
                                          style="text-align: left;width: auto;">${member.mobile}</span>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <div class="layui-col-md4">
                                    <label class="layui-form-label">身份证号</label>
                                    <span class="layui-form-label"
                                          style="text-align: left;width: auto;">${member.idNumber}</span>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <div class="layui-col-md4">
                                    <label class="layui-form-label">现居住地址</label>
                                    <span class="layui-form-label" style="text-align: left;width: auto;">${member.address}</span>

                                </div>
                            </div>
                            <div class="layui-form-item">
                                <div class="layui-col-md4">
                                    <label class="layui-form-label">开户行名称</label>
                                    <span class="layui-form-label" style="text-align: left;width: auto;">${member.bankName}</span>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <div class="layui-col-md4">
                                    <label class="layui-form-label">银行卡号</label>
                                    <span class="layui-form-label"
                                          style="text-align: left;width: auto;">${member.bankAccount}</span>
                                </div>
                            </div>
                            
                            <div class="layui-form-item">
                                <div class="layui-col-md4">
                                    <label class="layui-form-label">积分</label>
                                    <span class="layui-form-label"
                                          style="text-align: left;width: auto;">${member.integral }</span>
                                </div>
                            </div>
                            
                            
                            
                            
                        </div>
                    </fieldset>
                    
                </div>

            </form>
        </div>
    </div>
</div>
<script src="/static/js/layui.all.js"></script>

<script>
    

    //JavaScript代码区域
    layui.use(['element', 'jquery'], function () {
        var element = layui.element;
        
    });
</script>
</body>
</html>
