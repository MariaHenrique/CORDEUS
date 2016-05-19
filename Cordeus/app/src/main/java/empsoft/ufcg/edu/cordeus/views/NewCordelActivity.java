package empsoft.ufcg.edu.cordeus.views;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
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
        final TextInputLayout mLayoutCode = (TextInputLayout) dialog_code.findViewById(R.id.input_layout_code);
        Button btn_ok = (Button) dialog_code.findViewById(R.id.btn_ok);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String code = code_promotional.getText().toString();
                if (validateCodePromotional(code, code_promotional, mLayoutCode)) {
                    userController.validateCode(code, MainActivity.class);
                } else if (!validateCodePromotional(code, code_promotional, mLayoutCode)){
                    return;
                }
            }
        });

    }

    private boolean validateCodePromotional(final String code, final EditText editTextCode, final TextInputLayout layoutCode){
        if (code.trim().isEmpty()) {
            layoutCode.setError(getString(R.string.err_msg_code_promotional));
            requestFocus(editTextCode);
            return false;
        } else if (code.trim().length() < 6) {
            layoutCode.setError(getString(R.string.err_short_code_promotional));
            requestFocus(editTextCode);
            return false;
        } else {
            layoutCode.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

}
