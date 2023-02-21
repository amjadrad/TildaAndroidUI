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
import ir.tildaweb.tilda_android_ui.dialogs.DialogBottomSheetMultiSelect;
import ir.tildaweb.tilda_android_ui.dialogs.DialogBottomSheetSelect;


public class AdapterDialogMultiSelect extends RecyclerView.Adapter<AdapterDialogMultiSelect.ViewHolder> {

    private final String TAG = getClass().getName();
    private List<DialogBottomSheetSelect.SelectObject> list;
    private ArrayList<Integer> selectedIds;
    private DialogBottomSheetMultiSelect.OnItemsSelectListener onItemsSelectListener;

    public AdapterDialogMultiSelect(ArrayList<DialogBottomSheetSelect.SelectObject> list, DialogBottomSheetMultiSelect.OnItemsSelectListener onItemsSelectListener) {
        this.list = list;
        this.onItemsSelectListener = onItemsSelectListener;
        this.selectedIds = new ArrayList<>();
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
            item.setSelected(!item.isSelected());
            selectedIds.clear();
            for (DialogBottomSheetSelect.SelectObject selectObject : list) {
                if (selectObject.isSelected()) {
                    selectedIds.add(selectObject.getId());
                }
            }
            onItemsSelectListener.onItemsSelected(selectedIds);
            notifyDataSetChanged();
        });
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
