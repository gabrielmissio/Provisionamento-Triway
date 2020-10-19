package com.gmissio.provisionamentotriway.diologs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class Modelo extends AppCompatDialogFragment {

    public int vlan = 1;
    private TesteDiologListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        builder.setTitle("MODELO");
        // add a radio button list
        String[] animals = {"EG8145V5 COM WIFI","EG8245W5-6T COM WIFI", "EG8120L SEM WIFI", "EG8120L5 SEM WIFI","EG8145A5 SEM WIFI", "EG8245H5 SEM WIFI"};
        final int checkedItem = 0; // boa vista
        builder.setSingleChoiceItems(animals, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        //EG8145V5
                        vlan = 1;
                        break;
                    case 1:
                        //EG8245W5-6T
                        vlan = 5;
                        break;
                    case 2:
                        //EG8120L
                        vlan = 2;
                        break;
                    case 3:
                        //EG8120L5
                        vlan = 3;
                        break;
                    case 4:
                        //EG8145A5
                        vlan = 6;
                        break;
                    case 5:
                        //EG8245H5
                        vlan = 4;
                        break;
                }
            }
        });

// add OK and Cancel buttons
        builder.setPositiveButton("CONFIRMAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.aplicarModelo(vlan);
            }
        });
        builder.setNegativeButton("CANCELAR", null);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (TesteDiologListener) context;

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public  interface TesteDiologListener{
        void aplicarModelo(int modelo);

    }


}
