package com.iydsj.sw.common.dao;

import java.util.HashMap;
import java.util.Map;

public class ParamsMap {

    private Map<String, Object> params;

    private ParamsMap() {
        params = new HashMap<String, Object>();
    }

    public ParamsMap put(String key, Object value) {
        params.put(key, value);
        return this;
    }

    public static ParamsMap create(String key, Object value) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(key, value);
        return paramsMap;
    }

    public static ParamsMap empty() {
        ParamsMap paramsMap = new ParamsMap();
        return paramsMap;
    }

    public Map<String, Object> getParams() {
        return params;
    }
}