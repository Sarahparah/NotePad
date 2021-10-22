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

        String toastError = getString(R.string.toast_error);
        String toastSaved = getString(R.string.toast_save);
        notemodel = new NoteModel(this); //Skapa nytt objekt av notemodel. this(här) är referensen till EDtiACtivity

        String title = getIntent().getStringExtra("titleKey");
        titleInput.setText(title);

        String noteText = notemodel.loadNoteText(title);      //retunerar  texten som en sträng
        textInput.setText(noteText);



        //savedTitleName = titleInput.getText().toString();
       // savedTextInput = textInput.getText().toString();

        //return button, aktiviteten stängs när image view trycks på

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                savedTitleName = titleInput.getText().toString();
                savedTextInput = textInput.getText().toString();

                notemodel.saveData(savedTitleName,savedTextInput,toastError,toastSaved);

            }
        });

    }

    /**
     * Ville att användaren ej ska kunna spara förren en titel och text skriven.
     * det som sparas i min mapp kommer vara namnet som användaren skrivit in i ET title.
     * en toast visas om det saknas parametrar och nör det lyckas sparas
     *
     */

        }


