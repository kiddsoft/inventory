package pers.qfy.filter;

import java.io.IOException;
import javax.servlet.*;

/*
 * 这是一个过滤器，需要在web.xml中注册后才生效
 * 本过滤器的作用是：将语言设置成utf-8，中文就不会乱码了
 * */
public class EncodingFilter implements Filter{
	public void init(FilterConfig filterConfig) throws ServletException {}  
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException
    {
    	//设置
    	request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        chain.doFilter(request, response);      
    }
	public void destroy() {}
}