package ir.tildaweb.tilda_android_ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ir.tildaweb.tilda_android_ui.databinding.ItemDialogSelectBinding;
import ir.tildaweb.tilda_android_ui.dialogs.DialogBottomSheetSelect;


public class AdapterDialogSelect extends RecyclerView.Adapter<AdapterDialogSelect.ViewHolder> {

    private final String TAG = getClass().getName();
    private List<DialogBottomSheetSelect.SelectObject> list;
    private final DialogBottomSheetSelect.OnSelectListener onSelectListener;

    public AdapterDialogSelect(ArrayList<DialogBottomSheetSelect.SelectObject> list, DialogBottomSheetSelect.OnSelectListener onSelectListener) {
        this.list = list;
        this.onSelectListener = onSelectListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDialogSelectBinding itemBinding = ItemDialogSelectBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DialogBottomSheetSelect.SelectObject item = list.get(position);
        holder.tvTitle.setText(String.valueOf(item.getTitle()));
        holder.imageViewIcon.setImageDrawable(item.getIcon());
        if (item.isSelected()) {
            holder.tvSelected.setVisibility(View.VISIBLE);
        } else {
            holder.tvSelected.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(view -> {
            onSelectListener.onItemSelected(item.getId());
            changeSelection(item);
        });
    }

    private void changeSelection(DialogBottomSheetSelect.SelectObject selectedObject) {
        for (DialogBottomSheetSelect.SelectObject select : list) {
            select.setSelected(select.getId().intValue() == selectedObject.getId().intValue());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final AppCompatTextView tvTitle;
        private final AppCompatTextView tvSelected;
        private final AppCompatImageView imageViewIcon;

        public ViewHolder(@NonNull ItemDialogSelectBinding binding) {
            super(binding.getRoot());
            tvTitle = binding.tvTitle;
            imageViewIcon = binding.imageViewIcon;
            tvSelected = binding.tvSelected;
        }
    }

    public void addItems(ArrayList<DialogBottomSheetSelect.SelectObject> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void updateItems(ArrayList<DialogBottomSheetSelect.SelectObject> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public void clearAll() {
        this.list.clear();
        notifyDataSetChanged();
    }


}
