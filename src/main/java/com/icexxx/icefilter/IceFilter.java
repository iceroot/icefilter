package com.icexxx.icefilter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class IceFilter
 */
@WebFilter("/*")
public class IceFilter implements Filter {
    private static boolean load = false;
    static {
        if (!load) {
            IceFilterUtil.init();
            IceFilterUtil.prop("icefilter");
            load = true;
        }
    }

    /**
     * Default constructor.
     */
    public IceFilter() {

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
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String requestURI = httpRequest.getRequestURI();
            String ext = IceFilterUtil.ext(requestURI);
            int cate = IceFilterUtil.cate(ext);
            if (cate < 4) {
                if (cate <= 3) {
                    addUTF8(response);
                    if (cate <= 2) {
                        addLog(httpRequest, response);
                        if (cate <= 1) {
                            addAttr(httpRequest);
                        }
                    }
                }
            }
            chain.doFilter(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    private void addAttr(HttpServletRequest httpRequest) {
        if (IceFilterContext.isUseDev()) {
            String dev = IceFilterContext.getDev();
            httpRequest.setAttribute(dev, "dev");
        }
        if (IceFilterContext.isUseVersion()) {
            String version = IceFilterContext.getVersion();
            String versionValue = IceFilterContext.getVersionValue();
            httpRequest.setAttribute(version, versionValue);
        }
        if (IceFilterContext.isUseVersionDev()) {
            String versionDev = IceFilterContext.getVersionDev();
            String versionValue = IceFilterUtil.versionValue();
            httpRequest.setAttribute(versionDev, versionValue);
        }
        String basePath = null;
        if (IceFilterContext.isUseBasePath()) {
            basePath = IceWebUtil.getbasePath(httpRequest);
            if (basePath != null) {
                String key = IceFilterContext.getBasePath();
                httpRequest.setAttribute(key, basePath);
            }
        }
        if (IceFilterContext.isUseOtherPath()) {
            if (basePath == null) {
                basePath = IceWebUtil.getbasePath(httpRequest);
            }
            if (basePath != null) {
                String cssPath = IceFilterContext.getCssPath();
                String jsPath = IceFilterContext.getJsPath();
                String imgPath = IceFilterContext.getImgPath();
                String uploadPath = IceFilterContext.getUploadPath();
                String imgValue = IceFilterContext.getImgValue();
                if (imgValue == null) {
                    imgValue = "img";
                }
                httpRequest.setAttribute(cssPath, basePath + "/css");
                httpRequest.setAttribute(jsPath, basePath + "/js");
                httpRequest.setAttribute(imgPath, basePath + "/" + imgValue);
                httpRequest.setAttribute(uploadPath, basePath + "/upload");
            }
        }

    }

    private void addLog(HttpServletRequest httpRequest, ServletResponse response) {
        String url = IceWebUtil.getUrl(httpRequest);
        if (IceFilterContext.isUseLog()) {
            IceFilterUtil.log(IceWebUtil.logMessage(url, httpRequest));
        }
        if (IceFilterContext.isUseSyso()) {
            System.out.println(IceWebUtil.logMessage(url, httpRequest));
        }
        if (IceFilterContext.isUseAllow()) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        }
    }

    public void addUTF8(HttpServletRequest httpRequest) {
        if (IceFilterContext.isUseUTF8()) {
            try {
                httpRequest.setCharacterEncoding("UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

    }

    private void addUTF8(ServletResponse response) {
        if (response instanceof HttpServletResponse) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            if (IceFilterContext.isUseUTF8()) {
                httpResponse.setCharacterEncoding("UTF-8");
            }
        }

    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {

    }

}
