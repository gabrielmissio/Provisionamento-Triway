package com.gmissio.provisionamentotriway.eg8120l5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.gmissio.provisionamentotriway.Conectividade.Conectividade;
import com.gmissio.provisionamentotriway.R;

public class OpcoesProvisionamento8120l5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcoes_provisionamento8120l5);
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