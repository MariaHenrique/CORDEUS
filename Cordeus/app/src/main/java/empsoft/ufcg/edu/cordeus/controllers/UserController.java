package empsoft.ufcg.edu.cordeus.controllers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import empsoft.ufcg.edu.cordeus.utils.HttpListener;
import empsoft.ufcg.edu.cordeus.utils.HttpUtils;

public class UserController {

    private HttpUtils mHttp;
    private Activity mActivity;
    private String url;

    public UserController(Activity activity) {
        mActivity = activity;
        mHttp = new HttpUtils(mActivity);
        url =  "http://cordeus-cordeus.rhcloud.com/";
    }

    public void registerUser(String username, String password){
       // mLoadingCadastre.setVisibility(View.VISIBLE);
        String rout_register = url + "registeruser";
        JSONObject json = new JSONObject();
        try {
            json.put("login", username);
            json.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mHttp.post(rout_register, json.toString(), new HttpListener() {
            @Override
            public void onSucess(JSONObject result) throws JSONException{
                if (result.getInt("ok") == 0) {
                    Log.d("DANI", "ok 0");
                    new AlertDialog.Builder(mActivity)
                            .setTitle("Erro")
                            .setMessage(result.getString("msg"))
                            .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //mLoadingCadastre.setVisibility(View.GONE);
                                }
                            })
                            .create()
                            .show();
                } else {
                    new AlertDialog.Builder(mActivity)
                            .setMessage("Cadastro realizado com sucesso!")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    mActivity.finish();
                                    mActivity.finish();
                                }
                            })
                            .create()
                            .show();
                }
            }
            @Override
            public void onTimeout() {
                new AlertDialog.Builder(mActivity)
                        .setTitle("Erro")
                        .setMessage("Conexão não disponível!")
                        .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                               // mLoadingCadastre.setVisibility(View.GONE);
                            }
                        })
                        .create()
                        .show();
            }
        });

    }

    public void login(String login, String password) {
        //mLoading.setVisibility(View.VISIBLE);
        String rout_check_login = url + "checklogin";
        final JSONObject json = new JSONObject();
        try {
            json.put("login", login);
            json.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mHttp.post(rout_check_login, json.toString(), new HttpListener() {
            @Override
            public void onSucess(JSONObject result) {
                try {
                    if (result.getInt("ok") == 0) {
                        new AlertDialog.Builder(mActivity)
                                .setTitle("Erro")
                                .setMessage(result.getString("msg"))
                                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                       // mLoading.setVisibility(View.GONE);
                                    }
                                })
                                .create()
                                .show();
                    } else {
                       // setView(LoginActivity.this, DonorsActivity.class);
                        //finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onTimeout() {
                new AlertDialog.Builder(mActivity)
                        .setTitle("Erro")
                        .setMessage("Conexão não disponível.")
                        .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                               // mLoading.setVisibility(View.GONE);
                            }
                        })
                        .create()
                        .show();
            }
        });
    }
}


