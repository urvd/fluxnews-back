package com.backend.fluxnewsapi.utils;

public class StringUtils {
    public static boolean equalsOrNotEmmptyAndNull(String s1, String s2){
        if (s1 == null || s2 == null ) return false;
        if(s1.isEmpty() || s2.isEmpty())return false;
        if(s1.equals(s2)) return true;
        else return false;
    }
}
