package mk.ukim.finki.wp.lab.web.filter;

import mk.ukim.finki.wp.lab.model.Course;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter
public class courseFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Long courseId = (Long) request.getSession().getAttribute("courseId");
//        String path =request.getServletPath();!"/courses".equals(path)

//                contains("/courses") && request.getSession().getAttribute("courseId") == null){
        if(!request.getServletPath().startsWith("/courses") && courseId == null){
            response.sendRedirect("/courses");
        }else{
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }
}
