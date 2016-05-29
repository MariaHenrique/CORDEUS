package empsoft.ufcg.edu.cordeus.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import empsoft.ufcg.edu.cordeus.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }
}
