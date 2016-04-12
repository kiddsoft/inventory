package pers.qfy.filter;

import java.io.IOException;
import javax.servlet.*;

/*
 * ����һ������������Ҫ��web.xml��ע������Ч
 * ���������������ǣ����������ó�utf-8�����ľͲ���������
 * */
public class EncodingFilter implements Filter{
	public void init(FilterConfig filterConfig) throws ServletException {}  
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException
    {
    	//����
    	request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        chain.doFilter(request, response);      
    }
	public void destroy() {}
}