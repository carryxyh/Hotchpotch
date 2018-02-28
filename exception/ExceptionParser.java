import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExceptionParser {

    /**
     * 云音乐包前缀1
     */
    public static final String PACKAGE_1 = "com.XXX";

    /**
     * 云音乐包前缀2
     */
    public static final String PACKAGE_2 = "com.XXX";

    /**
     * 动态代理
     */
    private static final String CGLIB = "CGLIB";

    /**
     * 过滤器
     */
    private static final String DO_FILTER = ".filter.";

    /**
     * 切面
     */
    private static final String ASPECT_AROUND = ".aspect.";

    /**
     * 需要过滤的前缀
     */
    private static List<String> COMPANY_PACKAGE_NAME = Arrays.asList(PACKAGE_2, PACKAGE_1);

    public static void setCompanyPackageName(List<String> companyPackageName) {
        if (companyPackageName == null) {
            throw new NullPointerException(" company package names can not be null !");
        }
        COMPANY_PACKAGE_NAME = companyPackageName;
    }

    /**
     * 将一个异常转化成清理过堆栈之后的异常
     *
     * @param ex 异常
     * @return 清理过堆栈之后的异常
     */
    public Throwable parse(Throwable ex) {
        return cleanStackTrace(ex);
    }

    /**
     * 清理堆栈
     *
     * @param e 异常
     * @return 清理之后的异常
     */
    private Throwable cleanStackTrace(Throwable e) {
        StackTraceElement[] s = e.getStackTrace();
        List<StackTraceElement> afterClean = new ArrayList<>();
        for (StackTraceElement element : s) {
            if (isCompanyPackage(element.getClassName())) {
                afterClean.add(element);
            }
        }
        StackTraceElement[] finalStack = new StackTraceElement[afterClean.size()];
        e.setStackTrace(afterClean.toArray(finalStack));
        return e;
    }

    /**
     * 是否是公司包名前缀
     */
    private boolean isCompanyPackage(String name) {
        boolean clean1 = !StringUtils.isBlank(name) && !name.contains(CGLIB) && !name.contains(DO_FILTER) && !name.contains(ASPECT_AROUND);
        if (COMPANY_PACKAGE_NAME.size() == 0) {
            return clean1;
        }
        boolean clean2 = false;
        for (String n : COMPANY_PACKAGE_NAME) {
            clean2 = clean2 || name.startsWith(n);
        }
        return clean1 && clean2;
    }
}
