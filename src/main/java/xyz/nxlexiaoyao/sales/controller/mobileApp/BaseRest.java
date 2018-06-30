package xyz.nxlexiaoyao.sales.controller.mobileApp;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import xyz.nxlexiaoyao.sales.bean.BaseBean;
import xyz.nxlexiaoyao.sales.bean.Member;

import javax.servlet.http.HttpServletRequest;

public class BaseRest {
    protected <T> BaseBean<T> success(){
        return new BaseBean<T>(1,"请求成功",null);
    }

    protected <T> BaseBean<T> success(T t){
        return new BaseBean<T>(1,"请求成功",t);
    }

    protected <T> BaseBean<T> success(T t,String msg) {
        return new BaseBean<T>(1,msg,t);
    }

    protected <T> BaseBean<T> fail(T t) {
        return new BaseBean<T>(2,"请求失败",t);
    }

    protected <T> BaseBean<T> fail(T t,String msg) {
        return new BaseBean<T>(2,msg,t);
    }

    protected <T> BaseBean<T> fail() {
        return new BaseBean<T>(2,"请求失败",null);
    }

    protected <T> BaseBean<T> fail(String msg) {
        return new BaseBean<T>(2,msg,null);
    }

    protected String getUserId(){
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        return request.getHeader("userId");
    }
}
