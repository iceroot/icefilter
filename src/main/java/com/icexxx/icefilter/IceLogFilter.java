package com.icexxx.icefilter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet Filter implementation class Main
 */
@WebFilter("/*")
public class IceLogFilter implements Filter {
    private static Logger log = LoggerFactory.getLogger(IceLogFilter.class);

    public IceLogFilter() {

    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {

    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (response instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String requestURL = httpRequest.getRequestURL().toString();
            String queryString = httpRequest.getQueryString();
            String url = requestURL;
            if (queryString != null && "".equals(queryString)) {
                url = requestURL + "?" + queryString;
            }
            log(url);
            chain.doFilter(httpRequest, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    private void log(String url) {
        url = "[url]:" + url;
        log.info(url);
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {

    }

}
