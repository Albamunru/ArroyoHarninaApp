package com.example.albaunidadtres;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity3 extends AppCompatActivity {
 private VideoView miVideo;
 private MediaController mediaController;
 int posicion=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        miVideo=findViewById(R.id.videoViewVideo);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.instituto;
        miVideo.setVideoURI(Uri.parse(videoPath));
        miVideo.start();


        MediaController mediaController = new MediaController(this);

        mediaController.setAnchorView(miVideo);
        miVideo.setMediaController(mediaController);
    }
}