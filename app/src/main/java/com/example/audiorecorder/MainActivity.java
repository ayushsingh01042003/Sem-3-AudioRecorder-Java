package com.example.audiorecorder;

import static android.app.PendingIntent.getActivity;
import static java.security.AccessController.getContext;
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


public class MainActivity extends AppCompatActivity {
    private int PERMISSION_CODE = 1;
    private ImageButton micButton;
    private Button saveButton, listRecordingsButton;
    private MediaRecorder recorder;
    private static final String mFileName = null;
    private boolean isRecording = false;
    private String recordPermission = Manifest.permission.RECORD_AUDIO;


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
                    if(checkPermissions()) {
                        Toast.makeText(MainActivity.this, "Started Recording", Toast.LENGTH_SHORT).show();
                        isRecording = true;
                        //code for recording
                    }

                }else {
                    Toast.makeText(MainActivity.this, "Paused Recording", Toast.LENGTH_SHORT).show();
                    isRecording = false;
                    //code for pause at the last save point so we can continue from there
                }
            }
        });


    }


    private void pauseRecording() {

    }

    private void saveRecording () {//hitting the save button will generate the audio file

    }

    public boolean checkPermissions() {
        // this method is used to check permission
        if(ActivityCompat.checkSelfPermission(getContext(), recordPermission) = PackageManager.PERMISSION_GRANTED) {
            return true;
        }else {
            ActivityCompat.requestPermissions(getActivity(), new String[] {recordPermission}, PERMISSION_CODE );
            return false;
        }
    }


    public void openSavedRecordings(View v) {
        Toast.makeText(this, "works", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, savedRecordings.class);
        startActivity(intent);
    }
}