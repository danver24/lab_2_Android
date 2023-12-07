package com.example.shoprecycler;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ShoppingItem> shoppingItems;
    private ShoppingListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        shoppingItems = new ArrayList<>();
        adapter = new ShoppingListAdapter(shoppingItems, this);
        recyclerView.setAdapter(adapter);

        Button btnAddItem = findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editTextItem = findViewById(R.id.editTextItem);
                String itemName = editTextItem.getText().toString();

                if (!itemName.isEmpty()) {
                    ShoppingItem newItem = new ShoppingItem(itemName);
                    shoppingItems.add(newItem);
                    adapter.notifyItemInserted(shoppingItems.size() - 1);
                    editTextItem.getText().clear();
                }
            }
        });
    }
}
