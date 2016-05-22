package empsoft.ufcg.edu.cordeus.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import empsoft.ufcg.edu.cordeus.R;
import empsoft.ufcg.edu.cordeus.controllers.UserController;
import empsoft.ufcg.edu.cordeus.models.Cordel;
import empsoft.ufcg.edu.cordeus.utils.MySharedPreferences;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_PLAY_VIDEO = 101;
    private static final String TAG = "MainActivity";
    private List<String> refer_myCordels;
    private UserController userController;
    private HashMap<String, String> userDetails;
    private MySharedPreferences mySharedPreferences;
    private String login;
    private  List<Cordel> myCordels;
    private  List<Cordel> newCordels;
    private ImageButton account_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);

        LinearLayoutManager llm2 = new LinearLayoutManager(this);
        llm2.setOrientation(LinearLayoutManager.HORIZONTAL);

        account_user = (ImageButton) findViewById(R.id.ib_account);

        RecyclerView myReflections = (RecyclerView) findViewById(R.id.my_reflections);
        myReflections.setLayoutManager(llm);
        RecyclerView newReflections = (RecyclerView) findViewById(R.id.new_reflections);
        newReflections.setLayoutManager(llm2);

        mySharedPreferences = new MySharedPreferences(getApplicationContext());
        userController = new UserController(MainActivity.this);
        userDetails = mySharedPreferences.getUserDetails();
        login = userDetails.get(MySharedPreferences.KEY_USERNAME);

        refer_myCordels = new ArrayList<>();
        refer_myCordels = mySharedPreferences.getMyCordels();

            myReflections.setAdapter(new ReflectionAdapter(getMyCordeis(), new OnItemClickListener() {
                @Override
                public void onItemClick(Cordel cordel) {
                    Intent intent = new Intent(MainActivity.this, VideoActivity.class);
                    intent.putExtra("CORDEL", cordel);
                    startActivityForResult(intent, REQUEST_PLAY_VIDEO);
                }
            }));

        newReflections.setAdapter(new ReflectionAdapter(getNewCordeis(), new OnItemClickListener() {
            @Override
            public void onItemClick(Cordel cordel) {
                Intent it = new Intent(MainActivity.this, NewCordelActivity.class);
                it.putExtra("NEWCORDEL", cordel);
                startActivity(it);
                }
        }));

        account_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySharedPreferences.logoutUser();
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

    public List<Cordel> getMyCordeis() {
        int color = getResources().getColor(R.color.colorAccent);
        myCordels = new ArrayList<>();
        for (String refer : refer_myCordels){
            Cordel cordel = new Cordel(refer, refer, color);
            myCordels.add(cordel);
        }
        return myCordels;
    }

    public List<Cordel> getNewCordeis() {
        newCordels = new ArrayList<>();
        int colorNew = getResources().getColor(R.color.colorPrimaryDark);
        Cordel cordel = new Cordel("Filipenses 3:13-14", "Filipenses 3:13-14", colorNew);
        newCordels.add(cordel);
        return newCordels;
    }
}
