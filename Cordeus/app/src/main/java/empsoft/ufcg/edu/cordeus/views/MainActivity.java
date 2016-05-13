package empsoft.ufcg.edu.cordeus.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;

import empsoft.ufcg.edu.cordeus.R;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_PLAY_VIDEO = 101;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CardView cardView = (CardView) findViewById(R.id.card_view);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VideoActivity.class);
                startActivityForResult(intent, REQUEST_PLAY_VIDEO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PLAY_VIDEO && resultCode == RESULT_OK) {
            Log.d(TAG, "no onActivityResult, o resultado foi ok.");
        } else if (requestCode == REQUEST_PLAY_VIDEO && resultCode == RESULT_CANCELED) {
            Log.d(TAG, "no onActivityResult, o resultado indica que algo deu errado na reprodução");
        }
    }
}
