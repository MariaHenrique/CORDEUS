package empsoft.ufcg.edu.cordeus.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import empsoft.ufcg.edu.cordeus.R;
import empsoft.ufcg.edu.cordeus.models.Cordel;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_PLAY_VIDEO = 101;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);

        LinearLayoutManager llm2 = new LinearLayoutManager(this);
        llm2.setOrientation(LinearLayoutManager.HORIZONTAL);

        RecyclerView myReflections = (RecyclerView) findViewById(R.id.my_reflections);
        myReflections.setLayoutManager(llm);
        RecyclerView newReflections = (RecyclerView) findViewById(R.id.new_reflections);
        newReflections.setLayoutManager(llm2);

        myReflections.setAdapter(new ReflectionAdapter(getMyCordeis(), new OnItemClickListener() {
            @Override
            public void onItemClick(Cordel cordel) {
                Intent intent = new Intent(MainActivity.this, VideoActivity.class);
                startActivityForResult(intent, REQUEST_PLAY_VIDEO);
            }
        }));

        newReflections.setAdapter(new ReflectionAdapter(getNewCordeis(), new OnItemClickListener() {
            @Override
            public void onItemClick(Cordel cordel) {
                setView(MainActivity.this, NewCordelActivity.class);
            }
        }));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PLAY_VIDEO && resultCode == RESULT_OK) {
            Log.d(TAG, "no onActivityResult, o resultado foi ok.");
        } else if (requestCode == REQUEST_PLAY_VIDEO && resultCode == RESULT_CANCELED) {
            Log.d(TAG, "no onActivityResult, o resultado indica que algo deu errado na reprodução");
        }
    }

    public List<Cordel> getMyCordeis() {
        int color = getResources().getColor(R.color.colorAccent);
        Cordel cordel1 = new Cordel("Cordel 01", color);
        Cordel cordel2 = new Cordel("Cordel 02", color);
        Cordel cordel3 = new Cordel("Cordel 03", color);
        Cordel cordel4 = new Cordel("Cordel 04", color);
        Cordel cordel5 = new Cordel("Cordel 05", color);
        Cordel cordel6 = new Cordel("Cordel 06", color);
        Cordel cordel7 = new Cordel("Cordel 07", color);
        Cordel cordel8 = new Cordel("Cordel 08", color);
        Cordel cordel9 = new Cordel("Cordel 09", color);


        List<Cordel> reflectionInfos = new ArrayList<>();
        reflectionInfos.add(cordel1);
        reflectionInfos.add(cordel2);
        reflectionInfos.add(cordel3);
        reflectionInfos.add(cordel4);
        reflectionInfos.add(cordel5);
        reflectionInfos.add(cordel6);
        reflectionInfos.add(cordel7);
        reflectionInfos.add(cordel8);
        reflectionInfos.add(cordel9);
        return reflectionInfos;
    }

    public List<Cordel> getNewCordeis() {
        int colorNew = getResources().getColor(R.color.colorPrimaryDark);
        Cordel cordel1 = new Cordel("Cordel 01", colorNew);
        Cordel cordel2 = new Cordel("Cordel 02", colorNew);
        Cordel cordel3 = new Cordel("Cordel 03", colorNew);
        Cordel cordel4 = new Cordel("Cordel 04", colorNew);
        Cordel cordel5 = new Cordel("Cordel 05", colorNew);
        Cordel cordel6 = new Cordel("Cordel 06", colorNew);
        Cordel cordel7 = new Cordel("Cordel 07", colorNew);
        Cordel cordel8 = new Cordel("Cordel 08", colorNew);
        Cordel cordel9 = new Cordel("Cordel 09", colorNew);


        List<Cordel> reflectionInfos = new ArrayList<>();
        reflectionInfos.add(cordel1);
        reflectionInfos.add(cordel2);
        reflectionInfos.add(cordel3);
        reflectionInfos.add(cordel4);
        reflectionInfos.add(cordel5);
        reflectionInfos.add(cordel6);
        reflectionInfos.add(cordel7);
        reflectionInfos.add(cordel8);
        reflectionInfos.add(cordel9);
        return reflectionInfos;
    }


    public void setView(Context context, Class classe) {
        Intent it = new Intent();
        it.setClass(context, classe);
        startActivity(it);
    }
}
