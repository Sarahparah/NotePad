package com.example.notepad.Model;

import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.notepad.Controller.EditActivity;
import com.example.notepad.R;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class NoteModel {

    AppCompatActivity activity;
    File folder;

    //skapar en constructor
    public NoteModel(AppCompatActivity activity){
        this.activity = activity;

        try {
            folder = new File(activity.getFilesDir(), "NotePad");
            if (!folder.exists()) {
                folder.mkdir();

            }

        }catch (Exception e){ }
    }

    //Här sker allt som har med sparning stt göra, tar string ifrån edit till classen notemodel

    public void saveData(String savedTitleName, String savedTextInput) {

        if (TextUtils.isEmpty(savedTitleName) || TextUtils.isEmpty(savedTextInput)) {
            Toast.makeText(activity, R.string.toast_error, Toast.LENGTH_LONG).show();
        } else {

            try {
                File fileName = new File(folder, savedTitleName);
                FileWriter writer = new FileWriter(fileName);
                writer.append(savedTextInput);
                writer.close();
            } catch (IOException e) { }
            Toast.makeText(activity, R.string.toast_save, Toast.LENGTH_LONG).show();

        }

    }
    //här tar vi array listan och connectar den med vår folder vi skapade i saveData metoden.

    public ArrayList <String> loadData(){
        
        ArrayList <String> noteTitles = new ArrayList<>();
        
        try {

            File [] files = folder.listFiles();
            for (File currentFile: files) {

               //Date date = new Date(currentFile.lastModified());
                //String creationTime = date.toString();
                 String title = currentFile.getName();
                //title += " - " + creationTime;
                noteTitles.add(title);
            }
            //for (int i = 0; i<files.length; i++){
           // }

        }catch(Exception e){
            
        }
        return noteTitles;
        
    }

    public String loadNoteText(String title){
        String noteText = "";

        try{
            File file = new File(folder, title );

            Scanner scanner = new Scanner(file);
            //hasNext retunerar en true eller false.
            while (scanner.hasNext()){
                noteText += scanner.nextLine() ; //"\n"
            }
        }catch(Exception e){

        }
        return noteText;

    }

    public void deleteNote(String title){
        File file = new File(folder, title);
        if( file.exists()){
            file.delete();


        }


    }

    }







