package xyz.nxlexiaoyao.sales.common;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;


@Aspect
@Component
public class LogAspect {
    private static final Log logger = LogFactory.getLog(LogAspect.class);
    /**
     * controller切面
     */
    @Pointcut("execution(public * xyz.nxlexiaoyao.sales.controller.*.*.*(..))")
    public void controllerAspect(){}



    @AfterReturning(value = "controllerAspect()",returning = "rt")
    public void doBefore(Object rt) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.debug("↓↓↓↓↓↓↓↓↓↓↓↓↓请求记录↓↓↓↓↓↓↓↓↓↓↓↓↓");
        logger.debug("URL="+request.getRequestURI());
        logger.debug("Method="+request.getMethod());
        logger.debug("RemoteIP="+request.getRemoteAddr());
        logger.debug("QueryString="+request.getQueryString());
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String headerName = headerNames.nextElement();
            logger.debug("Header["+headerName +"]=[{"+request.getHeader(headerName)+"}]");
        }
        logger.debug("ResponseBody="+JSON.toJSON(rt));//对于返回类型为Application的才可以输出
        logger.debug("↑↑↑↑↑↑↑↑↑↑↑↑↑请求记录↑↑↑↑↑↑↑↑↑↑↑↑↑\n");

    }

}
