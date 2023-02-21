package ir.tildaweb.tilda_android_ui.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

import ir.tildaweb.tilda_android_ui.databinding.CustomTimePickerBinding;


public class CustomTimePickerDialog {

    private String TAG = this.getClass().getName();

    private final static int TIME_PICKER_INTERVAL = 5;
    private int minMinutes = 0;
    private int maxMinutes = 60;
    private int minHours = 0;
    private int maxHours = 48;
    private int currentHour = 0;
    private int currentMinute = 50;
    private String[] arrHour;
    private String[] arrMinute;

    private Context context;
    private AlertDialog alertDialog;
    private CustomTimePickerBinding binding;

    public CustomTimePickerDialog(Context context, int hour, int minute) {
        this.context = context;
        this.alertDialog = new AlertDialog.Builder(context).create();
        this.binding = CustomTimePickerBinding.inflate(LayoutInflater.from(context));
        this.alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.alertDialog.setView(binding.getRoot());
        this.alertDialog.setCancelable(false);
        this.currentHour = hour;
        this.currentMinute = minute;
        this.binding.btnCancel.setOnClickListener(view -> dismiss());
    }


    private void setTimes() {
        int hourSelection = 0;
        int minuteSelection = 0;
        List<String> displayedValues = new ArrayList<>();
        for (int i = minMinutes; i < maxMinutes; i += TIME_PICKER_INTERVAL) {
            displayedValues.add(String.format("%02d", i));
            if (i == currentMinute) {
                minuteSelection = displayedValues.size() - 1;
            }
        }
        arrMinute = new String[displayedValues.size()];
        displayedValues.toArray(arrMinute);


        binding.numberPickerMinute.setDisplayedValues(arrMinute);

        List<String> displayedValuesHours = new ArrayList<>();
        for (int i = minHours; i < maxHours; i++) {
            displayedValuesHours.add(String.format("%02d", i));
            if (i == currentHour) {
                hourSelection = displayedValuesHours.size() - 1;
            }
        }
        arrHour = new String[displayedValuesHours.size()];
        displayedValuesHours.toArray(arrHour);
        binding.numberPickerHour.setDisplayedValues(arrHour);
        binding.numberPickerHour.setMinValue(0);
        binding.numberPickerHour.setMaxValue(arrHour.length - 1);
        binding.numberPickerMinute.setMinValue(0);
        binding.numberPickerMinute.setMaxValue(arrMinute.length - 1);
        binding.numberPickerMinute.setValue(minuteSelection);
        binding.numberPickerHour.setValue(hourSelection);
    }

    public void setOnBtnConfirmClick(View.OnClickListener onBtnConfirmClick) {
        this.binding.btnConfirm.setOnClickListener(onBtnConfirmClick);
    }

    public void setOnBtnCancelClick(View.OnClickListener onBtnCancelClick) {
        this.binding.btnCancel.setOnClickListener(onBtnCancelClick);
    }

    public int getHour() {
        return Integer.parseInt(arrHour[binding.numberPickerHour.getValue()]);
    }

    public int getMinute() {
        return Integer.parseInt(arrMinute[binding.numberPickerMinute.getValue()]);
    }

    public String getHourString() {
        return arrHour[binding.numberPickerHour.getValue()];
    }

    public String getMinuteString() {
        return arrMinute[binding.numberPickerMinute.getValue()];
    }


    public void setMaxHours(int maxHours) {
        this.maxHours = maxHours;
    }

    public void setMinHours(int minHours) {
        this.minHours = minHours;
    }


    public void show() {
        setTimes();
        this.alertDialog.show();
    }

    public void dismiss() {
        this.alertDialog.dismiss();
    }

    public void setTitle(String title) {
        this.binding.tvTitle.setText(title + "");
    }


}