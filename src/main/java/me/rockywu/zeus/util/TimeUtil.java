package me.rockywu.zeus.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author RockyWu
 * @date 2019-01-23 22:25
 */
public class TimeUtil {
    /**
     * @return String 时间戳, 比如20190123222930
     */
    public static String getTimeStamps(){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime time = LocalDateTime.now();
        return df.format(time);
    }

    public static Timestamp getTimestamp(){
        LocalDateTime time = LocalDateTime.now();
        return Timestamp.valueOf(time);
    }

    public static void main(String[] args) {
        System.out.println(getTimeStamps());
    }
}
