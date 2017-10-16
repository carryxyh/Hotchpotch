package com.dfire.consumer.util.validator;

import com.dfire.consumer.i18n.code.PublicCode;
import com.dfire.consumer.util.validator.abstracts.ValidatorAdapter;

public class NotBlank extends ValidatorAdapter {
    /**
     * 判断是否为空或Null 返回true,验证失败
     * @param object
     * @return
     */
    public boolean validate(Object object){
        setErrorCode(PublicCode.MULTI_102942.getErrCode());
        setMessage(PublicCode.MULTI_102942.getMultiCode());
        if(object == null){
            return true;
        }
        return isBlank(object.toString());
    }
    private boolean isBlank(String str) {
        int strLen;
        if ((strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((!Character.isWhitespace(str.charAt(i)))) {
                return false;
            }
        }
        return true;
    }
}
