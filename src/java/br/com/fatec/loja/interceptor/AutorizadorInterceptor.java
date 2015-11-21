package br.com.fatec.loja.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.portlet.handler.HandlerInterceptorAdapter;


public class AutorizadorInterceptor extends HandlerInterceptorAdapter{
    
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller) throws Exception{
        String uri = request.getRequestURI();
        if(uri.endsWith("loginForm") || uri.endsWith("efetuaLogin") || uri.contains("resources")){
            return true;
        }    
        if(request.getSession().getAttribute("user")!=null){    
        return true;
        }else{
           response.sendRedirect("loginForm");
            return false;
        }
    }
    
}
