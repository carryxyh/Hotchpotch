package com.netease.music.musician.batch;

import java.util.List;

/**
 * DemoBatchTask
 *
 * @author 修宇航 [xiuyuhang@corp.netease.com]
 * @since 2017-11-23
 */
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
