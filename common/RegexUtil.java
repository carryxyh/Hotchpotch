package com.dfire.consumer.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    public static boolean match(String str,String regex){
        if (str == null || str.length() == 0  || regex == null || regex.length() == 0) {
            return false;
        }
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
