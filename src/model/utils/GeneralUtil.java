package model.utils;

import java.sql.Timestamp;

public class GeneralUtil {
    
    public static Timestamp formatToTimestamp(String str){
        return Timestamp.valueOf(str.replace("T", " ")+":00");
    }
}
