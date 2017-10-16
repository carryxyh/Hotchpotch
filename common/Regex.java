package com.dfire.consumer.util.regex;

public class Regex {

    //http://www.cnblogs.com/zxin/archive/2013/01/26/2877765.html

    public static final String PHONE = "^(\\(\\d{3,4}-)|\\d{3.4}-)?\\d{7,8}$";

    public static final String MOBILE = "^0?(13[0-9]|15[0-9]|17[0-9]|18[0-9]|14[0-9])[0-9]{8}$";

    public static final String EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    public static final String DOMAIN = "[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(/.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+/.?";

    public static final String URL = "^http://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$";

    public static final String DATE = "^\\d{4}-\\d{1,2}-\\d{1,2}";

    public static final String ENTITY = "^.{9}$";

    public static final String ENTITY_ID = "^\\d{8,}$";

    public static final String SEAT_CODE = "";

}
