package com.backend.fluxnewsapi.utils;

import org.apache.commons.lang3.RandomStringUtils;
import java.util.List;

public class AutoGenerateUtils {
    //auto generate number
    public static long generateNumbers(int lenght, List<Long> existingIds) {
        long id;
        do {
            id = Long.valueOf(RandomStringUtils.randomNumeric(lenght));
        }while(existingIds.contains(id));
        return id;
    }
}
