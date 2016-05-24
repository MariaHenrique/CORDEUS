package empsoft.ufcg.edu.cordeus.controllers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import empsoft.ufcg.edu.cordeus.utils.HttpListener;
import empsoft.ufcg.edu.cordeus.utils.HttpUtils;
import empsoft.ufcg.edu.cordeus.utils.MySharedPreferences;
import empsoft.ufcg.edu.cordeus.views.LoginActivity;
import empsoft.ufcg.edu.cordeus.views.MainActivity;
import empsoft.ufcg.edu.cordeus.views.NewCordelActivity;
import empsoft.ufcg.edu.cordeus.views.RegisterActivity;

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

    public void registerUser(final String username, String password){
        RegisterActivity.mLoadingRegister.setVisibility(View.VISIBLE);
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
                    new AlertDialog.Builder(mActivity)
                            .setTitle("Erro")
                            .setMessage(result.getString("msg"))
                            .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    RegisterActivity.mLoadingRegister.setVisibility(View.GONE);
                                }
                            })
                            .create()
                            .show();
                } else {
                    addCordel(username, "Lamentações 3:22-23", MainActivity.class);
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
                                RegisterActivity.mLoadingRegister.setVisibility(View.GONE);
                            }
                        })
                        .create()
                        .show();
            }
        });

    }

    public void login(final String login, final String password, final Class classDest) {
        LoginActivity.mLoadingLogin.setVisibility(View.VISIBLE);
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
                                        LoginActivity.mLoadingLogin.setVisibility(View.GONE);
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
                                LoginActivity.mLoadingLogin.setVisibility(View.GONE);
                            }
                        })
                        .create()
                        .show();
            }
        });
    }

    public void validateCode(final String login, final String cordel_name, final String code,
                             final Class classDest){
        NewCordelActivity.mLoadingBuyCordel.setVisibility(View.VISIBLE);
        String rout_get_code = "http://cordeus-cordeus.rhcloud.com/getcode";
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
                                        NewCordelActivity.mLoadingBuyCordel.setVisibility(View.GONE);
                                    }
                                })
                                .create()
                                .show();
                    } else {
                        useCode(login, cordel_name, json, classDest);
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
                                NewCordelActivity.mLoadingBuyCordel.setVisibility(View.GONE);
                            }
                        })
                        .create()
                        .show();
            }
        });


    }

    private void useCode(final String login, final String cordel_name, final JSONObject json,
                         final Class classDest){

        String rout_remove_code = url + "removecode";

        mHttp.post(rout_remove_code, json.toString(), new HttpListener() {
            @Override
            public void onSucess(JSONObject result) throws JSONException{
                if (result.getInt("ok") == 0) {
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
                                    addCordel(login, cordel_name, MainActivity.class);
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

    public void addCordel(final String login, final String refercordel, final Class classDest){
            //mLoading.setVisibility(View.VISIBLE);
            String rout_add_cordel = url + "addCordel";
            final JSONObject json = new JSONObject();
            try {
                json.put("login", login);
                json.put("refercordel", refercordel);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mHttp.post(rout_add_cordel, json.toString(), new HttpListener() {
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
                            new AlertDialog.Builder(mActivity)
                                    .setTitle("Cordel adicionado")
                                    .setMessage(result.getString("Já está disponível em seus cordeis."))
                                    .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            // mLoading.setVisibility(View.GONE);
                                        }
                                    })
                                    .create()
                                    .show();
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

    public void getMyCordels(String username) {
        String route_get_user = "http://cordeus-cordeus.rhcloud.com/getUser?login=" + username;
        mHttp.get(route_get_user, new HttpListener() {
            @Override
            public void onSucess(JSONObject response) throws JSONException {
                if (response.getInt("ok") == 1) {
                    JSONObject jsonUser = response.getJSONObject("result");
                    JSONArray jsonArray = jsonUser.getJSONArray("listMyCordels");
                    mySharedPreferences.saveListMyCordels(jsonArray.toString());
                }
            }

            @Override
            public void onTimeout() {

                //Colocar o dialog de timeout

            }
        });
    }

    public void setView(Context context, Class classe) {
        Intent it = new Intent();
        it.setClass(context, classe);
        mActivity.startActivity(it);

    }
}


