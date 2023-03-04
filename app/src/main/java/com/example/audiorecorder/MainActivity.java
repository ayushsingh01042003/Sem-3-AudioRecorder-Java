package com.example.audiorecorder;

import android.Manifest;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private int PERMISSION_CODE = 1;
    private ImageButton micButton;
    private Button saveButton, listRecordingsButton;
    private MediaRecorder recorder;
    private boolean isRecording = false;
    private String recordPermission = Manifest.permission.RECORD_AUDIO;
    private String fileName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        micButton = findViewById(R.id.micButton);
        //saveButton = findViewById(R.id.saveButton);
        listRecordingsButton = findViewById(R.id.listRecordingsButton);

        micButton.setOnClickListener(new View.OnClickListener() {//to display start and stop recording messages
            @Override
            public void onClick(View view) {
                if(!isRecording) {
                    if(checkPermissions()) {
                        startRecording();
                        Toast.makeText(MainActivity.this, "Started Recording", Toast.LENGTH_SHORT).show();
                        isRecording = true;
                    }

                }else {
                    saveRecording();
                    Toast.makeText(MainActivity.this, "Saved Recording", Toast.LENGTH_SHORT).show();
                    isRecording = false;
                }
            }
        });

//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //stop the recording and save it
//                saveRecording();
//            }
//        });


    }

    private void startRecording() {
        String filePath = this.getExternalFilesDir("/").getAbsolutePath();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss", Locale.CANADA);
        Date date = new Date();
        fileName = "Recording_"+ formatter.format(date) + ".3gp";

        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setOutputFile(filePath+ "/" +fileName);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            recorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        recorder.start();
    }


//    private void pauseRecording() {
//        recorder.pause();
//    }

    private void saveRecording () {//hitting the save button will generate the audio file
        recorder.stop();
        recorder.release();
        recorder = null;
    }

    public boolean checkPermissions() {
        // this method is used to check permission
        if(ActivityCompat.checkSelfPermission(this, recordPermission) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }else {
            ActivityCompat.requestPermissions(this, new String[] {recordPermission}, PERMISSION_CODE );
            return false;
        }
    }

//    public void onStop() {
//        super.onStop();
//        if(isRecording) {
//            saveRecording();
//        }
//    }


    public void openSavedRecordings(View v) {

        //Toast.makeText(MainActivity.this, "Saved Recording", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, savedRecordings.class);
        startActivity(intent);
    }
}