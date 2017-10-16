package com.dfire.consumer.util.validator;

import com.dfire.consumer.i18n.code.PublicCode;
import com.dfire.consumer.util.validator.abstracts.ValidatorAdapter;
import net.sf.oval.constraint.MinCheck;

public class Min  extends ValidatorAdapter {
    /**
     * 判断是否小于指定数字
     * @param object
     * @param value
     * @return
     */
    public boolean validate(Object object, double[] value) {
        setErrorCode(PublicCode.MULTI_102940.getErrCode());
        //setMessage(PublicCode.MULTI_102940.getMessage().replace("{1}",value[0]+""));
        setMessage(PublicCode.MULTI_102940.getMultiCode());
        MinCheck check = new MinCheck();
        check.setMin(value[0]);
        return check.isSatisfied(null, object, null, null);
    }
}
