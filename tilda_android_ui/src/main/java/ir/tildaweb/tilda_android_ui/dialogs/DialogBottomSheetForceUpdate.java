package ir.tildaweb.tilda_android_ui.dialogs;

import android.content.DialogInterface;
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
import ir.tildaweb.tilda_android_ui.databinding.DialogBottomSheetForceUpdateBinding;

public class DialogBottomSheetForceUpdate extends BottomSheetDialogFragment {

    private final String TAG = this.getClass().getName();
    private final String changes;

    public interface OnClickListener {
        void onUpdateClickListener();
        void onCancelClickListener();
        void onCancel();
    }

    private OnClickListener onClickListener;

    public DialogBottomSheetForceUpdate(String changes) {
        this.changes = changes;
    }

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
        DialogBottomSheetForceUpdateBinding binding = DialogBottomSheetForceUpdateBinding.inflate(inflater, container, false);

        if (this.changes != null)
            binding.tvChanges.setText(this.changes);
        binding.btnUpdate.setOnClickListener(view -> onClickListener.onUpdateClickListener());
        binding.btnCancel.setOnClickListener(view -> onClickListener.onCancelClickListener());
        return binding.getRoot();
    }

    @Override
    public void onCancel(DialogInterface dialog)
    {
        super.onCancel(dialog);
        onClickListener.onCancel();
    }

    public void show(FragmentManager fragmentManager) {
        this.show(fragmentManager, null);
    }
}
