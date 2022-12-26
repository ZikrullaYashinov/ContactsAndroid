package com.example.lesson;

import com.example.lesson.models.Contact;

public interface OnItemClickListener {
    void onItemDelete(Contact contact, int position);
    void onItemEdit(Contact contact, int position);
}
