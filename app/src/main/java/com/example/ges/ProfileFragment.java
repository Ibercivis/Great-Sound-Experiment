package com.example.ges;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    String error_check;

    public ProfileFragment() {
        // Required empty public constructor


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_profile, container, false);

        final Spinner sexospinner = (Spinner) v.findViewById(R.id.spinnersexo);
        final Spinner edadspinner = (Spinner) v.findViewById(R.id.spinneredad);
        final Spinner cascosspinner = (Spinner) v.findViewById(R.id.spinnerauriculares);
        final Spinner preciospinner = (Spinner) v.findViewById(R.id.spinnerprecios);
        final Spinner formaspinner = (Spinner) v.findViewById(R.id.spinnerformacion);
        final TextView usernametext = v.findViewById(R.id.textparauser);
        final TextView atext = v.findViewById(R.id.txtedad);
        final TextView stext = v.findViewById(R.id.txtsexo);
        final TextView ftext = v.findViewById(R.id.txtformacion);
        final TextView htext = v.findViewById(R.id.txtauriculares);
        final TextView ptext = v.findViewById(R.id.txtprecio);

        final Button applychange = v.findViewById(R.id.aplicarcambio);
        final Button cancelchange = v.findViewById(R.id.cancelarcambio);
        final Button apply = v.findViewById(R.id.aceptarcambios);
        final Button cerrar = v.findViewById(R.id.cerrarsesion);

        final LinearLayout tabmodificar = v.findViewById(R.id.modificar);
        final LinearLayout info = v.findViewById(R.id.mostrar);

// Creamos adaptador, fijamos la vista dropdown y asociamos adaptador a array y spinner
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(),
                R.array.sexo_array, android.R.layout.simple_spinner_item);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sexospinner.setAdapter(adapter1);
        // Creamos adaptador, fijamos la vista dropdown y asociamos adaptador a array y spinner
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),
                R.array.edades_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edadspinner.setAdapter(adapter2);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(getActivity(),
                R.array.tipocascos_array, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cascosspinner.setAdapter(adapter3);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(getActivity(),
                R.array.precios_array, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        preciospinner.setAdapter(adapter4);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(getActivity(),
                R.array.formacion_array, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        formaspinner.setAdapter(adapter5);

        getInfoRequest(usernametext, stext, atext, ftext, htext, ptext);

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.setVisibility(View.GONE);
                tabmodificar.setVisibility(View.VISIBLE);
                apply.setVisibility(View.INVISIBLE);
                cerrar.setVisibility(View.INVISIBLE);

            }
        });

        applychange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateInfo(sexospinner, edadspinner, cascosspinner, preciospinner, formaspinner);
                info.setVisibility(View.VISIBLE);
                tabmodificar.setVisibility(View.GONE);
                apply.setVisibility(View.VISIBLE);
                cerrar.setVisibility(View.VISIBLE);
                getInfoRequest(usernametext, stext, atext, ftext, htext, ptext);
            }
        });

        cancelchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                info.setVisibility(View.VISIBLE);
                tabmodificar.setVisibility(View.GONE);
                apply.setVisibility(View.VISIBLE);
                cerrar.setVisibility(View.VISIBLE);

            }
        });

        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManager session = new SessionManager(getActivity());
                session.setClear();
                Intent intent = new Intent(getActivity(), SplashActivity.class);
                startActivity(intent);

            }
        });


        return v;
    }

    public void getInfoRequest (final TextView usernametxt, final TextView sextxt, final TextView agetxt, final TextView formationtxt,
                                final TextView headphonestxt, final TextView pricetxt) {

            String idUser, toktok;
            SessionManager session = new SessionManager(getActivity());

            // Input data ok, so go with the request

            // Url for the webservice
            idUser = String.valueOf(session.getIdUser());
            toktok = session.getToken();
            String url = getString(R.string.base_url) + "/getUserInfo.php?idUser=" + idUser +"&token=" + toktok;

            RequestQueue queue = Volley.newRequestQueue(getActivity());
            queue.getCache().clear();
            StringRequest sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        System.out.println(response.toString());

                        JSONObject responseJSON = new JSONObject(response);

                        if ((int) responseJSON.get("result") == 1){

                            JSONObject jsonArray = responseJSON.getJSONObject("data");
                            String user = String.valueOf(jsonArray.get("username"));
                            usernametxt.setText(user);
                            sextxt.setText(String.valueOf(jsonArray.get("sexo")));
                            agetxt.setText(String.valueOf(jsonArray.get("edad")));
                            formationtxt.setText(String.valueOf(jsonArray.get("formacion_musical")));
                            headphonestxt.setText(String.valueOf(jsonArray.get("tipo_auriculares")));
                            pricetxt.setText(String.valueOf(jsonArray.get("rango_precio")));


                        }
                        else {
                            Log.println(1, "Error", "Algo ha fallado que la respuesta es 0");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
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

    public void updateInfo (final Spinner sspin, final Spinner espin, final Spinner cspin, final Spinner pspin, final Spinner fspin) {

        final SessionManager session = new SessionManager(getActivity());


        if(checkInputSignup(sspin, espin, cspin, pspin, fspin)) {
            // Input data ok, so go with the request

            // Url for the webservice
            String url = getString(R.string.base_url) + "/updateUserInfo.php";

            RequestQueue queue = Volley.newRequestQueue(getActivity());
            queue.getCache().clear();
            StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        System.out.println(response.toString());

                        JSONObject responseJSON = new JSONObject(response);

                        if ((int) responseJSON.get("result") == 1){

                            Toast toast;

                            toast = Toast.makeText(getActivity(), "Información Actualizada", Toast.LENGTH_LONG);
                            toast.show();


                        }
                        else {
                            showError("Error while signing up: " + responseJSON.get("message") + ".");

                            // Clean the text fields for new entries

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
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


                    signup_params.put("idUser", String.valueOf(session.getIdUser()));
                    signup_params.put("token", session.getToken());
                    signup_params.put("edad", espin.getSelectedItem().toString());
                    signup_params.put("sexo", sspin.getSelectedItem().toString());
                    signup_params.put("formacion_musical", fspin.getSelectedItem().toString());
                    signup_params.put("rango_precio", pspin.getSelectedItem().toString());
                    signup_params.put("tipo_auriculares", cspin.getSelectedItem().toString());

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

        toast = Toast.makeText(getActivity(), error, duration);
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

    private boolean checkInputSignup (final Spinner sspin, final Spinner espin, final Spinner cspin, final Spinner pspin, final Spinner fspin) {

        error_check = "";
        boolean valid = true;
        String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        // valid is evaluated in the second part to force the check function being called always, so all the errors are displayed at the same time (&& conditional evaluation)

//"/^[a-z]([0-9a-z_ ])+$/i"
        // In the regular expression for the username and password we do not use {3,16} (for instance),
        // to control the length through the regex, since it is most accurate to indicate the length error
        // separately, so it is not considered the length in the regex (it has been taken into account previously



        valid = checkSelect( espin, "age" ) && valid;
        valid = checkSelect( sspin, "gender" ) && valid;
        valid = checkSelect( fspin, "musical formation" ) && valid;
        valid = checkSelect( pspin, "price" ) && valid;
        valid = checkSelect( cspin, "headphones" ) && valid;

        if (!error_check.equals("")){
            showError(error_check);
        }

        return valid;
    }

    }


