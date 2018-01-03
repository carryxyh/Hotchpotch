package com.netease.music.musician.batch;

import java.util.List;

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
