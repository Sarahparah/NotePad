package com.example.notepad.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.notepad.Model.NoteModel;
import com.example.notepad.R;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EditActivity extends AppCompatActivity {

  ImageView returnButton;
  ImageView saveButton;
  EditText textInput;
  EditText titleInput;
  String newTitle;

  String savedTitleName;
  String savedTextInput;


  NoteModel notemodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        returnButton = findViewById(R.id.iv_return);
        saveButton = findViewById(R.id.iv_save);
        textInput = findViewById(R.id.et_text);
        titleInput = findViewById(R.id.et_title);

        //Skapa nytt objekt av notemodel. this(här) är referensen till Edit Activity
        notemodel = new NoteModel(this);

        String title = getIntent().getStringExtra("titleKey");
        titleInput.setText(title);

        //retunerar  texten som en sträng
        String noteText = notemodel.loadNoteText(title);
        textInput.setText(noteText);


        //return button, aktiviteten stängs när image view trycks på
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            finish();
            }
        });

        //när man trycker på sparaknappen så omvandlas objektet i EditText till en string först.
        //Sen kallas saveData ifrån notemodel.
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                savedTitleName = titleInput.getText().toString();
                savedTextInput = textInput.getText().toString();

                notemodel.saveData(savedTitleName,savedTextInput);

            }
        });

    }

        }


