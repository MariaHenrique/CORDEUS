package empsoft.ufcg.edu.cordeus.views;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import empsoft.ufcg.edu.cordeus.R;
import empsoft.ufcg.edu.cordeus.controllers.UserController;

public class NewCordelActivity extends AppCompatActivity {


    private UserController userController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cordel);

        final Button code_promotional = (Button) findViewById(R.id.btn_code_promotional);
        userController = new UserController(NewCordelActivity.this);

        code_promotional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

    }

    private void openDialog() {

        final Dialog dialog_code = new Dialog(NewCordelActivity.this);
        dialog_code.setContentView(R.layout.dialog_buy_cordel);
        dialog_code.setTitle("Código promocional");
        dialog_code.setCancelable(true);
        dialog_code.show();

        final EditText code_promotional = (EditText) dialog_code.findViewById(R.id.et_code);

        Button btn_ok = (Button) dialog_code.findViewById(R.id.btn_ok);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String code = code_promotional.getText().toString();
                userController.validateCode(code, MainActivity.class);
            }
        });

    }
}
