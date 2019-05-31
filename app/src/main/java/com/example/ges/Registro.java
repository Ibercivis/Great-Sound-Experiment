package com.example.ges;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Registro extends AppCompatActivity {

    TextView signup_username_textview, signup_email_textview, signup_password_textview;
    int sex_item, headphones_item, formation_item;
    int age_item, price_item;
    int selected;
    Spinner sexospinner;
    Spinner edadspinner;
    Spinner cascosspinner;
    Spinner preciospinner;
    Spinner formaspinner;
    String error_check;
    LinearLayout cargar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registro);

        final Spinner sexospinner = (Spinner) findViewById(R.id.spinnersexo);
        final Spinner edadspinner = (Spinner) findViewById(R.id.spinneredad);
        final Spinner cascosspinner = (Spinner) findViewById(R.id.spinnerauriculares);
        final Spinner preciospinner = (Spinner) findViewById(R.id.spinnerprecios);
        final Spinner formaspinner = (Spinner) findViewById(R.id.spinnerformacion);
        final LinearLayout rgpd = findViewById(R.id.protecciondatos);

        Button applyrgpd = findViewById(R.id.rgpdapply);
        Button cancelrgpd = findViewById(R.id.rgpdcancel);
        Button registar = findViewById(R.id.registroaceptar);

        registar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rgpd.setVisibility(View.VISIBLE);
            }
        });

        applyrgpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupRequest();
            }
        });

        cancelrgpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rgpd.setVisibility(View.GONE);
            }
        });

