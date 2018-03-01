import com.netease.music.musician.cloudbean.common.utils.LoggerUtil;
import com.netease.music.musician.exception.picker.ExceptionParser;
import com.netease.music.musician.exception.picker.ExceptionPicker;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * ExceptionCenter
 * 异常处理中心
 *
 * @author 修宇航 [xiuyuhang]
 * @since 2018-03-01
 */
public final class ExceptionCenter {

    /**
     * 分割线
     */
    private static final String SEPARATOR = " ---- ";

    /**
     * 异常只记录日志
     *
     * @param e               e
     * @param request         请求
     * @param cleanStackTrace 是否清理堆栈
     */
    public static void errorLogOnly(Exception e, HttpServletRequest request, boolean cleanStackTrace) {
        LoggerUtil.error.error(pick(request, e, cleanStackTrace));
    }

    /**
     * 异常记录日志并发送overmind
     *
     * @param e               e
     * @param request         请求
     * @param cleanStackTrace 是否清理堆栈
     */
    public static void errorLogAndOvermind(Exception e, HttpServletRequest request, boolean cleanStackTrace) {
        if (request == null) {
            LoggerUtil.error.error(" ", parse(e, cleanStackTrace));
        } else {
            LoggerUtil.error.error(requestInfo(request), parse(e, cleanStackTrace));
        }
    }

    /**
     * job异常只记录日志
     *
     * @param jobName         job名称
     * @param e               e
     * @param cleanStackTrace 是否清理堆栈
     */
    public static void jobLogOnly(String jobName, Exception e, boolean cleanStackTrace) {
        LoggerUtil.job.error(jobName + SEPARATOR + pick(null, e, cleanStackTrace));
    }

    /**
     * job异常记录日志并发送overmind
     *
     * @param jobName         job名称
     * @param e               e
     * @param cleanStackTrace 是否清理堆栈
     */
    public static void jobLogAndOvermind(String jobName, Exception e, boolean cleanStackTrace) {
        LoggerUtil.job.error(jobName, parse(e, cleanStackTrace));
    }

    /**
     * 消息处理异常只记录日志
     *
     * @param topicAndKey     topic-key
     * @param e               e
     * @param cleanStackTrace 是否清理堆栈
     */
    public static void messageLogOnly(String topicAndKey, Exception e, boolean cleanStackTrace) {
        LoggerUtil.message.error(topicAndKey + SEPARATOR + pick(null, e, cleanStackTrace));
    }

    /**
     * 消息处理异常记录日志并发送overmind
     *
     * @param topicAndKey     topic-key
     * @param e               e
     * @param cleanStackTrace 是否清理堆栈
     */
    public static void messageLogAndOvermind(String topicAndKey, Exception e, boolean cleanStackTrace) {
        LoggerUtil.job.error(topicAndKey, parse(e, cleanStackTrace));
    }

    /**
     * 从异常中找出需要的信息，并且拼装request的信息
     *
     * @param request http request
     * @param ex      异常信息
     * @return 日志内容
     */
    private static String pick(HttpServletRequest request, Exception ex, boolean cleanStackTrace) {
        if (cleanStackTrace) {
            return request == null ? ExceptionPicker.pick(ex) : requestInfo(request) + SEPARATOR + ExceptionPicker.pick(ex);
        }
        return request == null ? ex.toString() : requestInfo(request) + SEPARATOR + ex.toString();
    }

    /**
     * 组装request信息
     *
     * @param request request
     * @return url?param
     */
    private static String requestInfo(HttpServletRequest request) {
        StringBuilder requestInfo = new StringBuilder();
        requestInfo.append(request.getRequestURI());
        if (StringUtils.isNotBlank(request.getQueryString())) {
            requestInfo.append("?").append(request.getQueryString());
        }
        return requestInfo.toString();
    }

    /**
     * 转化异常
     *
     * @param ex              异常
     * @param cleanStackTrace 是否清理堆栈
     * @return 处理之后的异常
     */
    private static Throwable parse(Exception ex, boolean cleanStackTrace) {
        if (cleanStackTrace) {
            return ExceptionParser.parse(ex);
        }
        return ex;
    }
}
