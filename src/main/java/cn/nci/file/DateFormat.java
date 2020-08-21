package cn.nci.file;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
    public String getCurrentTime(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(new Date());
    }

    public static void main(String[] args) {
        DateFormat dateFormat = new DateFormat();
        String currentTime = dateFormat.getCurrentTime("yyyy-MM-dd HH:mm:ss");
        System.out.println(currentTime);
    }
}
