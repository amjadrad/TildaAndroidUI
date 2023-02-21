package ir.tildaweb.tilda_android_ui.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class IntentUtils {

    private Context context;

    public IntentUtils(Context context) {
        this.context = context;
    }

    public void openSite(String link) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(link));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phone));
        context.startActivity(intent);
    }

}
