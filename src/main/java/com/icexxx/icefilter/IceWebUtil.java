package com.icexxx.icefilter;

import javax.servlet.http.HttpServletRequest;

public class IceWebUtil {
    public static String getbasePath(HttpServletRequest httpRequest) {
        String localAddr = httpRequest.getLocalAddr();
        if ("0:0:0:0:0:0:0:1".equals(localAddr)) {
            localAddr = "127.0.0.1";
        }
        String basepath = httpRequest.getScheme() + "://" + localAddr + ":" + httpRequest.getServerPort()
                + httpRequest.getContextPath();
        return basepath;
    }

    public static String getUrl(HttpServletRequest httpRequest) {
        String requestURL = httpRequest.getRequestURL().toString();
        String queryString = httpRequest.getQueryString();
        String url = requestURL;
        if (queryString != null && !"".equals(queryString)) {
            url = requestURL + "?" + queryString;
        }
        return url;
    }

    public static String logMessage(String url, HttpServletRequest httpRequest) {
        if (url == null) {
            return "<<null>>";
        }
        boolean useStackTrace = IceFilterContext.isUseStackTrace();
        if (useStackTrace) {
            String stackTrace = stackTrace(httpRequest);
            if (IceFilterUtil.isNotBlank(stackTrace)) {
                url = stackTrace + " " + url;
            }
        }
        return "[url]:" + url;
    }

    private static String stackTrace(HttpServletRequest httpRequest) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[1];
        return stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + "("
                + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ")";
    }

}
