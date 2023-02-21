package ir.tildaweb.tilda_android_ui.dialogs;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import ir.tildaweb.tilda_android_ui.R;
import ir.tildaweb.tilda_android_ui.databinding.DialogBottomSheetSigninHelpBinding;


public class DialogBottomSheetSigninHelp extends BottomSheetDialogFragment {

    private final String TAG = this.getClass().getName();

    public interface OnClickListener {
        void onCallClickListener();
        void onFAQClickListener();
    }

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.ModalBottomSheetDialog);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DialogBottomSheetSigninHelpBinding binding = DialogBottomSheetSigninHelpBinding.inflate(inflater, container, false);
        binding.linearCall.setOnClickListener(view -> onClickListener.onCallClickListener());
        binding.linearFAQ.setOnClickListener(view -> onClickListener.onFAQClickListener());
        return binding.getRoot();
    }

    public void show(FragmentManager fragmentManager) {
        this.show(fragmentManager, null);
    }
}
