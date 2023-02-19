package com.example.audiorecorder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.AudioViewHolder> {
    private File[] files;

    public ListAdapter(File[] files) {
        this.files = files;
    }

    @NonNull
    @Override
    public AudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_item, parent, false);
        return new AudioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AudioViewHolder holder, int position) {
        holder.ListTitle.setText(files[position].getName());
        holder.ListDate.setText(files[position].lastModified() + "");
    }

    @Override
    public int getItemCount() {
        return files.length;
    }

    public class AudioViewHolder extends RecyclerView.ViewHolder {

        private ImageView ListIcon;
        private TextView ListTitle;
        private TextView ListDate;

        public AudioViewHolder(@NonNull View itemView) {
            super(itemView);

            ListIcon = itemView.findViewById(R.id.ListIcon);
            ListTitle = itemView.findViewById(R.id.ListTitle);
            ListDate = itemView.findViewById(R.id.ListDate);
        }
    }
}
