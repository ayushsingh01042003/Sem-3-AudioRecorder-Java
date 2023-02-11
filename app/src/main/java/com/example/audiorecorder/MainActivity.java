package com.example.audiorecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageButton micButton;
    private Button stopButton;
    private Button listRecordingsButton;
    private boolean isRecording = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        micButton = findViewById(R.id.micButton);
        stopButton = findViewById(R.id.stopButton);
        listRecordingsButton = findViewById(R.id.listRecordingsButton);
        micButton.setOnClickListener(new View.OnClickListener() {//to display start and stop recording messages
            @Override
            public void onClick(View view) {
                if(!isRecording) {
                    Toast.makeText(MainActivity.this, "Started Recording", Toast.LENGTH_SHORT).show();
                    isRecording = true;
                }else {
                    Toast.makeText(MainActivity.this, "Stopped Recording", Toast.LENGTH_SHORT).show();
                    isRecording = false;
                }
            }
                
        });


    }

    public void openSavedRecordings(View v) {
        Toast.makeText(this, "works", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, savedRecordings.class);
        startActivity(intent);
    }
}