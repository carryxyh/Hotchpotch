package com.dfire.consumer.util.validator;

import com.dfire.consumer.i18n.code.PublicCode;
import com.dfire.consumer.util.validator.abstracts.ValidatorAdapter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Enum extends ValidatorAdapter {

    public boolean validate(final Object object, final double[] values){
        setErrorCode(PublicCode.MULTI_102947.getErrCode());
        if (null == object) {
            setMessage("不能为空");
            return false;
        } else {
            StringBuilder sb = new StringBuilder("");
            try {
                for (double v : values) {
                    sb.append(doubleTrans(v)).append(", ");
                    final double doubleValue = Double.parseDouble(object.toString());
                    if (v == doubleValue) {
                        return true;
                    }
                }
            } catch (final NumberFormatException e) {
                setMessage("参数转换失败，枚举验证目前仅支持验证数字类型");
                return false;
            }
            sb.setLength(sb.length() - 2);
            //setMessage(PublicCode.MULTI_102947.getMessage().replace("{}",sb.toString()));
            setMessage(PublicCode.MULTI_102947.getMultiCode());
            return false;
        }
    }

    public boolean validate(final Object object, final Class c){
        setErrorCode(PublicCode.MULTI_102947.getErrCode());
        if (null == object || c == null) {
            setMessage("不能为空");
            return false;
        } else {
            try {
                Object[] objs = c.getEnumConstants();
                Method getId = c.getMethod("getId");

                if (null == objs || objs.length == 0) {
                    setMessage("枚举中没有定义属性");
                    return false;
                } else {
                    StringBuilder sb = new StringBuilder("");
                    for (Object obj : objs) {
                        String v = getId == null ? obj.toString() : getId.invoke(obj).toString();
                        sb.append(v).append(", ");
                        if (object.toString().equals(v)) {
                            return true;
                        }
                    }
                    sb.setLength(sb.length() - 2);
                    //setMessage(PublicCode.MULTI_102947.getMessage().replace("{}",sb.toString()));
                    setMessage(PublicCode.MULTI_102947.getMultiCode());
                    return false;
                }
            } catch (NoSuchMethodException e) {
                setMessage("需要匹配的枚举属性请使用id并实现geiId方法");
                return false;
            } catch (IllegalAccessException | InvocationTargetException e) {
                setMessage("系统异常，验证失败");
                return false;
            }
        }
    }

    private String doubleTrans(double num) {
        String s = String.valueOf(num);
        if(s.indexOf(".") > 0){
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }
}
