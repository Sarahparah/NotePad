package com.example.notepad.Controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

    //medlar texten til listView
    static ArrayAdapter arrayAdapter;

    NoteModel notemodel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notes = findViewById(R.id.lv_list_notes);
        addsNotePlus = findViewById(R.id.iv_add_note);

        //Skapar ett object av klassen Notemodel
        notemodel = new NoteModel(this);


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

                //konverterar view till textView. Talar om för view att det är en textview

                TextView textView = (TextView)view;
                String title = textView.getText().toString();

                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra("titleKey", title);
                startActivity(intent);
            }
        });

        //Delete note when holding title on Main longer
        notes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                TextView title = (TextView)view;

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(getString(R.string.delete))

                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                MainActivity.this.notemodel.deleteNote(title.getText().toString());
                                updateListView();                 }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .show();

                return true;
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