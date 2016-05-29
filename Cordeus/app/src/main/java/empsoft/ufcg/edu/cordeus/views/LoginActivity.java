package empsoft.ufcg.edu.cordeus.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import empsoft.ufcg.edu.cordeus.R;
import empsoft.ufcg.edu.cordeus.controllers.UserController;
import empsoft.ufcg.edu.cordeus.utils.MySharedPreferences;

public class LoginActivity extends AppCompatActivity {

    private EditText mUsername;
    private EditText mPassword;
    private TextInputLayout mLayoutUsername;
    private TextInputLayout mLayoutPassword;
    private String username;
    private String password;
    private Button mEnter;
    private Button mRegister;
    private UserController userController;
    private MySharedPreferences mySharedPreferences;
    public static View mLoadingLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoadingLogin = findViewById(R.id.loadingLogin);
        userController = new UserController(LoginActivity.this);
        mUsername = (EditText) findViewById(R.id.et_username);
        mPassword = (EditText) findViewById(R.id.et_password);
        mLayoutUsername = (TextInputLayout) findViewById(R.id.input_layout_name);
        mLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        mEnter = (Button) findViewById(R.id.btn_enter);
        mRegister = (Button) findViewById(R.id.btn_register);
        mySharedPreferences = new MySharedPreferences(getApplicationContext());

        mEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = mUsername.getText().toString();
                password = mPassword.getText().toString();

                if (validateUsername() && validatePassword()) {
                    userController.login(username, password, MainActivity.class);
                } else if (!validateUsername()){
                    return;
                } else if (!validatePassword()){
                    return;
                }
            }
        });

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setView(LoginActivity.this, RegisterActivity.class);
            }
        });
    }

    private boolean validateUsername(){
        if (username.trim().isEmpty()) {
            mLayoutUsername.setError(getString(R.string.err_msg_username));
            requestFocus(mUsername);
            return false;
        } else if (username.trim().length() < 5) {
            mLayoutUsername.setError(getString(R.string.err_short_username));
            requestFocus(mUsername);
            return false;
        } else {
            mLayoutUsername.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePassword(){
        if (password.trim().isEmpty()) {
            mLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(mPassword);
            return false;
        } else if (password.trim().length() < 6){
            mLayoutPassword.setError(getString(R.string.err_short_password));
            requestFocus(mPassword);
            return false;
        } else {
            mLayoutPassword.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public void setView(Context context, Class classe) {
        Intent it = new Intent();
        it.setClass(context, classe);
        startActivity(it);
    }

}
