package empsoft.ufcg.edu.cordeus.views;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import empsoft.ufcg.edu.cordeus.R;

public class NewCordelActivity extends AppCompatActivity {

    private Button code_promotional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cordel);

        code_promotional = (Button) findViewById(R.id.btn_code_promotional);

        code_promotional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

    }

    private void openDialog(){

        final Dialog dialogChooseForm = new Dialog(NewCordelActivity.this);
        dialogChooseForm.setContentView(R.layout.dialog_buy_cordel);
        dialogChooseForm.setTitle("Código promocional");
        dialogChooseForm.setCancelable(true);
        dialogChooseForm.show();
    }
}
