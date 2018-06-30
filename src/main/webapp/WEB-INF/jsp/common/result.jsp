<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <jsp:include page="top.jsp"/>
    <style>
        .loading {
            width: 300px;
            height: 300px;
            line-height: 300px;
            font-size: 30px;
            background: #009688;
            border: 1px solid rgba(200, 200, 200, 0.2);
            border-radius: 50%;
            box-shadow: 2px 2px 3px #666;
            margin: 200px auto 0;
            text-align: center;
            color: #fff;
            animation: show .3s;
        }
        @keyframes show {
            0% {
                opacity: 0;
                margin-top: 400px;
            }
            100% {
                opacity: 1;
                margin-top: 200px;
            }
        }

    </style>
</head>
<body>
<div>
    <div class="loading">
        <span>${result.msg}...</span>
    </div>
</div>
<script>
    $(document).ready(function () {
        setTimeout(function(){
            window.location.href = '${pageContext.request.contextPath}/${result.href}'
        },1000)
    })
</script>
</body>
</html>
