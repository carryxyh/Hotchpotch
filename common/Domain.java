package com.dfire.consumer.util.validator;


import com.dfire.consumer.i18n.code.PublicCode;
import com.dfire.consumer.util.RegexUtil;
import com.dfire.consumer.util.regex.Regex;
import com.dfire.consumer.util.validator.abstracts.ValidatorAdapter;

public class Domain extends ValidatorAdapter {

    /**
     * 验证是否域名
     *
     * @param object
     * @return
     */
    public boolean validate(Object object) {
        if (object == null) {
            return true;
        }
        String mobile = object.toString();
        if (RegexUtil.match(mobile, Regex.DOMAIN)) {
            return true;
        }
        setErrorCode(PublicCode.MULTI_102934.getErrCode());
        setMessage(PublicCode.MULTI_102934.getMultiCode());
        return false;
    }
}
