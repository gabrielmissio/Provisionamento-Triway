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

public class DiologProvisionamentoEmergencia extends AppCompatDialogFragment {

    private DiologProvisionamentoListener listener;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextVlan;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_diolog_emergencia, null);

        editTextUsername = view.findViewById(R.id.edit_username);
        editTextPassword = view.findViewById(R.id.edit_password);
        editTextVlan = view.findViewById(R.id.edit_vlan);

        builder.setView(view)
                .setTitle("INFORMAÇÕES PPPoE")
                .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("CONFIRMAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String username = editTextUsername.getText().toString();
                        String password = editTextPassword.getText().toString();
                        String vlan = editTextVlan.getText().toString();
                        listener.aplicarTexts(username, password, vlan);
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
        void aplicarTexts(String username, String password, String vlan);
    }
}
