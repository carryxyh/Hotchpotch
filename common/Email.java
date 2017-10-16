package com.dfire.consumer.util.validator;

import com.dfire.consumer.i18n.code.PublicCode;
import com.dfire.consumer.util.validator.abstracts.ValidatorAdapter;
import net.sf.oval.constraint.EmailCheck;

public class Email extends ValidatorAdapter {

    /**
     * 验证邮箱格式正确性
     * @param object
     * @return
     */
    public boolean validate(Object object){
        setErrorCode(PublicCode.MULTI_102933.getErrCode());
        setMessage(PublicCode.MULTI_102933.getMultiCode());
        return new EmailCheck().isSatisfied(null,object,null,null);
    }

}
