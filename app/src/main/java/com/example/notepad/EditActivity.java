package com.example.notepad;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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
  String error = "Enter title and text";
  String saved = "Saved";
  String savedTitleName;
  String savedTextInput;

  SharedPreferences sharedprefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        returnButton = findViewById(R.id.iv_return);
        saveButton = findViewById(R.id.iv_save);
        textInput = findViewById(R.id.et_text);
        titleInput = findViewById(R.id.et_title);

        savedTitleName = titleInput.getText().toString();
        savedTextInput = textInput.getText().toString();

        sharedprefs = getSharedPreferences("com.example.notepad.prefrence", MODE_PRIVATE );


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
                saveData();

            }
        });

    }

    /**
     * Ville att användaren ej ska kunna spara förren en titel och text skriven.
     * det som sparas i min mapp kommer vara namnet som användaren skrivit in i ET title.
     * en toast visas om det saknas parametrar och nör det lyckas sparas
     *
     */
    public void saveData() {

        savedTitleName = titleInput.getText().toString();
        savedTextInput = textInput.getText().toString();

        if (TextUtils.isEmpty(savedTitleName) || TextUtils.isEmpty(savedTextInput)) {
            Toast.makeText(EditActivity.this, error, Toast.LENGTH_LONG).show();
        } else {

            File folder = new File(EditActivity.this.getFilesDir(), "NotePad");
            if (!folder.exists()) {
                folder.mkdir();

            }
            try {

                File fileName = new File(folder, savedTitleName);
                FileWriter writer = new FileWriter(fileName);
                writer.append(savedTextInput);
                writer.close();
            } catch (IOException e) {

            }
            toast();
        }
    }

    public void toast(){

        Toast.makeText(EditActivity.this,saved,Toast.LENGTH_LONG).show();

    }

   /** public void getInput(){

         File fileName = new File(savedTitleName);
         Scanner scanner = new Scanner(savedTitleName);

    }

    /**public void sharedPrefs(){

        sharedprefs = getSharedPreferences("com.example.notepad.prefrence", MODE_PRIVATE );

        SharedPreferences.Editor editPrefs = sharedprefs.edit();
        editPrefs.putString("saved", savedTitleName);
        editPrefs.apply();

    }
     **/

        }


