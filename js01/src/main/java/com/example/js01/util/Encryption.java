package com.example.js01.util;

public class Encryption {

    public static String encryp(String str) {
        char[] chars = str.toCharArray();
        StringBuilder result = new StringBuilder();
        for (int i=chars.length-1; i >= 0; i--) {
            result.append(chars[i]).append(i%3).append(String.valueOf(chars[i])).append(i%2);
        }
        return result.toString();
    }

}
