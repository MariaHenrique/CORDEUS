package empsoft.ufcg.edu.cordeus.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import empsoft.ufcg.edu.cordeus.R;
import empsoft.ufcg.edu.cordeus.controllers.UserController;

public class LoginActivity extends AppCompatActivity {

    private EditText mUsername;
    private EditText mPassword;
    private Button mEnter;
    private Button mRegister;
    private UserController userController;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userController = new UserController(LoginActivity.this);
        mUsername = (EditText) findViewById(R.id.et_username);
        mPassword = (EditText) findViewById(R.id.et_password);
        mEnter = (Button) findViewById(R.id.btn_enter);
        mRegister = (Button) findViewById(R.id.btn_register);

        mEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = mUsername.getText().toString();
                String password = mPassword.getText().toString();
                userController.login(username, password);
            }
        });

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setView(LoginActivity.this, RegisterActivity.class);
            }
        });
    }

    public void setView(Context context, Class classe) {
        Intent it = new Intent();
        it.setClass(context, classe);
        startActivity(it);
    }

}
