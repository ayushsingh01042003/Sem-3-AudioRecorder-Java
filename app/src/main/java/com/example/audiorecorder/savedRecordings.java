package com.example.audiorecorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class savedRecordings extends AppCompatActivity implements ListAdapter.OnItemListClick {
    private RecyclerView listRecordings;
    private File[] files;
    private ListAdapter listAdapter;
    private MediaPlayer player = null;
    private boolean isPlaying = false;
    private File fileToPlay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_recordings);
        listRecordings = findViewById(R.id.listRecordings);

        String path = this.getExternalFilesDir("/").getAbsolutePath();
        File directory = new File(path);
        files = directory.listFiles();
        listAdapter = new ListAdapter(files, this);
        listRecordings.setHasFixedSize(true);
        listRecordings.setLayoutManager(new LinearLayoutManager(this));
        listRecordings.setAdapter(listAdapter);
    }

    @Override
    public void onClickListener(File file, int position) {

        Log.d("PLAY LOG","File Playing" +file.getName());

        if(isPlaying) {
            stopAudio();
            playAudio(fileToPlay);
        } else {
            fileToPlay = file;
            playAudio(fileToPlay);
        }
    }


    private void playAudio(File fileToPlay) {
        //play the audio
        player = new MediaPlayer();

        try {
            player.setDataSource(fileToPlay.getAbsolutePath());
            player.prepare();
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        isPlaying = true;
    }

    private void stopAudio() {
        //Stop the audio
        isPlaying = false;
        player.stop();
    }
    @Override
    public void onStop() {
        super.onStop();
        stopAudio();
    }
}