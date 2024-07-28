package com.yourcompany.personalcrm.util;

import java.time.format.DateTimeFormatter;

public class Dates
{
    public static DateTimeFormatter YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static DateTimeFormatter MONTH_DAY_COMMA_YEAR = DateTimeFormatter.ofPattern("MMMM d, yyyy");
}
