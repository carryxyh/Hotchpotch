package com.netease.music.musician.batch;

import java.util.List;

public class DemoBatchTask extends AbstractBatchTask<List<DemoData>> {

    protected DemoBatchTask(List<DemoData> d) {
        super(d);
    }

    @Override
    public void realDone(List<DemoData> d) {
        for (DemoData demoData : d) {
            demoData.sayName();
        }
    }
}
