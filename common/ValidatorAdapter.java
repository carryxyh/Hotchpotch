package com.dfire.consumer.util.validator.abstracts;

public abstract  class ValidatorAdapter {

    private String message = "default validator error message!";

    private String errorCode = "DEFAULT_VALIDATOR";

    private String multiCode = "MULTI_101550";

    /**
     * 把需要验证的对象传到，由继承类实现验证规则，返回true为验证通过。返回false为验证失败！
     * @param o
     * @return
     */
    public boolean validate(final Object o){return true;}

    public boolean validate(final Object o, final double[] v){return true;}

    public boolean validate(final Object o, final Class<?> c){return true;}

//    public boolean validate(Object o,double v1,double v2){return true;}

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMultiCode() {
        return multiCode;
    }

    public void setMultiCode(String multiCode) {
        this.multiCode = multiCode;
    }
}
