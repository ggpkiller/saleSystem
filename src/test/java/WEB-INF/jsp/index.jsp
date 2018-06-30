<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<head>
    <jsp:include page="common/top.jsp"/>
    <!-- CSS -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/normalize/8.0.0/normalize.min.css">
    <link rel="stylesheet" href="/static/css/supersized.css">
    <link rel="stylesheet" href="/static/css/index.css">
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
</head>
<body>

<div class="page-container">
    <h1>中谷久福-新经济管理系统</h1>
    <div class="mainform">
    <form action="${pageContext.request.contextPath}/login" method="post" autocomplete="off">
        <input type="text" name="account" class="username" placeholder="请输入用户名">
        <input type="text" name="password" class="password" placeholder="请输入密码" onfocus="this.type = 'password'">
        <button type="submit">登&nbsp;录</button>
        <div class="error"><span>+</span></div>
    </form>
    </div>
</div>
<!-- Javascript -->
<script src="/static/js/supersized.3.2.7.min.js"></script>
<script src="/static/js/supersized-init.js"></script>
<script src="/static/js/index.js"></script>

</body>
