package ir.tildaweb.tilda_android_ui.dialogs;


import android.graphics.drawable.Drawable;
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
import ir.tildaweb.tilda_android_ui.adapters.AdapterDialogSelect;
import ir.tildaweb.tilda_android_ui.databinding.DialogBottomSheetSelectBinding;


public class DialogBottomSheetSelect extends BottomSheetDialogFragment {

    private final String TAG = this.getClass().getName();
    private final String title;
    private final String searchHint;
    private final ArrayList<SelectObject> list;

    public static class SelectObject {

        private Integer id;
        private String title;
        private Drawable icon;
        private boolean isSelected;

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Drawable getIcon() {
            return icon;
        }

        public void setIcon(Drawable icon) {
            this.icon = icon;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }


    public interface OnSelectListener {
        void onItemSelected(int id);
    }

    private OnSelectListener onSelectListener;

    public DialogBottomSheetSelect(@Nullable String title, @Nullable String searchHint, ArrayList<SelectObject> list) {
        this.title = title;
        this.searchHint = searchHint;
        this.list = list;
    }

    public void setClickListener(OnSelectListener onSelectListener) {
        this.onSelectListener = onSelectListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.ModalBottomSheetDialog);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DialogBottomSheetSelectBinding binding = DialogBottomSheetSelectBinding.inflate(inflater, container, false);

        if (this.title != null)
            binding.tvTitle.setText(this.title);
        if (this.searchHint != null)
            binding.etSearch.setHint(this.searchHint);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        AdapterDialogSelect adapterDialogSelect = new AdapterDialogSelect(list, id -> onSelectListener.onItemSelected(id));
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
                    ArrayList<SelectObject> tempList = new ArrayList<>();
                    for (SelectObject selectObject : list) {
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
