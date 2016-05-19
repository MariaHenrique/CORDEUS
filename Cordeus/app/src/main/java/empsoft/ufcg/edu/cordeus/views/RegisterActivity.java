package empsoft.ufcg.edu.cordeus.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.support.design.widget.TextInputLayout;

import empsoft.ufcg.edu.cordeus.R;
import empsoft.ufcg.edu.cordeus.controllers.UserController;

public class RegisterActivity extends AppCompatActivity {

    private Button mRegister;
    private EditText mUsername;
    private EditText mPassword;
    private EditText mPasswordConfirm;
    private TextInputLayout mLayoutUsername;
    private TextInputLayout mLayoutPassword;
    private TextInputLayout mLayoutPasswordConfirm;
    private String username;
    private String password;
    private String passwordConfirm;

    private UserController usercontroller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mRegister = (Button) findViewById(R.id.btn_cadastre);
        mUsername = (EditText) findViewById(R.id.et_username);
        mPassword = (EditText) findViewById(R.id.et_password);
        mPasswordConfirm = (EditText) findViewById(R.id.et_password_confirm);
        mLayoutUsername = (TextInputLayout) findViewById(R.id.input_layout_name);
        mLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        mLayoutPasswordConfirm = (TextInputLayout) findViewById(R.id.input_layout_password_confirm);

        usercontroller = new UserController(RegisterActivity.this);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = mUsername.getText().toString();
                password = mPassword.getText().toString();
                passwordConfirm = mPasswordConfirm.getText().toString();

                if (validateUsername() && validatePassword() && validatePasswordConfirm() && confirmationPassword()) {
                    usercontroller.registerUser(username, password);
                } else if (!validateUsername()){
                    return;
                } else if (!validatePassword()){
                    return;
                } else if (!validatePasswordConfirm()){
                    return;
                } else if (!confirmationPassword()){
                    new AlertDialog.Builder(RegisterActivity.this)
                            .setMessage("As senhas não coincidem!")
                            .setNeutralButton("OK", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface arg0, int arg1) {
                                    mPassword.setText("");
                                    mPasswordConfirm.setText("");
                                    requestFocus(mPassword);
                                }
                            })
                            .create()
                            .show();
                }
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

    private boolean validatePasswordConfirm(){
        if (passwordConfirm.trim().isEmpty()) {
            mLayoutPasswordConfirm.setError(getString(R.string.err_msg_password_confirm));
            requestFocus(mPasswordConfirm);
            return false;
        } else if (passwordConfirm.trim().length() < 6){
            mLayoutPasswordConfirm.setError(getString(R.string.err_short_password));
            requestFocus(mPasswordConfirm);
            return false;
        } else {
            mLayoutPasswordConfirm.setErrorEnabled(false);
        }
        return true;
    }

    private boolean confirmationPassword(){
        if (!password.trim().equals(passwordConfirm)){
            return false;
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

}
