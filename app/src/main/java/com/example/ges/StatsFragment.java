package com.example.ges;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class StatsFragment extends Fragment {

    int expandido1 = 0;
    int expandido2 = 0;
    int expandido3 = 0;
    int expandido4 = 0;
    int expandido5 = 0;



    public StatsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_stats, container, false);

        final Spinner filtrospinner = (Spinner) v.findViewById(R.id.spinnerfiltro);
        final ImageView xpand1 = v.findViewById(R.id.expand1);
        final ImageView xpand2 = v.findViewById(R.id.expand2);
        final ImageView xpand3 = v.findViewById(R.id.expand3);
        final ImageView xpand4 = v.findViewById(R.id.expand4);
        final ImageView xpand5 = v.findViewById(R.id.expand5);
        final LinearLayout detalle1 = v.findViewById(R.id.detaillev1);
        final LinearLayout detalle2 = v.findViewById(R.id.detaillev2);
        final LinearLayout detalle3 = v.findViewById(R.id.detaillev3);
        final LinearLayout detalle4 = v.findViewById(R.id.detaillev4);
        final LinearLayout detalle5 = v.findViewById(R.id.detaillev5);
        final CardView cardstat = v.findViewById(R.id.cardview);

        final ProgressBar lev1gen = v.findViewById(R.id.genlev1);
        final ProgressBar lev1ind = v.findViewById(R.id.indlev1);
        final ProgressBar lev1hoy = v.findViewById(R.id.hoylev1);
        final TextView num1g = v.findViewById(R.id.numg1);
        final TextView num1i = v.findViewById(R.id.numi1);
        final TextView num1h = v.findViewById(R.id.numh1);

        final ProgressBar lev2gen = v.findViewById(R.id.genlev2);
        final ProgressBar lev2ind = v.findViewById(R.id.indlev2);
        final ProgressBar lev2hoy = v.findViewById(R.id.hoylev2);
        final TextView num2g = v.findViewById(R.id.numg2);
        final TextView num2i = v.findViewById(R.id.numi2);
        final TextView num2h = v.findViewById(R.id.numh2);

        final ProgressBar lev3gen = v.findViewById(R.id.genlev3);
        final ProgressBar lev3ind = v.findViewById(R.id.indlev3);
        final ProgressBar lev3hoy = v.findViewById(R.id.hoylev3);
        final TextView num3g = v.findViewById(R.id.numg3);
        final TextView num3i = v.findViewById(R.id.numi3);
        final TextView num3h = v.findViewById(R.id.numh3);

        final ProgressBar lev4gen = v.findViewById(R.id.genlev4);
        final ProgressBar lev4ind = v.findViewById(R.id.indlev4);
        final ProgressBar lev4hoy = v.findViewById(R.id.hoylev4);
        final TextView num4g = v.findViewById(R.id.numg4);
        final TextView num4i = v.findViewById(R.id.numi4);
        final TextView num4h = v.findViewById(R.id.numh4);

        final ProgressBar lev5gen = v.findViewById(R.id.genlev5);
        final ProgressBar lev5ind = v.findViewById(R.id.indlev5);
        final ProgressBar lev5hoy = v.findViewById(R.id.hoylev5);
        final TextView num5g = v.findViewById(R.id.numg5);
        final TextView num5i = v.findViewById(R.id.numi5);
        final TextView num5h = v.findViewById(R.id.numh5);
        Button apply = v.findViewById(R.id.aplicar);

        xpand1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandido1==0){
                    TransitionManager.beginDelayedTransition(cardstat);
                    detalle1.setVisibility(View.VISIBLE);
                    expandido1 = 1;
                    xpand1.setImageDrawable(getResources().getDrawable(R.drawable.ic_collapse));
                }
                else if(expandido1==1){
                    TransitionManager.beginDelayedTransition(cardstat);
                    detalle1.setVisibility(View.GONE);
                    expandido1 = 0;
                    xpand1.setImageDrawable(getResources().getDrawable(R.drawable.ic_expand));
                }
            }
        });

        xpand2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandido2==0){
                    TransitionManager.beginDelayedTransition(cardstat);
                    detalle2.setVisibility(View.VISIBLE);
                    expandido2 = 1;
                    xpand2.setImageDrawable(getResources().getDrawable(R.drawable.ic_collapse));
                }
                else if(expandido2==1){
                    TransitionManager.beginDelayedTransition(cardstat);
                    detalle2.setVisibility(View.GONE);
                    expandido2 = 0;
                    xpand2.setImageDrawable(getResources().getDrawable(R.drawable.ic_expand));
                }
            }
        });

        xpand3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandido3==0){
                    TransitionManager.beginDelayedTransition(cardstat);
                    detalle3.setVisibility(View.VISIBLE);
                    expandido3 = 1;
                    xpand3.setImageDrawable(getResources().getDrawable(R.drawable.ic_collapse));
                }
                else if(expandido3==1){
                    TransitionManager.beginDelayedTransition(cardstat);
                    detalle3.setVisibility(View.GONE);
                    expandido3 = 0;
                    xpand3.setImageDrawable(getResources().getDrawable(R.drawable.ic_expand));
                }
            }
        });

        xpand4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandido4==0){
                    TransitionManager.beginDelayedTransition(cardstat);
                    detalle4.setVisibility(View.VISIBLE);
                    expandido4 = 1;
                    xpand4.setImageDrawable(getResources().getDrawable(R.drawable.ic_collapse));
                }
                else if(expandido4==1){
                    TransitionManager.beginDelayedTransition(cardstat);
                    detalle4.setVisibility(View.GONE);
                    expandido4 = 0;
                    xpand4.setImageDrawable(getResources().getDrawable(R.drawable.ic_expand));
                }
            }
        });

        xpand5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandido5==0){
                    TransitionManager.beginDelayedTransition(cardstat);
                    detalle5.setVisibility(View.VISIBLE);
                    expandido5 = 1;
                    xpand5.setImageDrawable(getResources().getDrawable(R.drawable.ic_collapse));
                }
                else if(expandido5==1){
                    TransitionManager.beginDelayedTransition(cardstat);
                    detalle5.setVisibility(View.GONE);
                    expandido5 = 0;
                    xpand5.setImageDrawable(getResources().getDrawable(R.drawable.ic_expand));
                }
            }
        });






        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(),
                R.array.filtro_array, android.R.layout.simple_spinner_item);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        filtrospinner.setAdapter(adapter1);

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInfoRequest(filtrospinner, lev1gen, lev1ind, lev1hoy, lev2gen, lev2ind, lev2hoy,
                        lev3gen, lev3ind, lev3hoy, lev4gen, lev4ind, lev4hoy, lev5gen, lev5ind, lev5hoy,
                        num1g, num1i, num1h, num2g, num2i, num2h, num3g, num3i, num3h, num4g, num4i, num4h, num5g, num5i, num5h);
            }
        });

        return v;


    }

    public void getInfoRequest (Spinner filterspin, final ProgressBar pdg1, final ProgressBar pdi1, final ProgressBar pdh1, final ProgressBar pdg2, final ProgressBar pdi2, final ProgressBar pdh2, final ProgressBar pdg3, final ProgressBar pdi3, final ProgressBar pdh3, final ProgressBar pdg4, final ProgressBar pdi4, final ProgressBar pdh4, final ProgressBar pdg5, final ProgressBar pdi5, final ProgressBar pdh5,
                                final TextView n1g, final TextView n1i, final TextView n1h, final TextView n2g, final TextView n2i, final TextView n2h, final TextView n3g, final TextView n3i, final TextView n3h, final TextView n4g, final TextView n4i, final TextView n4h, final TextView n5g, final TextView n5i, final TextView n5h) {

        String idUser, toktok, filtro = "";
        int item;
        SessionManager session = new SessionManager(getActivity());

        // Input data ok, so go with the request

        // Url for the webservice
        idUser = String.valueOf(session.getIdUser());
        toktok = session.getToken();
        item = filterspin.getSelectedItemPosition();
        if (item == 0){
            filtro = "";
            Toast toast;
            toast = Toast.makeText(getActivity(), "Debes seleccionar un filtro", Toast.LENGTH_LONG);
            toast.show();
            return;
        } else if (item == 1){
            filtro = "";
        } else if (item == 2){
            filtro = "&sexo=Hombre";
        } else if (item == 3){
            filtro = "&sexo=Mujer";
        } else if (item == 4){
            filtro = "&rango_precio=30";
        } else if (item == 5){
            filtro = "&rango_precio=29";
        }

        String url = getString(R.string.base_url) + "/statistics.php?idUser=" + idUser +"&token=" + toktok + filtro;

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.getCache().clear();
        StringRequest sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    System.out.println(response.toString());

                    JSONObject responseJSON = new JSONObject(response);

                    if ((int) responseJSON.get("result") == 1){

                        int value;
                        JSONArray jsonArray = responseJSON.getJSONArray("data");
                        JSONArray jsonArray1 = jsonArray.getJSONArray(0); //Array del día
                        value = Integer.valueOf(String.valueOf(jsonArray1.getJSONObject(0).get("percentage"))); //Nivel 1 del día de hoy
                        pdh1.setProgress(Integer.valueOf(value), true);
                        coloreaNum(Integer.valueOf(value), n1h);
                        value = Integer.valueOf(String.valueOf(jsonArray1.getJSONObject(1).get("percentage"))); //Nivel 2 del día de hoy
                        pdh2.setProgress(Integer.valueOf(value), true);
                        coloreaNum(Integer.valueOf(value), n2h);
                        value = Integer.valueOf(String.valueOf(jsonArray1.getJSONObject(2).get("percentage"))); //Nivel 3 del día de hoy
                        pdh3.setProgress(Integer.valueOf(value), true);
                        coloreaNum(Integer.valueOf(value), n3h);
                        value = Integer.valueOf(String.valueOf(jsonArray1.getJSONObject(3).get("percentage"))); //Nivel 4 del día de hoy
                        pdh4.setProgress(Integer.valueOf(value), true);
                        coloreaNum(Integer.valueOf(value), n4h);
                        value = Integer.valueOf(String.valueOf(jsonArray1.getJSONObject(4).get("percentage"))); //Nivel 5 del día de hoy
                        pdh5.setProgress(Integer.valueOf(value), true);
                        coloreaNum(Integer.valueOf(value), n5h);

                        jsonArray1 = jsonArray.getJSONArray(1); //Array histórico de la persona
                        value = Integer.valueOf(String.valueOf(jsonArray1.getJSONObject(0).get("percentage"))); //Nivel 1 del histórico
                        pdi1.setProgress(Integer.valueOf(value), true);
                        coloreaNum(Integer.valueOf(value), n1i);
                        value = Integer.valueOf(String.valueOf(jsonArray1.getJSONObject(1).get("percentage"))); //Nivel 2 del histórico
                        pdi2.setProgress(Integer.valueOf(value), true);
                        coloreaNum(Integer.valueOf(value), n2i);
                        value = Integer.valueOf(String.valueOf(jsonArray1.getJSONObject(2).get("percentage"))); //Nivel 3 del histórico
                        pdi3.setProgress(Integer.valueOf(value), true);
                        coloreaNum(Integer.valueOf(value), n3i);
                        value = Integer.valueOf(String.valueOf(jsonArray1.getJSONObject(3).get("percentage"))); //Nivel 4 del histórico
                        pdi4.setProgress(Integer.valueOf(value), true);
                        coloreaNum(Integer.valueOf(value), n4i);
                        value = Integer.valueOf(String.valueOf(jsonArray1.getJSONObject(4).get("percentage"))); //Nivel 5 del histórico
                        pdi5.setProgress(Integer.valueOf(value), true);
                        coloreaNum(Integer.valueOf(value), n5i);

                        jsonArray1 = jsonArray.getJSONArray(2); //Array histórico de la persona
                        value = Integer.valueOf(String.valueOf(jsonArray1.getJSONObject(0).get("percentage"))); //Nivel 1 del histórico
                        pdg1.setProgress(Integer.valueOf(value), true);
                        coloreaNum(Integer.valueOf(value), n1g);
                        value = Integer.valueOf(String.valueOf(jsonArray1.getJSONObject(1).get("percentage"))); //Nivel 2 del histórico
                        pdg2.setProgress(Integer.valueOf(value), true);
                        coloreaNum(Integer.valueOf(value), n2g);
                        value = Integer.valueOf(String.valueOf(jsonArray1.getJSONObject(2).get("percentage"))); //Nivel 3 del histórico
                        pdg3.setProgress(Integer.valueOf(value), true);
                        coloreaNum(Integer.valueOf(value), n3g);
                        value = Integer.valueOf(String.valueOf(jsonArray1.getJSONObject(3).get("percentage"))); //Nivel 4 del histórico
                        pdg4.setProgress(Integer.valueOf(value), true);
                        coloreaNum(Integer.valueOf(value), n4g);
                        value = Integer.valueOf(String.valueOf(jsonArray1.getJSONObject(4).get("percentage"))); //Nivel 5 del histórico
                        pdg5.setProgress(Integer.valueOf(value), true);
                        coloreaNum(Integer.valueOf(value), n5g);


                       // usernametxt.setText(user);


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

    public void coloreaNum(int i, TextView numtext) {

        int valor=0;
        for (valor=0; valor<=i; valor++){
            if (valor == 0) {
                numtext.setText(String.valueOf(valor));
                numtext.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorcero));
            } else if (0 < valor && valor < 50) {
                numtext.setText(String.valueOf(valor));
                numtext.setTextColor(ContextCompat.getColor(getActivity(), R.color.colormal));
            } else if (50 <= valor && valor < 70) {
                numtext.setText(String.valueOf(valor));
                numtext.setTextColor(ContextCompat.getColor(getActivity(), R.color.colornormal));
            } else if (70 <= valor && valor < 85) {
                numtext.setText(String.valueOf(valor));
                numtext.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorbien));
            } else if (85 <= valor && valor <= 100) {
                numtext.setText(String.valueOf(valor));
                numtext.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorgloria));
            }
        }
    }

}
