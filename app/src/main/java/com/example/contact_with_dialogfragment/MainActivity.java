package com.example.contact_with_dialogfragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements ContactsAdapter.ItemEventListener, MyDialog.MyDialogEventListener {
    private ContactsAdapter adapter;
    private int editingItemPosition = -1;
    private ImageView showDialogBtn;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adapter = new ContactsAdapter(this);
        recyclerView.setAdapter(adapter);


        showDialogBtn = findViewById(R.id.iv_main_addNewContact);
        showDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialog myDialog = new MyDialog();
                myDialog.setCancelable(true);
                myDialog.show(getSupportFragmentManager(), null);

            }
        });


    }



    @Override
    public void onItemClick(String fullName, int position) {
        editingItemPosition = position;
//        fullNameET.setText((fullName));
        showDialogBtn.setImageResource(R.drawable.ic_done_white_24);
        MyDialog myDialog = new MyDialog();
        Bundle bundle = new Bundle();
        bundle.putString("fullName", fullName); // ارسال نام مخاطب به Dialog
        myDialog.setArguments(bundle);
        myDialog.show(getSupportFragmentManager(), null);

    }

    @Override
    public void onOkButtonClicked(String data) {
        if (data != null && !data.isEmpty()) {

            if (editingItemPosition > -1) {
                adapter.updateContact(data, editingItemPosition );
                editingItemPosition = -1;
                showDialogBtn.setImageResource(R.drawable.ic_add_white_24);

            } else {
//                Toast.makeText(this , fullNameET.getText().toString(), Toast.LENGTH_LONG).show();

                adapter.addNewContact(data);
                //         recyclerView.scrollToPosition(0); // سفا اسکرول میشه
                recyclerView.smoothScrollToPosition(0);// به نرمی اسمکرول میشه

            }



        }
    }

    @Override
    public void onCancelButtonClicked() {
        Toast.makeText(this, "Canceled the process", Toast.LENGTH_LONG).show();
    }
}