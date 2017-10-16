package com.dfire.consumer.util.validator;

import com.dfire.consumer.i18n.code.PublicCode;
import com.dfire.consumer.util.RegexUtil;
import com.dfire.consumer.util.regex.Regex;
import com.dfire.consumer.util.validator.abstracts.ValidatorAdapter;

public class Mobile extends ValidatorAdapter {

    /**
     * 验证是否手机号码
     * @param object
     * @return
     */
    public boolean validate(Object object){
        if(object == null){
            return true;
        }
        String mobile = object.toString();
        if(RegexUtil.match(mobile, Regex.MOBILE)){
            return true;
        }
        setErrorCode(PublicCode.MULTI_102932.getErrCode());
        setMessage(PublicCode.MULTI_102932.getMultiCode());
        return false;
    }

}
