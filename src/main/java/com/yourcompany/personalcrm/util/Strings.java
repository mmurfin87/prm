package com.yourcompany.personalcrm.util;

public class Strings
{
    public static boolean equalsIgnoreCase(final String ...strings)
    {
        if (strings == null || strings.length < 1)
            return false;
        for (int i = 1; i < strings.length; i++)
            if ((strings[0] == null && strings[i] != null) || strings[0] != null && !strings[0].equalsIgnoreCase(strings[i]))
                return false;
        return true;
    }
}
