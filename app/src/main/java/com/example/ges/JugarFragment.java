package com.example.ges;


import android.app.ActivityOptions;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothHeadset;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


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
        int i=0;

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

    public void transi1() {

        AudioManager audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);



        if (audioManager.isWiredHeadsetOn() == true) {

            Intent intent = new Intent(getContext(), Nivel1.class);

            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());

        } else if(isBluetoothHeadsetConnected() == true){
            Intent intent = new Intent(getContext(), Nivel1.class);

            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
        }

     else {
            Toast toast1 = Toast.makeText(getActivity(), "Debes conectar tus auriculares para jugar", Toast.LENGTH_SHORT);
            toast1.setGravity(Gravity.CENTER, 0, 0);
            toast1.show();
        }
    }

    public static boolean isBluetoothHeadsetConnected() {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        return mBluetoothAdapter != null && mBluetoothAdapter.isEnabled()
                && mBluetoothAdapter.getProfileConnectionState(BluetoothHeadset.HEADSET) == BluetoothHeadset.STATE_CONNECTED;
    }

}
