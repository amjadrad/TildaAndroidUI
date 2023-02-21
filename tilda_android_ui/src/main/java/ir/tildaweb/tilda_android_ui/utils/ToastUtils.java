package ir.tildaweb.tilda_android_ui.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import ir.tildaweb.tilda_android_ui.R;
import ir.tildaweb.tilda_android_ui.databinding.CustomToastBinding;


public class ToastUtils {

    public enum ToastType {
        NONE,
        SUCCESS,
        ERROR,
        WARNING,
        SUCCESS_INSERT,
        SUCCESS_DELETE,
        SUCCESS_UPDATE,
        ERROR_INSERT,
        ERROR_DELETE,
        ERROR_UPDATE,
    }

    public static void toast(Context context, String message, ToastType toastType) {
        String result = message;
        if (message == null) {
            switch (toastType) {
                case NONE:
                    break;
                case SUCCESS:
                    result = "با موفقیت انجام شد.";
                    break;
                case ERROR:
                    result = "مشکلی پیش آمد. مجددا تلاش کنید.";
                    break;
                case WARNING:
                    result = "مجددا تلاش کنید.";
                    break;
                case SUCCESS_INSERT:
                    result = "با موفقیت افزوده شد.";
                    break;
                case SUCCESS_DELETE:
                    result = "با موفقیت حذف شد.";
                    break;
                case SUCCESS_UPDATE:
                    result = "با موفقیت ویرایش شد.";
                    break;
                case ERROR_INSERT:
                    result = "مشکلی در افزودن " + message + " به وجود آمد.";
                    break;
                case ERROR_DELETE:
                    result = "مشکلی در حذف " + message + " به وجود آمد.";
                    break;
                case ERROR_UPDATE:
                    result = "مشکلی در ویرایش " + message + " به وجود آمد.";
                    break;
            }
        }

        int textColor = R.color.colorText;
        switch (toastType) {
            case SUCCESS:
            case SUCCESS_INSERT:
            case SUCCESS_DELETE:
            case SUCCESS_UPDATE:
                textColor = R.color.colorSuccess;
                break;
            case ERROR:
            case ERROR_INSERT:
            case ERROR_DELETE:
            case ERROR_UPDATE:
                textColor = R.color.colorDanger;
                break;
            case WARNING:
                textColor = R.color.colorWarning;
                break;
        }

        CustomToastBinding binding = CustomToastBinding.inflate(LayoutInflater.from(context));
        binding.tvMessage.setText(result);
        binding.tvMessage.setTypeface(ResourcesCompat.getFont(context, R.font.iran_yekan_fa_regular), Typeface.BOLD);
        binding.tvMessage.setTextColor(ContextCompat.getColor(context, textColor));
        binding.imageView.setColorFilter(ContextCompat.getColor(context, textColor), android.graphics.PorterDuff.Mode.SRC_IN);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER | Gravity.TOP, 0, 56);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(binding.getRoot());
        toast.show();
    }
}