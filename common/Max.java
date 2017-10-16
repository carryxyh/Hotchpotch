package com.dfire.consumer.util.validator;

import com.dfire.consumer.i18n.code.PublicCode;
import com.dfire.consumer.util.validator.abstracts.ValidatorAdapter;
import net.sf.oval.constraint.MaxCheck;

public class Max extends ValidatorAdapter{

    /**
     * 判断值是否大于value
     * @param object
     * @param value
     * @return
     */
    public boolean validate(Object object,double[] value){
        MaxCheck check = new MaxCheck();
        check.setMax(value[0]);
        setErrorCode(PublicCode.MULTI_102938.getErrCode());
        //setMessage(PublicCode.MULTI_102938.getMessage().replace("{1}",value[0]+""));
        setMessage(PublicCode.MULTI_102938.getMultiCode());
        return check.isSatisfied(null,object,null,null);
    }

}
