package com.example.notepad.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.notepad.Model.NoteModel;
import com.example.notepad.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView notes;
    ImageView addsNotePlus;


    static ArrayList<String> arrayList = new ArrayList<>();
    static ArrayAdapter arrayAdapter; //medlar texten til listView

    NoteModel notemodel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //hej.hejsan();

        notes = findViewById(R.id.lv_list_notes);
        addsNotePlus = findViewById(R.id.iv_add_note);

        notemodel = new NoteModel(this); //Skapar ett object av klassen Notemodel
        updateListView();


        //String s = getIntent().getStringExtra("display_text");
        //arrayList.add(s);


        addsNotePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addsAnotherNote = new Intent(MainActivity.this, EditActivity.class);
                addsAnotherNote.putExtra("titleKey", "");

                startActivity(addsAnotherNote);

            }
        });

        notes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView = (TextView)view;//konverterar view till textView. Talar om för view att det är en textview
                String title = textView.getText().toString();

                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra("titleKey", title);
                startActivity(intent);
            }
        });

    }

    protected void onResume(){
        super.onResume();
        updateListView();
    }

    public void updateListView(){

        arrayList = notemodel.loadData();

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        notes.setAdapter(arrayAdapter);



    }






}