package ir.tildaweb.tilda_android_ui.dialogs;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

import ir.tildaweb.tilda_android_ui.R;
import ir.tildaweb.tilda_android_ui.adapters.AdapterDialogMultiSelect;
import ir.tildaweb.tilda_android_ui.databinding.DialogBottomSheetMultiselectBinding;

public class DialogBottomSheetMultiSelect extends BottomSheetDialogFragment {

    private final String TAG = this.getClass().getName();
    private final String title;
    private final String searchHint;
    private final ArrayList<DialogBottomSheetSelect.SelectObject> list;

    public interface OnItemsSelectListener {
        void onItemsSelected(ArrayList<Integer> selectedItems);
    }

    private OnItemsSelectListener onItemsSelectListener;

    public DialogBottomSheetMultiSelect(@Nullable String title, @Nullable String searchHint, ArrayList<DialogBottomSheetSelect.SelectObject> list) {
        this.title = title;
        this.searchHint = searchHint;
        this.list = list;
    }

    public void setClickListener(OnItemsSelectListener onItemsSelectListener) {
        this.onItemsSelectListener = onItemsSelectListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.ModalBottomSheetDialog);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DialogBottomSheetMultiselectBinding binding = DialogBottomSheetMultiselectBinding.inflate(inflater, container, false);

        if (this.title != null)
            binding.tvTitle.setText(this.title);
        if (this.searchHint != null)
            binding.etSearch.setHint(this.searchHint);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        AdapterDialogMultiSelect adapterDialogSelect = new AdapterDialogMultiSelect(list, selectedItems -> onItemsSelectListener.onItemsSelected(selectedItems));
        binding.recyclerView.setAdapter(adapterDialogSelect);

        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() > 0) {
                    ArrayList<DialogBottomSheetSelect.SelectObject> tempList = new ArrayList<>();
                    for (DialogBottomSheetSelect.SelectObject selectObject : list) {
                        if (selectObject.getTitle().contains(editable.toString())) {
                            tempList.add(selectObject);
                        }
                    }
                    adapterDialogSelect.updateItems(tempList);
                } else {
                    adapterDialogSelect.updateItems(list);
                }
            }
        });
        return binding.getRoot();
    }

    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, null);
    }
}
