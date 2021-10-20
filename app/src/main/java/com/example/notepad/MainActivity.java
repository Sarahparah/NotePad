package com.example.notepad;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView notes;
    ImageView addsNotePlus;


    static ArrayList<String> arrayList = new ArrayList<>();
    static ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notes = findViewById(R.id.lv_list_notes);
        addsNotePlus = findViewById(R.id.iv_add_note);

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        notes.setAdapter(arrayAdapter);





        //String s = getIntent().getStringExtra("display_text");
        //arrayList.add(s);





        addsNotePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addsAnotherNote = new Intent(MainActivity.this, EditActivity.class);
                startActivity(addsAnotherNote);

            }
        });

    }

    public void AddNote() {

        arrayList.add("New note");

    }

}