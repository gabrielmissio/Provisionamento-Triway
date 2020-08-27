package com.gmissio.provisionamentotriway.diologs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.gmissio.provisionamentotriway.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DiologSsidPassword extends AppCompatDialogFragment {

    private DiologProvisionamentoListener listener;
    private EditText editTextSsid2;
    private EditText editTextPassword2;
    private EditText editTextSsid5;
    private EditText editTextPassword5;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_diolog_ssid_password, null);

        editTextSsid2 = view.findViewById(R.id.edit_ssid2);
        editTextSsid5 = view.findViewById(R.id.edit_ssid5);
        editTextPassword2 = view.findViewById(R.id.edit_password2);
        editTextPassword5 = view.findViewById(R.id.edit_password5);


        builder.setView(view)
                .setTitle("INFORMAÇÕES NECESSARIAS")
                .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("CONFIRMAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String ssid2 = editTextSsid2.getText().toString();
                        String password2 = editTextPassword2.getText().toString();
                        String ssid5 = editTextSsid5.getText().toString();
                        String password5 = editTextPassword5.getText().toString();

                        listener.pegarTextos(ssid2, password2, ssid5, password5);
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (DiologProvisionamentoListener) context;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public interface DiologProvisionamentoListener{
        void pegarTextos(String ssid2, String password2, String ssid5, String password5);
    }
}
