package empsoft.ufcg.edu.cordeus.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import empsoft.ufcg.edu.cordeus.R;
import empsoft.ufcg.edu.cordeus.controllers.UserController;

public class RegisterActivity extends AppCompatActivity {

    private Button mRegister;
    private EditText mUsername;
    private EditText mPassword;

    private UserController usercontroller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mRegister = (Button) findViewById(R.id.btn_cadastre);
        mUsername = (EditText) findViewById(R.id.et_username);
        mPassword = (EditText) findViewById(R.id.et_password);

        usercontroller = new UserController(RegisterActivity.this);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = mUsername.getText().toString();
                String password = mPassword.getText().toString();
                usercontroller.registerUser(username, password);
            }
        });
    }

}
