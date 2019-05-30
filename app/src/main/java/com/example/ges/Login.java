package com.example.ges;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;

import org.json.JSONObject;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    TextView login_username_textview;
    TextView login_password_textview;
    TextView recover_password_textview;
    TextView recovpass;



    String error_check;

    LinearLayout cargar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button recover = findViewById(R.id.botonrecordar);
        Button cancrecov = findViewById(R.id.cancelarrecordar);
        recovpass = findViewById(R.id.recordar);
        final LinearLayout recovlay = findViewById(R.id.recordando);

        recovpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recovlay.setVisibility(View.VISIBLE);
            }
        });



        cancrecov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                recovlay.setVisibility(View.GONE);

            }
        });

        recover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recoverRequest();
            }
        });




    }

    public void aDentro(View view){

        Intent intent = new Intent (this, Jugar.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();

    }

    public void loginRequest (View view) {
        final LinearLayout cargar = findViewById(R.id.cargando);

        login_username_textview = (TextView) findViewById(R.id.login_username);
        login_password_textview = (TextView) findViewById(R.id.login_password);
        cargar.setVisibility(View.VISIBLE);

        if(checkInputLogin()) {
            // Input data ok, so go with the request

            // Url for the webservice
            String url = getString(R.string.base_url) + "/login.php";

            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        System.out.println(response.toString());

                        JSONObject responseJSON = new JSONObject(response);

                        if ((int) responseJSON.get("result") == 1) {
                            SessionManager session = new SessionManager(getApplicationContext());
                            session.setLogin(true, login_username_textview.getText().toString());
                            session.setKeys(responseJSON.getString("token"), responseJSON.getInt("idUser"));
                            cargar.setVisibility(View.GONE);
                            openMain();



                        } else {
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast;
                            CharSequence text;

                            text = "Error while login: " + responseJSON.get("message") + ".";
                            toast = Toast.makeText(getApplicationContext(), text, duration);
                            toast.show();

                            // Clean the text fields for new entries
                            login_username_textview.setText("");
                            login_password_textview.setText("");
                            cargar.setVisibility(View.GONE);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        cargar.setVisibility(View.GONE);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast;
                    CharSequence text;
                    text = "Error while login: " + error.getLocalizedMessage() + ".";
                    toast = Toast.makeText(getApplicationContext(), text, duration);
                    toast.show();
                    cargar.setVisibility(View.GONE);
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> login_params = new HashMap<String, String>();
                    login_params.put("username", login_username_textview.getText().toString());
                    login_params.put("password", login_password_textview.getText().toString());

                    return login_params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Content-Type", "application/x-www-form-urlencoded");
                    return params;
                }
            };
            queue.add(sr);
        }
        else {
            // Do nothing, error has been shown in a toast and views clean
        }
    }

    private void showError (CharSequence error) {
        int duration = Toast.LENGTH_LONG;
        Toast toast;

        toast = Toast.makeText(getApplicationContext(), error, duration);
        toast.show();
    }

    private boolean checkLength( String text, String fieldName, int min, int max ) {
        if ( text.length() > max || text.length() < min ) {
            error_check = error_check + "Length of " + fieldName + " must be between " +
                    min + " and " + max + ".\n";
            return false;
        } else {
            return true;
        }
    }

    private boolean checkRegexp(String text, Pattern regexp, String errorMessage) {
        if (!regexp.matcher(text).matches()) {
            error_check = error_check + errorMessage + "\n";
            return false;
        } else {
            return true;
        }
    }

    private boolean checkInputLogin () {

        error_check = "";
        boolean valid = true;

        // valid is evaluated in the second part to force the check function being called always, so all the errors are displayed at the same time (&& conditional evaluation)
        valid = checkLength( login_username_textview.getText().toString(), "username", 3, 16 ) && valid;
        valid = checkLength( login_password_textview.getText().toString(), "password", 5, 16 ) && valid;
//"/^[a-z]([0-9a-z_ ])+$/i"
        // In the regular expression for the username and password we do not use {3,16} (for instance),
        // to control the length through the regex, since it is most accurate to indicate the length error
        // separately, so it is not considered the length in the regex (it has been taken into account previously
        valid = checkRegexp( login_username_textview.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Username may consist of a-z, 0-9, underscores, spaces and must begin with a letter." ) && valid;
        valid = checkRegexp( login_password_textview.getText().toString(), Pattern.compile("^[0-9a-zA-Z]+$"), "Password field only allow : a-z 0-9") && valid;


        if (!error_check.equals("")){
            showError(error_check);
        }

        return valid;
    }

    /** Open Sign Up Activity */
    public void openSignUp(View view) {
        Intent intent = new Intent(this, Registro.class);
        startActivity(intent);
        finish();
    }
    /** Open MAIN Activity */
    public void openMain() {
        Intent intent = new Intent(this, Jugar.class);
        startActivity(intent);
        finish();
    }

    public void recoverRequest () {
        final LinearLayout cargar = findViewById(R.id.cargando);


        recover_password_textview = (TextView) findViewById(R.id.recover_password);
        cargar.setVisibility(View.VISIBLE);




            // Url for the webservice
            String url = getString(R.string.base_url) + "/recover.php";

            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        System.out.println(response.toString());

                        JSONObject responseJSON = new JSONObject(response);

                        if ((int) responseJSON.get("result") == 1) {

                            cargar.setVisibility(View.GONE);
                            Toast toast;
                            CharSequence text;
                            int duration = Toast.LENGTH_SHORT;
                            text = "Recibirás un mail en tu bandeja de entrada.";
                            toast = Toast.makeText(getApplicationContext(), text, duration);
                            toast.show();
                            openMain();



                        } else {
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast;
                            CharSequence text;

                            text = "Error while recovering.";
                            toast = Toast.makeText(getApplicationContext(), text, duration);
                            toast.show();

                            // Clean the text fields for new entries
                            login_username_textview.setText("");
                            login_password_textview.setText("");
                            recover_password_textview.setText("");
                            cargar.setVisibility(View.GONE);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast;
                        CharSequence text;

                        text = "Error while recovering.";
                        toast = Toast.makeText(getApplicationContext(), text, duration);
                        toast.show();

                        // Clean the text fields for new entries
                        login_username_textview.setText("");
                        login_password_textview.setText("");
                        recover_password_textview.setText("");
                        cargar.setVisibility(View.GONE);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast;
                    CharSequence text;
                    text = "Error while login: " + error.getLocalizedMessage() + ".";
                    toast = Toast.makeText(getApplicationContext(), text, duration);
                    toast.show();
                    cargar.setVisibility(View.GONE);
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> login_params = new HashMap<String, String>();

                    login_params.put("email", recover_password_textview.getText().toString());

                    return login_params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Content-Type", "application/x-www-form-urlencoded");
                    return params;
                }
            };
            queue.add(sr);

    }


}




