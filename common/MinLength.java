package com.dfire.consumer.util.validator;

import com.dfire.consumer.i18n.code.PublicCode;
import com.dfire.consumer.util.validator.abstracts.ValidatorAdapter;
import net.sf.oval.constraint.MinLengthCheck;

public class MinLength extends ValidatorAdapter {

    /**
     * 判断字符串数量是否小于指定数量
     * @param object
     * @param v
     * @return
     */
    public boolean validate(Object object,double[] v){
        setErrorCode(PublicCode.MULTI_102941.getErrCode());
        //setMessage(PublicCode.MULTI_102941.getMessage().replace("{1}",v[0]+""));
        setMessage(PublicCode.MULTI_102941.getMultiCode());
        MinLengthCheck check = new MinLengthCheck();
        check.setMin((int)v[0]);
        return check.isSatisfied(null,object,null,null);
    }
}