package com.icexxx.icefilter;

public class IceFilterContext {
    private static boolean useUTF8;
    private static boolean useAllow;
    private static boolean useLog;
    private static boolean useSyso;
    private static boolean useBasePath;
    private static boolean useVersion;
    private static boolean useVersionDev;
    private static boolean useOtherPath;
    private static boolean useDev;
    private static boolean useStackTrace;
    private static String basePath;
    private static String jsPath;
    private static String imgPath;
    private static String cssPath;
    private static String uploadPath;
    private static String dev;
    private static String version;
    private static String versionDev;
    private static String imgValue;
    private static Object log;
    private static String versionValue;

    public static boolean isUseUTF8() {
        return useUTF8;
    }

    public static void setUseUTF8(boolean useUTF8) {
        IceFilterContext.useUTF8 = useUTF8;
    }

    public static boolean isUseAllow() {
        return useAllow;
    }

    public static void setUseAllow(boolean useAllow) {
        IceFilterContext.useAllow = useAllow;
    }

    public static boolean isUseLog() {
        return useLog;
    }

    public static void setUseLog(boolean useLog) {
        IceFilterContext.useLog = useLog;
    }

    public static boolean isUseSyso() {
        return useSyso;
    }

    public static void setUseSyso(boolean useSyso) {
        IceFilterContext.useSyso = useSyso;
    }

    public static boolean isUseBasePath() {
        return useBasePath;
    }

    public static void setUseBasePath(boolean useBasePath) {
        IceFilterContext.useBasePath = useBasePath;
    }

    public static boolean isUseVersion() {
        return useVersion;
    }

    public static void setUseVersion(boolean useVersion) {
        IceFilterContext.useVersion = useVersion;
    }

    public static boolean isUseVersionDev() {
        return useVersionDev;
    }

    public static void setUseVersionDev(boolean useVersionDev) {
        IceFilterContext.useVersionDev = useVersionDev;
    }

    public static boolean isUseOtherPath() {
        return useOtherPath;
    }

    public static void setUseOtherPath(boolean useOtherPath) {
        IceFilterContext.useOtherPath = useOtherPath;
    }

    public static boolean isUseDev() {
        return useDev;
    }

    public static void setUseDev(boolean useDev) {
        IceFilterContext.useDev = useDev;
    }

    public static boolean isUseStackTrace() {
        return useStackTrace;
    }

    public static void setUseStackTrace(boolean useStackTrace) {
        IceFilterContext.useStackTrace = useStackTrace;
    }

    public static String getBasePath() {
        return basePath;
    }

    public static void setBasePath(String basePath) {
        IceFilterContext.basePath = basePath;
    }

    public static String getJsPath() {
        return jsPath;
    }

    public static void setJsPath(String jsPath) {
        IceFilterContext.jsPath = jsPath;
    }

    public static String getImgPath() {
        return imgPath;
    }

    public static void setImgPath(String imgPath) {
        IceFilterContext.imgPath = imgPath;
    }

    public static String getCssPath() {
        return cssPath;
    }

    public static void setCssPath(String cssPath) {
        IceFilterContext.cssPath = cssPath;
    }

    public static String getUploadPath() {
        return uploadPath;
    }

    public static void setUploadPath(String uploadPath) {
        IceFilterContext.uploadPath = uploadPath;
    }

    public static String getDev() {
        return dev;
    }

    public static void setDev(String dev) {
        IceFilterContext.dev = dev;
    }

    public static String getVersion() {
        return version;
    }

    public static void setVersion(String version) {
        IceFilterContext.version = version;
    }

    public static String getVersionDev() {
        return versionDev;
    }

    public static void setVersionDev(String versionDev) {
        IceFilterContext.versionDev = versionDev;
    }

    public static String getImgValue() {
        return imgValue;
    }

    public static void setImgValue(String imgValue) {
        IceFilterContext.imgValue = imgValue;
    }

    public static Object getLog() {
        return log;
    }

    public static void setLog(Object log) {
        IceFilterContext.log = log;
    }

    public static String getVersionValue() {
        return versionValue;
    }

    public static void setVersionValue(String versionValue) {
        IceFilterContext.versionValue = versionValue;
    }

}
