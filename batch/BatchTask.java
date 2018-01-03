package com.netease.music.musician.batch;

import java.util.List;

public interface BatchTask<Data extends List> {

    /**
     * 获取数据
     *
     * @return 数据
     */
    Data getData();

    /**
     * 对数据进行处理
     *
     * @param d data
     */
    void realDone(Data d);
}
