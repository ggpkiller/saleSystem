package xyz.nxlexiaoyao.sales.common;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import xyz.nxlexiaoyao.sales.bean.BaseBean;
import xyz.nxlexiaoyao.sales.bean.Token;

public class SignInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /**
         * 跨域
         *
         */
        /*response.setHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET");
	    response.setHeader("Access-Control-Max-Age", "3600");*/
        if(request.getRequestURI().indexOf(".do") > -1){
        	String userId = request.getHeader("userId");
        	String authorization = request.getHeader("Authorization");
        	
        	
        	if(!StringUtils.isEmpty(userId) && !StringUtils.isEmpty(authorization)) {
        		Token token = MobileSession.getToken(authorization);
        		if(token != null) {
        			String tokenUserId = token.getUserId();
        			if( userId.equals(tokenUserId)) {
        				MobileSession.flushTimestamp(token.getToken());
        				return true;
        			}
        		}
        	}
        	BaseBean<String> baseBean = new BaseBean<String>();
        	baseBean.setCode(0);
        	baseBean.setData(null);
        	baseBean.setMsg("Authorization Failed.");
        	String res = JSON.toJSONString(baseBean);
        	response.setContentType("application/JSON;charset=UTF-8");
        	PrintWriter out = response.getWriter();
        	out.write(res);
        	out.flush();
            return false;
        }
        HttpSession session = request.getSession(true);
        Object manager = session.getAttribute("manager");
        if(manager == null){
            System.out.println("拦截请求:"+request.getRequestURI());
            request.getRequestDispatcher("/").forward(request, response);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
