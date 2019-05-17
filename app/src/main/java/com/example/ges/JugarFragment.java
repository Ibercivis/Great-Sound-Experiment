package com.example.ges;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;


/**
 * A simple {@link Fragment} subclass.
 */
public class JugarFragment extends Fragment {


    public JugarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_jugar, container, false);

        Button boton = view.findViewById(R.id.botonjugar);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                setFragment(new FragmentNivel1());
        transi1();
            }
        });

        return view;
    }

    void setFragment(Fragment fragment){

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();

    }

    public void transi1(){

        Intent intent = new Intent(getContext(), Nivel1.class);

        startActivity(intent);

    }

}
