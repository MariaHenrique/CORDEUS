package empsoft.ufcg.edu.cordeus.views;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

import empsoft.ufcg.edu.cordeus.R;
import empsoft.ufcg.edu.cordeus.controllers.UserController;
import empsoft.ufcg.edu.cordeus.models.Cordel;
import empsoft.ufcg.edu.cordeus.utils.MySharedPreferences;

public class NewCordelActivity extends AppCompatActivity {

    private UserController userController;
    private Cordel cordel;
    private String login;
    private HashMap<String, String> userDetails;
    private MySharedPreferences mySharedPreferences;
    public static View mLoadingBuyCordel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cordel);

        final Button code_promotional = (Button) findViewById(R.id.btn_code_promotional);
        mySharedPreferences = new MySharedPreferences(getApplicationContext());
        userController = new UserController(NewCordelActivity.this);
        userDetails = mySharedPreferences.getUserDetails();
        login = userDetails.get(MySharedPreferences.KEY_USERNAME);

        code_promotional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        Intent it = getIntent();
        cordel = (Cordel) it.getSerializableExtra("NEWCORDEL");
        showDetails(cordel);
    }

    private void showDetails(Cordel cordel) {
        final ImageView imageView = (ImageView) findViewById(R.id.iv_cordel);
        final TextView tvTitle = (TextView) findViewById(R.id.tv_title);
        final TextView tvPassage = (TextView) findViewById(R.id.tv_passage);

        imageView.setBackgroundResource(getImage(cordel.getTitle()));
        tvTitle.setText(cordel.getTitle());
        tvPassage.setText(cordel.getPassage());
    }

    private void openDialog() {

        final Dialog dialog_code = new Dialog(NewCordelActivity.this);
        dialog_code.setContentView(R.layout.dialog_buy_cordel);
        dialog_code.setTitle("Código promocional");
        dialog_code.setCancelable(true);
        dialog_code.show();

        mLoadingBuyCordel = dialog_code.findViewById(R.id.loadingBuyCordel);
        final EditText code_promotional = (EditText) dialog_code.findViewById(R.id.et_code);
        final TextInputLayout mLayoutCode = (TextInputLayout) dialog_code.findViewById(R.id.input_layout_code);
        Button btn_ok = (Button) dialog_code.findViewById(R.id.btn_ok);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String code = code_promotional.getText().toString();

                if (validateCodePromotional(code, code_promotional, mLayoutCode)) {
                    userController.validateCode(login, cordel.getTitle(), code, MainActivity.class);
                } else if (!validateCodePromotional(code, code_promotional, mLayoutCode)) {
                    return;
                }

                dialog_code.dismiss();
            }
        });

    }

    public void setView(Context context, Class classe) {
        Intent it = new Intent();
        it.setClass(context, classe);
        startActivity(it);

    }


    private int getImage(String refer) {
        switch (refer) {
            case "Confia no Senhor":
                return R.drawable.filipenses150;
            case "São João":
                return R.drawable.saojoao150;
            case "São Pedro":
                return R.drawable.saopedro150;
            case "Santo Antônio":
                return R.drawable.santoantonio150;
            default:
                return R.mipmap.cordel_blocked;
        }
    }
    private boolean validateCodePromotional(final String code, final EditText editTextCode, final TextInputLayout layoutCode) {
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
