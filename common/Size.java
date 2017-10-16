package com.dfire.consumer.util.validator;

import com.dfire.consumer.i18n.code.PublicCode;
import com.dfire.consumer.util.validator.abstracts.ValidatorAdapter;
import net.sf.oval.constraint.SizeCheck;

public class Size extends ValidatorAdapter {

    /**
     * 判断指定字符串是否在v1 与 v2范围内，包含v1 与 v2
     * @param object
     * @param v
     * @return
     */
    public boolean validate(Object object,double[] v){
        setErrorCode(PublicCode.MULTI_102946.getErrCode());
        //setMessage(PublicCode.MULTI_102946.getMessage().replace("{1}",v[0]+"").replace("{2}",v[1]+""));
        setMessage(PublicCode.MULTI_102946.getMultiCode());
        SizeCheck check = new SizeCheck();
        check.setMin((int)v[0]);
        check.setMax((int)v[1]);
        return check.isSatisfied(null,object,null,null);
    }

}