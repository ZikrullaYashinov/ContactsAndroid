package com.example.lesson;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.lesson.databinding.ActivityMainBinding;
import com.example.lesson.databinding.ActivitySelectBinding;
import com.example.lesson.models.Contact;

public class SelectActivity extends AppCompatActivity {

    private ActivitySelectBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        Contact contact = (Contact) intent.getSerializableExtra("contact");

        binding.name.setText(contact.getName());
        binding.number.setText(contact.getNumber());


    }
}