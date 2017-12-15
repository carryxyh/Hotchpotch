package com.netease.music.musician.batch;

abstract class DemoData {

    protected final String name;

    protected DemoData(String name) {
        this.name = name;
    }

    public abstract void sayName();


}

