package com.netease.music.musician.batch;

import java.util.List;

/**
 * BatchTask
 *
 * @author 修宇航 [xiuyuhang@corp.netease.com]
 * @since 2017-11-23
 */
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
