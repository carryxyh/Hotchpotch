package com.netease.music.musician.excel;

public class ExcelException extends Exception {

    private static final long serialVersionUID = -5383035568204230822L;

    /**
     * 异常编码
     */
    private String errorCode;

    /**
     * 异常描述
     */
    private String errorMsg;

    public ExcelException(String errorCode, String errorMsg){
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ExcelException(String errorCode, String errorMsg, Throwable t){
        super(errorMsg, t);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