// Creamos adaptador, fijamos la vista dropdown y asociamos adaptador a array y spinner
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.sexo_array, android.R.layout.simple_spinner_item);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sexospinner.setAdapter(adapter1);

        sexospinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected = sexospinner.getSelectedItemPosition();
                if (selected != 0)
                    sex_item = selected;
                System.out.println(selected);

                sexospinner.setSelection(sex_item);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // Creamos adaptador, fijamos la vista dropdown y asociamos adaptador a array y spinner
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.edades_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edadspinner.setAdapter(adapter2);

        edadspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected = edadspinner.getSelectedItemPosition();
                if (selected != 0)
                    edadspinner.setSelection(selected);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.tipocascos_array, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cascosspinner.setAdapter(adapter3);


        cascosspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected = cascosspinner.getSelectedItemPosition();
                if (selected != 0)
                    cascosspinner.setSelection(selected);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                R.array.precios_array, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        preciospinner.setAdapter(adapter4);

        preciospinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected = preciospinner.getSelectedItemPosition();
                if (selected != 0)
                    preciospinner.setSelection(selected);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,
                R.array.formacion_array, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        formaspinner.setAdapter(adapter5);

        formaspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected = formaspinner.getSelectedItemPosition();
                if (selected != 0)
                    formaspinner.setSelection(selected);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void escucharSpinner (final Spinner spin){

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected = spin.getSelectedItemPosition();
                if (selected!=0) {
                    spin.setSelection(selected);
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void signupRequest () {
        final LinearLayout cargar = findViewById(R.id.cargando);

        cargar.setVisibility(View.VISIBLE);
        signup_username_textview = (TextView) findViewById(R.id.txtusuario);
        signup_email_textview = (TextView) findViewById(R.id.txtemail);
        signup_password_textview = (TextView) findViewById(R.id.txtcontraseña);
        final Spinner sexospinner = (Spinner) findViewById(R.id.spinnersexo);
        final Spinner edadspinner = (Spinner) findViewById(R.id.spinneredad);
        final Spinner cascosspinner = (Spinner) findViewById(R.id.spinnerauriculares);
        final Spinner preciospinner = (Spinner) findViewById(R.id.spinnerprecios);
        final Spinner formaspinner = (Spinner) findViewById(R.id.spinnerformacion);

        if(checkInputSignup()) {
            // Input data ok, so go with the request

            // Url for the webservice
            String url = getString(R.string.base_url) + "/signup.php";

            RequestQueue queue = Volley.newRequestQueue(this);
            queue.getCache().clear();
            StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        System.out.println(response.toString());

                        JSONObject responseJSON = new JSONObject(response);

                        if ((int) responseJSON.get("result") == 1){
                            SessionManager session = new SessionManager(getApplicationContext());
                            session.setLogin(true, signup_username_textview.getText().toString());
                            Toast toast;
                            cargar.setVisibility(View.GONE);

                            toast = Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_LONG);
                            toast.show();
                            openLogin();


                        }
                        else {
                            showError("Error while signing up: " + responseJSON.get("message") + ".");

                            // Clean the text fields for new entries
                            signup_username_textview.setText("");
                            signup_email_textview.setText("");
                            signup_password_textview.setText("");
                            age_item = 0;
                            edadspinner.setSelection(age_item);
                            sex_item = 0;
                            sexospinner.setSelection(sex_item);
                            headphones_item = 0;
                            cascosspinner.setSelection(headphones_item);
                            formation_item = 0;
                            formaspinner.setSelection(formation_item);
                            price_item = 0;
                            preciospinner.setSelection(price_item);
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
                }
            }){
                @Override
                protected Map<String,String> getParams(){
                    Map<String,String> signup_params = new HashMap<String, String>();
                    signup_params.put("username", signup_username_textview.getText().toString());
                    signup_params.put("email", signup_email_textview.getText().toString());
                    signup_params.put("password", signup_password_textview.getText().toString());
                    signup_params.put("edad", edadspinner.getSelectedItem().toString());
                    signup_params.put("sexo", sexospinner.getSelectedItem().toString());
                    signup_params.put("formacion_musical", formaspinner.getSelectedItem().toString());
                    signup_params.put("rango_precio", preciospinner.getSelectedItem().toString());
                    signup_params.put("tipo_auriculares", cascosspinner.getSelectedItem().toString());

                    return signup_params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("Content-Type","application/x-www-form-urlencoded");
                    return params;
                }
            };
            sr.setShouldCache(false);
            queue.add(sr);
        }
        else {
            // Si llego aquí es ruina. nothing, error has been shown in a toast and views clean
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

    private boolean checkSelect(Spinner spinner, String errorMessage) {

        if (spinner.getSelectedItemPosition() == 0) {
            error_check = error_check + "You must select one of the options from the " + errorMessage + " drop-down.\n";
            return false;
        }
        return true;
    }

    private boolean checkInputSignup () {

        error_check = "";
        boolean valid = true;
        String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        // valid is evaluated in the second part to force the check function being called always, so all the errors are displayed at the same time (&& conditional evaluation)
        valid = checkLength( signup_username_textview.getText().toString(), "username", 3, 16 ) && valid;
        valid = checkLength( signup_email_textview.getText().toString(), "email", 6, 80 ) && valid;
        valid = checkLength( signup_password_textview.getText().toString(), "password", 5, 16 ) && valid;
//"/^[a-z]([0-9a-z_ ])+$/i"
        // In the regular expression for the username and password we do not use {3,16} (for instance),
        // to control the length through the regex, since it is most accurate to indicate the length error
        // separately, so it is not considered the length in the regex (it has been taken into account previously
        valid = checkRegexp( signup_username_textview.getText().toString(), Pattern.compile("^[a-zA-Z][a-zA-Z0-9 _]+$"), "Username may consist of a-z, 0-9, underscores, spaces and must begin with a letter." ) && valid;
        valid = checkRegexp( signup_email_textview.getText().toString(), Pattern.compile(emailRegex), "Wrong email address, eg. user@odourcollect.com" ) && valid;
        valid = checkRegexp( signup_password_textview.getText().toString(), Pattern.compile("^[0-9a-zA-Z]+$"), "Password field only allow : a-z 0-9") && valid;

        final Spinner sexospinner = (Spinner) findViewById(R.id.spinnersexo);
        final Spinner edadspinner = (Spinner) findViewById(R.id.spinneredad);
        final Spinner cascosspinner = (Spinner) findViewById(R.id.spinnerauriculares);
        final Spinner preciospinner = (Spinner) findViewById(R.id.spinnerprecios);
        final Spinner formaspinner = (Spinner) findViewById(R.id.spinnerformacion);

        valid = checkSelect( edadspinner, "age" ) && valid;
        valid = checkSelect( sexospinner, "gender" ) && valid;
        valid = checkSelect( formaspinner, "musical formation" ) && valid;
        valid = checkSelect( preciospinner, "price" ) && valid;
        valid = checkSelect( cascosspinner, "headphones" ) && valid;

        if (!error_check.equals("")){
            showError(error_check);
        }

        return valid;
    }

    /** Open MAIN Activity */
    public void openLogin() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }

}
