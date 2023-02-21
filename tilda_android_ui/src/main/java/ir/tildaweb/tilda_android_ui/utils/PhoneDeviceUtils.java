package ir.tildaweb.tilda_android_ui.utils;

import android.os.Build;
import android.text.TextUtils;

public class PhoneDeviceUtils {

    public static int getAndroidSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    public static String getAndroidVersionRelease() {
        return Build.VERSION.RELEASE;
    }

    public static String getDeviceManufacturer() {
        return capitalize(Build.MANUFACTURER);
    }

    public static String getDeviceModel() {
        return Build.MODEL;
    }

    private static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;

        StringBuilder phrase = new StringBuilder();
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase.append(Character.toUpperCase(c));
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase.append(c);
        }

        return phrase.toString();
    }
}
