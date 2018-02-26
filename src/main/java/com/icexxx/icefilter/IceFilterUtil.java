package com.icexxx.icefilter;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class IceFilterUtil {

    public static void init() {
        IceFilterContext.setUseUTF8(false);
        IceFilterContext.setUseAllow(false);
        IceFilterContext.setUseLog(true);
        IceFilterContext.setUseSyso(false);
        IceFilterContext.setUseBasePath(false);
        IceFilterContext.setUseVersion(true);
        IceFilterContext.setUseVersionDev(false);
        IceFilterContext.setUseOtherPath(false);
        IceFilterContext.setUseDev(true);
        IceFilterContext.setUseStackTrace(false);
        IceFilterContext.setBasePath("basePath");
        IceFilterContext.setJsPath("js");
        IceFilterContext.setImgPath("img");
        IceFilterContext.setCssPath("css");
        IceFilterContext.setUploadPath("upload");
        IceFilterContext.setDev("dev");
        IceFilterContext.setVersion("version");
        IceFilterContext.setVersionDev("versionDev");
        IceFilterContext.setImgValue("img");
        IceFilterContext.setLog(create());
        IceFilterContext.setVersionValue(versionValue());
    }

    public static void prop(String fileName) {
        if (!fileName.endsWith(".properties")) {
            fileName += ".properties";
        }
        Properties prop = new Properties();
        InputStream inStream = IceFilterUtil.class.getClassLoader().getResourceAsStream(fileName);
        if (inStream != null) {
            try {
                prop.load(inStream);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inStream != null) {
                    try {
                        inStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            String useUTF8 = prop.getProperty("useUTF8");
            if (isNotBlankBoolean(useUTF8)) {
                IceFilterContext.setUseUTF8(convert(useUTF8));
            }
            String useAllow = prop.getProperty("useAllow");
            if (isNotBlankBoolean(useAllow)) {
                IceFilterContext.setUseAllow(convert(useAllow));
            }
            String useLog = prop.getProperty("useLog");
            if (isNotBlankBoolean(useLog)) {
                IceFilterContext.setUseLog(convert(useLog));
            }
            String useSyso = prop.getProperty("useSyso");
            if (isNotBlankBoolean(useSyso)) {
                IceFilterContext.setUseSyso(convert(useSyso));
            }
            String useBasePath = prop.getProperty("useBasePath");
            if (isNotBlankBoolean(useBasePath)) {
                IceFilterContext.setUseBasePath(convert(useBasePath));
            }
            String useVersion = prop.getProperty("useVersion");
            if (isNotBlankBoolean(useVersion)) {
                IceFilterContext.setUseVersion(convert(useVersion));
            }
            String useVersionDev = prop.getProperty("useVersionDev");
            if (isNotBlankBoolean(useVersionDev)) {
                IceFilterContext.setUseVersionDev(convert(useVersionDev));
            }
            String useOtherPath = prop.getProperty("useOtherPath");
            if (isNotBlankBoolean(useOtherPath)) {
                IceFilterContext.setUseOtherPath(convert(useOtherPath));
            }
            String useStackTrace = prop.getProperty("useStackTrace");
            if (isNotBlankBoolean(useStackTrace)) {
                IceFilterContext.setUseStackTrace(convert(useStackTrace));
            }
            String useDev = prop.getProperty("useDev");
            if (isNotBlankBoolean(useDev)) {
                IceFilterContext.setUseDev(convert(useDev));
            }
            String basePath = prop.getProperty("basePath");
            if (isNotBlank(basePath)) {
                IceFilterContext.setBasePath(basePath.trim());
            }
            String jsPath = prop.getProperty("jsPath");
            if (isNotBlank(jsPath)) {
                IceFilterContext.setJsPath(jsPath.trim());
            }
            String imgPath = prop.getProperty("imgPath");
            if (isNotBlank(imgPath)) {
                IceFilterContext.setImgPath(imgPath.trim());
            }
            String imgValue = prop.getProperty("imgValue");
            if (isNotBlank(imgValue)) {
                IceFilterContext.setImgValue(imgValue.trim());
            }
            String cssPath = prop.getProperty("cssPath");
            if (isNotBlank(cssPath)) {
                IceFilterContext.setCssPath(cssPath.trim());
            }
            String uploadPath = prop.getProperty("uploadPath");
            if (isNotBlank(uploadPath)) {
                IceFilterContext.setUploadPath(uploadPath.trim());
            }
            String dev = prop.getProperty("dev");
            if (isNotBlank(dev)) {
                IceFilterContext.setDev(dev.trim());
            }
            String version = prop.getProperty("version");
            if (isNotBlank(version)) {
                IceFilterContext.setVersion(version.trim());
            }
            String versionDev = prop.getProperty("versionDev");
            if (isNotBlank(versionDev)) {
                IceFilterContext.setVersionDev(versionDev.trim());
            }
            String log = prop.getProperty("log");
            if (isNotBlank(log)) {
                Object obj = create(log);
                IceFilterContext.setLog(obj);
            }
        }
    }

    static boolean isNotBlank(String str) {
        if (str == null || "".equals(str.trim())) {
            return false;
        }
        return true;
    }

    private static boolean isNotBlankBoolean(String str) {
        if (str == null) {
            return false;
        }
        if ("true".equalsIgnoreCase(str.trim())) {
            return true;
        }
        if ("false".equalsIgnoreCase(str.trim())) {
            return true;
        }
        return false;
    }

    private static boolean convert(String str) {
        if (str == null) {
            return false;
        }
        if ("true".equalsIgnoreCase(str.trim())) {
            return true;
        }
        return false;
    }

    public static String ext(String str) {
        if (str == null || "".equals(str.trim())) {
            return null;
        }
        int lastIndexOf = str.lastIndexOf("/");
        if (lastIndexOf == -1) {
            int lastPointIndex = str.lastIndexOf(".");
            if (lastPointIndex == -1) {
                return null;
            } else {
                return str.substring(lastPointIndex + 1);
            }
        } else {
            int lastPointIndex = str.lastIndexOf(".");
            if (lastPointIndex == -1) {
                return null;
            } else if (lastPointIndex <= lastIndexOf) {
                return null;
            } else {
                return str.substring(lastPointIndex + 1);
            }
        }
    }

    public static boolean contain(String[] array, String sub) {
        if (array == null || sub == null || array.length == 0) {
            return false;
        }
        for (int i = 0; i < array.length; i++) {
            if (sub.equalsIgnoreCase(array[i])) {
                return true;
            }
        }
        return false;
    }

    public static int cate(String ext) {
        if (ext == null) {
            return 0;
        }
        if ("json".equalsIgnoreCase(ext)) {
            return 2;
        }
        if (contain(new String[] { "jsp", "html", "htm", "do", "action" }, ext)) {
            return 1;
        }
        if (contain(new String[] { "js", "css", "xml", "txt" }, ext)) {
            return 3;
        }
        return 4;
    }

    public static String versionValue() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String versionValue = sdf.format(new Date());
        return versionValue;
    }

    private static void invoke(Object log, String methodName, String param) {
        Class class1 = log.getClass();
        Method declaredMethod = null;
        try {
            declaredMethod = class1.getMethod(methodName, Object.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        try {
            declaredMethod.invoke(log, param);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static Object create() {
        String logName = "org.apache.log4j.Logger";
        return create(logName);
    }

    private static Object create(String logName) {
        Class clazz = IceFilterUtil.class;
        Class<?> forName = null;
        try {
            forName = Class.forName(logName);
        } catch (ClassNotFoundException e) {
            // null
        }
        if (forName != null) {
            Method declaredMethod = null;
            try {
                declaredMethod = forName.getDeclaredMethod("getLogger", Class.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            }
            if (declaredMethod != null) {
                Object invoke = null;
                try {
                    invoke = declaredMethod.invoke(null, clazz);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                return invoke;
            }
        }
        return null;
    }

    public static void log(String logMessage) {
        Object log = IceFilterContext.getLog();
        if (log != null) {
            invoke(log, "info", logMessage);
        }
    }
}
