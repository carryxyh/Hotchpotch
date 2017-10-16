package com.dfire.consumer.util.validator;

import com.dfire.consumer.i18n.code.PublicCode;
import com.dfire.consumer.util.regex.Regex;
import com.dfire.consumer.util.validator.abstracts.ValidatorAdapter;
import com.dfire.validator.util.RegexUtil;

public class SeatCodeValidator extends ValidatorAdapter {
    /**
     * 验证桌位号（seat_code）是否合法
     *
     * @param object
     * @return
     */
    public boolean validate(Object object) {
        if (object == null) {
            return true;
        }
        String entityId = object.toString();
        if (RegexUtil.match(entityId, Regex.SEAT_CODE)) {
            return true;
        }
        setErrorCode(PublicCode.MULTI_102950.getErrCode());
        setMessage(PublicCode.MULTI_102950.getMultiCode());
        return false;
    }
}

