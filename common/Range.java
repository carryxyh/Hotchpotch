package com.dfire.consumer.util.validator;

import com.dfire.consumer.i18n.code.PublicCode;
import com.dfire.consumer.util.validator.abstracts.ValidatorAdapter;
import net.sf.oval.constraint.RangeCheck;

public class Range extends ValidatorAdapter {

    /**
     * 判断数据是否在 v1 与 v2区间内，包含v1 或v2
     * @param object
     * @param v
     * @return
     */
    public boolean validate(Object object,double[] v){
        setErrorCode(PublicCode.MULTI_102945.getErrCode());
        //setMessage(PublicCode.MULTI_102945.getMessage().replace("{1}",v[0]+"").replace("{2}",v[1]+""));
        setMessage(PublicCode.MULTI_102945.getMultiCode());
        RangeCheck check = new RangeCheck();
        check.setMin(v[0]);
        check.setMax(v[1]);
        return check.isSatisfied(null,object,null,null);
    }
}