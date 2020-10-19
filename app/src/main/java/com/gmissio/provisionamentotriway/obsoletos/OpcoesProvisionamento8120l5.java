package com.gmissio.provisionamentotriway.obsoletos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.gmissio.provisionamentotriway.conectividade.Conectividade;
import com.gmissio.provisionamentotriway.R;
import com.gmissio.provisionamentotriway.eg8120l5.Provisionamento8120l5;

public class OpcoesProvisionamento8120l5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.obsoleto_activity_opcoes_provisionamento8120l5);
    }

    public void RouterIPv4IPv6A(View view){
        Intent intent = new Intent(this, Provisionamento8120l5.class);
        startActivity(intent);
    }

    public void TestarConectividade(View view){
        Intent intent = new Intent(this, Conectividade.class);
        startActivity(intent);
    }

}