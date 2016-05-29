package empsoft.ufcg.edu.cordeus.views;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import empsoft.ufcg.edu.cordeus.R;
import empsoft.ufcg.edu.cordeus.models.Cordel;

public class VideoActivity extends Activity implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        Intent it = getIntent();
        Cordel cordel = (Cordel) it.getSerializableExtra("CORDEL");

        Uri video = null;
        final VideoView videoView = (VideoView) findViewById(R.id.videoView);
        if (cordel.getTitle().equals("Deus é amor")){
            video = Uri.parse("android.resource://" + getPackageName() + "/" +
                    R.raw.lamentacoes3_22_23);
        }
        if (cordel.getTitle().equals("Confie no Senhor")){
            video = Uri.parse("android.resource://" + getPackageName() + "/" +
                    R.raw.filipenses_3_13_14);
        }


        videoView.setVideoURI(video);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setKeepScreenOn(true);
        videoView.setOnErrorListener(this);
        videoView.setOnCompletionListener(this);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
            }
        });
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.d(TAG, "Terminou de reproduzir o vídeo");
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Log.d(TAG, "Ocorreu um erro durante a reprodução. Cancelando e voltando para a tela principal");
        setResult(RESULT_CANCELED);
        finish();
        return true;
    }
}
