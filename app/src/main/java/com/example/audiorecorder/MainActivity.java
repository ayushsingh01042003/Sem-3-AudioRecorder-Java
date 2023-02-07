package com.example.audiorecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageButton micButton;
    private boolean isRecording = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        micButton = findViewById(R.id.micButton);
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
}