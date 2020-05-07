package com.backend.fluxnewsapi.utils;

public class BooleanUtils {
    public static int convertInt(Boolean b){
        return b?1:0;
    }
    public static boolean convertBool(int t){
        return t == 1?true:false;
    }
}
