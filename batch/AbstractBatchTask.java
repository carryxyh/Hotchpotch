package com.netease.music.musician.batch;

import java.util.List;

/**
 * AbstractBatchTask
 *
 * @author 修宇航 [xiuyuhang@corp.netease.com]
 * @since 2017-11-23
 */
public abstract class AbstractBatchTask<Data extends List> implements BatchTask<Data> {

    /**
     * data
     */
    private final Data data;

    protected AbstractBatchTask(Data d) {
        this.data = d;
    }

    public Data getData() {
        return data;
    }

    public abstract void realDone(Data d);
}
