package com.example.audiorecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

public class savedRecordings extends AppCompatActivity {
    ListView listRecordings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_recordings);
        listRecordings = findViewById(R.id.listRecordings);
    }

}