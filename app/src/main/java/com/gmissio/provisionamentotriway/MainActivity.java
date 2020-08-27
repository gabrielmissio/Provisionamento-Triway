package com.gmissio.provisionamentotriway;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.gmissio.provisionamentotriway.eg8120l5.OpcoesProvisionamento8120l5;
import com.gmissio.provisionamentotriway.provisionamentocomwifi.OpcoesProvicionamento;
import com.gmissio.provisionamentotriway.provisionamentosemwifi.OpcoesProvicionamentoSemWifi;

public class MainActivity extends AppCompatActivity {

    private ImageView imageLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        try{
            setContentView(R.layout.activity_main);
            imageLogo = findViewById(R.id.imageLogo);
            try {
                imageLogo.setImageResource(R.drawable.triway_logo);
            }catch (Exception e){
                try {
                    imageLogo.setImageResource(R.drawable.ic_triway_logo1);
                }catch (Exception e1){

                }
            }//fim do try catch
        }catch (Exception a2){
            setContentView(R.layout.activity_main_error);
        }

       }//fim do onCreate

    public void OpcoesProvicionamento(View view){
        Intent intent = new Intent(this, OpcoesProvicionamento.class);
        startActivity(intent);
    }

    public void OpcoesProvicionamentoSemWifi(View view){
        Intent intent = new Intent(this, OpcoesProvicionamentoSemWifi.class);
        startActivity(intent);
    }

    public void OpcoesProvicionamentoEg8120l5(View view){
        Intent intent = new Intent(this, OpcoesProvisionamento8120l5.class);
        startActivity(intent);
    }


}
