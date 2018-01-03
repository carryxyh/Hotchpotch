package com.netease.music.musician.batch;

import java.util.List;

public interface ExceptionHandler {

    /**
     * 异常处理
     *
     * @param data 本批次的数据
     * @param e    异常
     */
    void handle(List<?> data, Exception e);
}
