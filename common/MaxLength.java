package com.dfire.consumer.util.validator;

import com.dfire.consumer.i18n.code.PublicCode;
import com.dfire.consumer.util.validator.abstracts.ValidatorAdapter;
import net.sf.oval.constraint.MaxLengthCheck;

public class MaxLength extends ValidatorAdapter {


    /**
     * 判断字符串是否大于指定数量
     * @param object
     * @param v
     * @return
     */
    public boolean validate(Object object,double[] v){
        MaxLengthCheck check = new MaxLengthCheck();
        check.setMax((int)v[0]);
        setErrorCode(PublicCode.MULTI_102939.getErrCode());
        //setMessage(PublicCode.MULTI_102939.getMessage().replace("{1}",v[0]+""));
        setMessage(PublicCode.MULTI_102939.getMultiCode());
        return check.isSatisfied(null,object,null,null);
    }

}
