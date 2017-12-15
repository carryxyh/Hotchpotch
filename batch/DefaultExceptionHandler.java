package com.netease.music.musician.batch;

import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * DefaultExceptionHandler
 * 默认异常处理器
 *
 * @author 修宇航 [xiuyuhang@corp.netease.com]
 * @since 2017-12-15
 */
public class DefaultExceptionHandler implements ExceptionHandler {

    @Override
    public void handle(List<?> data, Exception e) {
        if (CollectionUtils.isEmpty(data)) {
            System.out.println("this batch data is empty ! ex is : " + e.toString());
        } else {
            System.out.println("this batch data is " + data.toString() + " and ex is : " + e.toString());
        }
    }
}
