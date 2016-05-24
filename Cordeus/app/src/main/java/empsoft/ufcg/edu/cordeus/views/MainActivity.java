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
    private List<String> refer_newCordels;
    private UserController userController;
    private HashMap<String, String> userDetails;
    private MySharedPreferences mySharedPreferences;
    private RecyclerView myReflections;
    private RecyclerView newReflections;
    private String login;
    private List<Cordel> myCordels;
    private List<Cordel> newCordels;
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

        myReflections = (RecyclerView) findViewById(R.id.my_reflections);
        myReflections.setLayoutManager(llm);
        newReflections = (RecyclerView) findViewById(R.id.new_reflections);
        newReflections.setLayoutManager(llm2);

        mySharedPreferences = new MySharedPreferences(getApplicationContext());
        userController = new UserController(MainActivity.this);
        userDetails = mySharedPreferences.getUserDetails();
        login = userDetails.get(MySharedPreferences.KEY_USERNAME);

        refer_myCordels = new ArrayList<>();
        refer_myCordels = mySharedPreferences.getMyCordels();

        updateMyReflections();
        updateNewReflections();

        account_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySharedPreferences.logoutUser();
                finish();
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

    private void updateMyReflections() {
        myReflections.setAdapter(new ReflectionAdapter(getMyCordeis(), new OnItemClickListener() {
            @Override
            public void onItemClick(Cordel cordel) {
                Intent intent = new Intent(MainActivity.this, VideoActivity.class);
                intent.putExtra("CORDEL", cordel);
                startActivityForResult(intent, REQUEST_PLAY_VIDEO);
            }
        }));
    }

    private void updateNewReflections() {
        newReflections.setAdapter(new ReflectionAdapter(getNewCordeis(), new OnItemClickListener() {
            @Override
            public void onItemClick(Cordel cordel) {
                Intent it = new Intent(MainActivity.this, NewCordelActivity.class);
                it.putExtra("NEWCORDEL", cordel);
                startActivity(it);
            }
        }));
    }

    public List<Cordel> getMyCordeis() {
        myCordels = new ArrayList<>();
        for (String refer : refer_myCordels) {
            Cordel cordel = new Cordel(refer, refer, getImage(refer));
            myCordels.add(cordel);
        }
        return myCordels;
    }

    private int getImage(String refer) {
        switch (refer) {
            case "Lamentações 3:22-23":
                return R.mipmap.lamentacoes_3_22_23;
            case "Filipenses 3:13-14":
                return R.mipmap.filipenses_3_13_14;
            default:
                return R.mipmap.cordel_blocked;
        }
    }

    public List<Cordel> getNewCordeis() {
        newCordels = new ArrayList<>();
        refer_newCordels = getRefNewCordels();
        for (String ref : refer_newCordels) {
            if (isNewToUser(ref)) {
                Cordel cordel = new Cordel(ref, ref, getImage(ref));
                newCordels.add(cordel);
            }
        }
        return newCordels;
    }

    private boolean isNewToUser(String refer) {
        return !refer_myCordels.contains(refer);
    }

    public List<String> getRefNewCordels() {
        List<String> refNewCordels = new ArrayList<>();
        refNewCordels.add("Filipenses 3:13-14");
        refNewCordels.add("São João");
        refNewCordels.add("São Pedro");
        refNewCordels.add("Santo Antônio");
        return refNewCordels;
    }
}
