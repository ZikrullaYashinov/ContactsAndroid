package com.example.lesson;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.lesson.Adapter.ContactAdapter;
import com.example.lesson.databinding.ActivityMainBinding;
import com.example.lesson.models.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ContactAdapter arrayAdapter;
    List<Contact> contactList;
    private ActivityMainBinding binding;
    private int lastSelectContactPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadData();

        arrayAdapter = new ContactAdapter(this, contactList, new OnItemClickListener() {
            @Override
            public void onItemDelete(Contact contact, int position) {
                contactList.remove(position);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onItemEdit(Contact contact, int position) {
                binding.editname.setText(contact.getName());
                binding.editnumber.setText(contact.getNumber());
                lastSelectContactPosition = position;
            }
        });
        binding.listItem.setAdapter(arrayAdapter);

        binding.listItem.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent=new Intent(this, SelectActivity.class);
            intent.putExtra("contact", contactList.get(i));
            startActivity(intent);
        });
    }

    private void loadData() {
        contactList = new ArrayList<>();
    }

    public void saveClick(View view) {
        String name = binding.editname.getText().toString();
        String number = binding.editnumber.getText().toString();

        if (lastSelectContactPosition == -1){
            contactList.add(new Contact(name, number));

        } else {
            contactList.get(lastSelectContactPosition).setName(name);
            contactList.get(lastSelectContactPosition).setNumber(number);
            lastSelectContactPosition = -1;
        }

        arrayAdapter.notifyDataSetChanged();

        binding.editname.setText("");
        binding.editnumber.setText("");
    }
}