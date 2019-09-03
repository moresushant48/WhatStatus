package io.moresushant48.whatstatus;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.support.v7.widget.Toolbar;
import android.widget.VideoView;

public class ViewStatus extends AppCompatActivity {

    ImageView selectedImage;
    VideoView selectedVideo;
    ImageView playSign;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_status);

        Toolbar toolbar = findViewById(R.id.statusToolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.getNavigationIcon().setTint(Color.parseColor("#FFFFFF"));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        intent = getIntent(); // get Intent which we set from Previous Activity

        selectedImage = findViewById(R.id.selectedImage); // init a ImageView
        selectedVideo = findViewById(R.id.selectedVideo);
        playSign = findViewById(R.id.playSign);

        selectedVideo.canSeekBackward();
        selectedVideo.canSeekForward();

        if(intent.getStringExtra("image").endsWith(".jpg")){
            selectedImage.setVisibility(View.VISIBLE);
            selectedImage.setImageURI(Uri.parse(intent.getStringExtra("image")));

        }else if(intent.getStringExtra("image").endsWith(".mp4")){
            selectedVideo.setVisibility(View.VISIBLE);
            selectedVideo.setVideoURI(Uri.parse(intent.getStringExtra("image")));
            selectedVideo.start();

            MediaController mediaController = new MediaController(this);
            selectedVideo.setMediaController(mediaController);
            mediaController.setAnchorView(selectedVideo);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabStatus);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND)
                        .putExtra(Intent.EXTRA_STREAM, Uri.parse(intent.getStringExtra("image")));
                if(intent.getStringExtra("image").endsWith(".jpg"))
                    shareIntent.setType("image/*");
                else if(intent.getStringExtra("image").endsWith(".mp4"))
                    shareIntent.setType("video/*");
                startActivity(Intent.createChooser(shareIntent, "Share using"));
            }
        });
    }
}
