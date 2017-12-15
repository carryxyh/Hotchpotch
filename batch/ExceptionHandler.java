package com.netease.music.musician.batch;

import java.util.List;

/**
 * ExceptionHandler
 * 异常处理器
 *
 * @author 修宇航 [xiuyuhang@corp.netease.com]
 * @since 2017-12-15
 */
public interface ExceptionHandler {

    /**
     * 异常处理
     *
     * @param data 本批次的数据
     * @param e    异常
     */
    void handle(List<?> data, Exception e);
}
