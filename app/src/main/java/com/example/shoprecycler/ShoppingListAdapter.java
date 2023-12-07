package com.example.shoprecycler;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {
    private List<ShoppingItem> shoppingItems;
    private Context context;

    public ShoppingListAdapter(List<ShoppingItem> shoppingItems, Context context) {
        this.shoppingItems = shoppingItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_shopping_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ShoppingItem item = shoppingItems.get(position);
        holder.textItem.setText(item.getItemName());

        final int itemPosition = position; // Declare a final local variable

        holder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteDialog(itemPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return shoppingItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textItem;
        public ImageView imageViewDelete;

        public ViewHolder(View view) {
            super(view);
            textItem = view.findViewById(R.id.textItem);
            imageViewDelete = view.findViewById(R.id.imageViewDelete);
        }
    }

    private void showDeleteDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Удалить элемент");

        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                shoppingItems.remove(position);
                notifyItemRemoved(position);
            }
        });

        builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.show();
    }
}
