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
    	//设置请求与响应的参数的语言类型 为 utf-8格式，因为这种格式支持中文，解决乱码问题
    	request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        //继续传递请求响应
        chain.doFilter(request, response);      
    }
    //结束函数，实现之，内容为空就行了
	public void destroy() {}
}