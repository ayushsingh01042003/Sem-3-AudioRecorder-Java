package com.example.audiorecorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import java.io.File;
import java.util.List;

public class savedRecordings extends AppCompatActivity {
    private RecyclerView listRecordings;
    private File[] files;
    private ListAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_recordings);
        listRecordings = findViewById(R.id.listRecordings);

        String path = this.getExternalFilesDir("/").getAbsolutePath();
        File directory = new File(path);
        files = directory.listFiles();
        listAdapter = new ListAdapter(files);
        listRecordings.setHasFixedSize(true);
        listRecordings.setLayoutManager(new LinearLayoutManager(this));
        listRecordings.setAdapter(listAdapter);
    }

}