package com.dfire.consumer.util.validator;

import com.dfire.consumer.i18n.code.PublicCode;
import com.dfire.consumer.util.validator.abstracts.ValidatorAdapter;

public class NotNull extends ValidatorAdapter {

    /**
     * 判断是否为null，null返回false,验证失败
     * @param object
     * @return
     */
    public boolean validate(Object object){
        setErrorCode(PublicCode.MULTI_102944.getErrCode());
        setMessage(PublicCode.MULTI_102944.getMultiCode());
        return object == null;
    }
}
