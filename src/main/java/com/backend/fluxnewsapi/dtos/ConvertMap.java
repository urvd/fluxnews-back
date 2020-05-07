package com.backend.fluxnewsapi.dtos;

import java.lang.reflect.Field;
import java.util.HashMap;

public class ConvertMap<T> {

    public HashMap<String,Object> convertOjectToMap(T t) throws IllegalAccessException {
        HashMap<String,Object> tMap = new HashMap<>();
        Field[] allFields = t.getClass().getDeclaredFields();
        for (Field field : allFields) {
            field.setAccessible(true);
            tMap.put(field.getName(),field.get(t));
        }
        return tMap;
    }

    public T convertMapToObject(HashMap<String, Object> tMap) throws IllegalAccessException {
        T t = null;
        Field[] allFields = t.getClass().getDeclaredFields();
        for (Field field : allFields) {
            field.setAccessible(true);
            field.set(t,tMap.get(field.getName()));
        }
        return t;
    }
}
