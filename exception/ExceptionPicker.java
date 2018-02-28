import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public final class ExceptionPicker {

    /**
     * >>
     */
    public static final String EXCEPTION_GT_GT = " >> ";

    /**
     * :
     */
    public static final String COLON = " : ";

    /**
     * 动态代理
     */
    public static final String CGLIB = "CGLIB";

    /**
     * 过滤器
     */
    public static final String DO_FILTER = ".filter.";

    /**
     * 切面
     */
    public static final String ASPECT_AROUND = ".aspect.";

    /**
     * 包名前缀 注意替换
     */
    public static final String PACKAGE_MUSIC = "com.XXXX";

    public static String pick(HttpServletRequest request, Exception ex) {
        StringBuilder contentBuilder = new StringBuilder();
        contentBuilder.append(request.getRequestURI());
        if (StringUtils.isNotBlank(request.getQueryString())) {
            contentBuilder.append("?").append(request.getQueryString());
        }
        contentBuilder.append(" ------ ").append(assembleMsg(ex.getMessage(), ex));
        return contentBuilder.toString();
    }

    /**
     * 组装信息
     */
    private static String assembleMsg(String message, Throwable e) {
        return e.getClass().getCanonicalName() + COLON + message + getExceptionCauseLine(e);
    }

    /**
     * 获取异常产生的行
     */
    private static String getExceptionCauseLine(Throwable e) {
        StringBuilder builder = new StringBuilder();
        for (StackTraceElement element : e.getStackTrace()) {
            if (isCompanyPackage(element.getClassName())) {
                builder.append(EXCEPTION_GT_GT).append(element.toString()).append(System.lineSeparator());
            }
        }
        return System.lineSeparator() + builder.toString();
    }

    /**
     * 是否是公司包名前缀
     */
    private static boolean isCompanyPackage(String name) {
        return !StringUtils.isBlank(name)
                && !name.contains(CGLIB)
                && !name.contains(DO_FILTER)
                && !name.contains(ASPECT_AROUND)
                && name.startsWith(PACKAGE_MUSIC);
    }
}
