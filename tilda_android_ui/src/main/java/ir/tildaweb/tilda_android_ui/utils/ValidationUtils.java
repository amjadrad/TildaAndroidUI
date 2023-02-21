package ir.tildaweb.tilda_android_ui.utils;

import java.util.regex.Pattern;

public class ValidationUtils {

    public static String validatePhone(String phone) {
        if (isPhone(phone)) {
            if (phone.length() == 10 && phone.startsWith("9")) {
                return "0".concat(phone);
            } else if (phone.length() == 11 && phone.startsWith("09")) {
                return phone;
            } else if (phone.length() == 12 && phone.startsWith("989")) {
                return "0".concat(phone.substring(2));
            } else if (phone.length() == 13 && phone.startsWith("+989")) {
                return "0".concat(phone.substring(3));
            }
        }
        return null;
    }

    public static boolean isPhone(String phone) {
        Pattern pattern = Pattern.compile("09[0-9]{9}");
        return pattern.matcher(phone).find();
    }

}
