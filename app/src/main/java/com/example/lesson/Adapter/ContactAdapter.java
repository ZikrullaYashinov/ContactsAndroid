package com.example.lesson.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lesson.OnItemClickListener;
import com.example.lesson.R;
import com.example.lesson.databinding.ItemContactBinding;
import com.example.lesson.models.Contact;

import java.util.List;

public class ContactAdapter extends ArrayAdapter {
    private List<Contact> contactList;
    private OnItemClickListener listener;
    private Context context;

    public ContactAdapter(Context context, List<Contact> contactList, OnItemClickListener listener) {
        super(context, R.layout.item_contact);
        this.contactList = contactList;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ItemContactBinding binding;
        if (convertView == null) {
            binding = ItemContactBinding.inflate(LayoutInflater.from(context), parent, false);
        } else {
            binding = ItemContactBinding.bind(convertView);
        }
        Contact contact = contactList.get(position);
        binding.name.setText(contact.getName());
        binding.number.setText(contact.getNumber());

        binding.delete.setOnClickListener(view -> {
            listener.onItemDelete(contact, position);
        });
        binding.edit.setOnClickListener(view -> {
            listener.onItemEdit(contact, position);
        });

        return binding.getRoot();
    }
}
