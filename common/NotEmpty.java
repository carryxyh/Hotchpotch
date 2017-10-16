package com.dfire.consumer.util.validator;

import com.dfire.consumer.i18n.code.PublicCode;
import com.dfire.consumer.util.validator.abstracts.ValidatorAdapter;
import net.sf.oval.constraint.NotEmptyCheck;

public class NotEmpty extends ValidatorAdapter {
    /**
     * 只判断是否为空，NULL 返回true,验证通过
     * @param object
     * @return
     */
    public boolean validate(Object object){
        setErrorCode(PublicCode.MULTI_102943.getErrCode());
        setMessage(PublicCode.MULTI_102943.getMultiCode());
        return new NotEmptyCheck().isSatisfied(null,object,null,null);
    }
}
