package cn.bupt.edu.base.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    public static String replace(String str) {
        String destination = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            destination = m.replaceAll("");
        }
        return destination;
    }
}
