package com.econception.employemanagement.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static DateTimeFormatter DateFormat      = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    public static DateTimeFormatter DateTimeFormat  = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    public static DateTimeFormatter MysqlDateTime   = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static DateTimeFormatter MysqlDate       = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static DateTimeFormatter UnixTimeFormat  = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    public static DateTimeFormatter dashboardFormat = DateTimeFormatter.ofPattern("MM/d/yyyy");
    public static String MysqlCurrentDateTime(){
        return MysqlDateTime.format(LocalDate.now()).toString();
    }

    // Graph Formats
    public static DateTimeFormatter dayFormat = DateTimeFormatter.ofPattern("d-MMM");
    public static DateTimeFormatter weekFormat = DateTimeFormatter.ofPattern("w");
    public static DateTimeFormatter monthFormat = DateTimeFormatter.ofPattern("MMMyy");

    public static String MysqlCurrentDate(){
        return MysqlDate.format(LocalDate.now());
    }
}
