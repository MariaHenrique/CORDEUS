package empsoft.ufcg.edu.cordeus.controllers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import empsoft.ufcg.edu.cordeus.utils.HttpListener;
import empsoft.ufcg.edu.cordeus.utils.HttpUtils;
import empsoft.ufcg.edu.cordeus.utils.MySharedPreferences;

public class UserController {

    private HttpUtils mHttp;
    private Activity mActivity;
    private String url;
    private MySharedPreferences mySharedPreferences;

    public UserController(Activity activity) {
        mActivity = activity;
        mHttp = new HttpUtils(mActivity);
        url =  "http://cordeus-cordeus.rhcloud.com/";
        mySharedPreferences = new MySharedPreferences(mActivity.getApplicationContext());
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

    public void login(final String login, final String password, final Class classDest) {
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
                        mySharedPreferences.saveUser(login, password);
                        setView(mActivity, classDest);
                        mActivity.finish();
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

    public void validateCode(final String code, final Class classDest){
        String rout_get_code = url + "getcode";
        final JSONObject json = new JSONObject();
        try {
            json.put("code", code);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mHttp.post(rout_get_code, json.toString(), new HttpListener() {
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
                        useCode(json, classDest);
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
                            }
                        })
                        .create()
                        .show();
            }
        });


    }

    private void useCode(final JSONObject json, final Class classDest){

        String rout_remove_code = url + "removecode";

        mHttp.post(rout_remove_code, json.toString(), new HttpListener() {
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
                            .setTitle("Cordel retirado ")
                            .setMessage("Seu cordel já está disponível. Aproveite!")
                            .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    setView(mActivity, classDest);
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

    public void setView(Context context, Class classe) {
        Intent it = new Intent();
        it.setClass(context, classe);
        mActivity.startActivity(it);
    }
}


