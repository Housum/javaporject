
package com.sishuok.chapter2.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "filter1", urlPatterns="/*",
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD},
        initParams={@WebInitParam(name="account",value="1234"),@WebInitParam(name="hotusm",value="1234")}
		)

public class Filter1 implements Filter {
    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
    	
    	String account = filterConfig.getInitParameter("account");
    	String hotusm = filterConfig.getInitParameter("hotusm");
    	
    	System.out.println("account:"+account+" hotusm:"+hotusm);
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        System.out.println("filter1===" + httpServletRequest.getRequestURI());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    	
    }
}
