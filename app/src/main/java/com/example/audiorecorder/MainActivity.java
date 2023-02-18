package com.example.audiorecorder;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageButton micButton;
    private Button saveButton, listRecordingsButton;
    private MediaRecorder recorder;
    private String fileName;
    private boolean isRecording = false;
    public static final int REQUEST_AUDIO_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        micButton = findViewById(R.id.micButton);
        saveButton = findViewById(R.id.saveButton);
        listRecordingsButton = findViewById(R.id.listRecordingsButton);
        micButton.setOnClickListener(new View.OnClickListener() {//to display start and stop recording messages
            @Override
            public void onClick(View view) {
                if(!isRecording) {
                    Toast.makeText(MainActivity.this, "Started Recording", Toast.LENGTH_SHORT).show();
                    isRecording = true;
                    //code for recording

                }else {
                    Toast.makeText(MainActivity.this, "Paused Recording", Toast.LENGTH_SHORT).show();
                    isRecording = false;
                    //code for pause at the last save point so we can continue from there
                }
            }
        });


    }

    private void startRecording() {
        if(CheckPermissions()) {

            recorder = new MediaRecorder();

        }else {
            RequestPermissions();
        }


    }

    private void pauseRecording() {

    }

    private void saveRecording () {

    }
     @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
         // this method is called when user will
         // grant the permission for audio recording.
         super.onRequestPermissionsResult(requestCode, permissions, grantResults);
         switch (requestCode) {
             case REQUEST_AUDIO_PERMISSION_CODE:
                 if (grantResults.length > 0) {
                     boolean permissionToRecord = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                     boolean permissionToStore = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                     if (permissionToRecord && permissionToStore) {
                         Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_LONG).show();
                     } else {
                         Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_LONG).show();
                     }
                 }
                 break;
         }
     }

    public boolean CheckPermissions() {
        // this method is used to check permission
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }

    private void RequestPermissions() {
        // this method is used to request the
        // permission for audio recording and storage.
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{RECORD_AUDIO, WRITE_EXTERNAL_STORAGE}, REQUEST_AUDIO_PERMISSION_CODE);
    }



    public void openSavedRecordings(View v) {
        Toast.makeText(this, "works", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, savedRecordings.class);
        startActivity(intent);
    }
}