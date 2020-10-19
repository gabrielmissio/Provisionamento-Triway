package com.gmissio.provisionamentotriway;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gmissio.provisionamentotriway.conectividade.Conectividade;
import com.gmissio.provisionamentotriway.diologs.DiologCidade;
import com.gmissio.provisionamentotriway.diologs.DiologProvisionamento;
import com.gmissio.provisionamentotriway.diologs.DiologProvisionamentoEmergencia;
import com.gmissio.provisionamentotriway.diologs.DiologSsidPassword;
import com.gmissio.provisionamentotriway.diologs.Modelo;
import com.gmissio.provisionamentotriway.eg8120l5.Provisionamento8120l5;
import com.gmissio.provisionamentotriway.eg8145v5.Provisionamento8145v5;
import com.gmissio.provisionamentotriway.eg8145v5.SsidPassword;
import com.gmissio.provisionamentotriway.eg8120l.Provisionamento8120l;

public class MainActivity extends AppCompatActivity implements DiologProvisionamento.DiologProvisionamentoListener, DiologSsidPassword.DiologProvisionamentoListener, DiologCidade.TesteDiologListener, DiologProvisionamentoEmergencia.DiologProvisionamentoListener, Modelo.TesteDiologListener {


    private Button emergence;
    private  String user;
    private  String pass;
    private TextView textView;
    private Button teste;
    private ImageView imageLogo;
    private String vlan;
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
        emergence = findViewById(R.id.button4);

        emergence.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast t = Toast.makeText(getApplicationContext(),"VLAN MANUAL",Toast.LENGTH_SHORT);//
                t.show();
                DiologProvisionamentoEmergencia diologProvisionamento = new DiologProvisionamentoEmergencia();
                diologProvisionamento.show(getSupportFragmentManager(), "diolog privicionamento emergencial");
                return true;
            }
        });


    }//fim do onCreate

    public void TestarConectividade(View view){
        Intent intent = new Intent(this, Conectividade.class);
        startActivity(intent);
    }

    public void ConfigSsidPassword(View view){
        DiologSsidPassword diologProvisionamento = new DiologSsidPassword();
        diologProvisionamento.show(getSupportFragmentManager(), "diolog ssid e password");

    }

    public void RouterIPv4IPv6(View view){
        DiologProvisionamento diologProvisionamento = new DiologProvisionamento();
        diologProvisionamento.show(getSupportFragmentManager(), "diolog provisionamento");

    }

    @Override
    public void aplicarTexts(String username, String password) {
        if (username.isEmpty() || password.isEmpty()){
            Toast t = Toast.makeText(getApplicationContext(),"CAMPOS INVALIDOS",Toast.LENGTH_SHORT);//.show();
            t.show();
        }else {
            user = username;
            pass = password;
            diologCidade();
//            intent.putExtra("username", username);
//            intent.putExtra("password", password);
//            intent.putExtra("vlan", vlan);
//            startActivity(intent);
        }

    }

    @Override
    public void pegarTextos(String ssid2, String password2, String ssid5, String password5) {
        Intent intent = new Intent(this, SsidPassword.class);
        if (ssid2.isEmpty() || password2.isEmpty() || ssid5.isEmpty() || password5.isEmpty()){
            Toast t = Toast.makeText(getApplicationContext(),"CAMPOS INVALIDOS",Toast.LENGTH_SHORT);//.show();
            t.show();
        }else {
            intent.putExtra("ssid2", ssid2);
            intent.putExtra("password2", password2);
            intent.putExtra("ssid5", ssid5);
            intent.putExtra("password5", password5);
            startActivity(intent);
        }
    }
    public void diologCidade() {
        DiologCidade diologProvisionamento = new DiologCidade();
        diologProvisionamento.show(getSupportFragmentManager(), "diolog cidade");
    }

    public void diologModelo() {
        Modelo diologProvisionamento = new Modelo();
        diologProvisionamento.show(getSupportFragmentManager(), "diolog modelo");
    }

    //diolog cidades
    @Override
    public void aplicarCidade(int vlan) {
        try{
            this.vlan = String.valueOf(vlan);

        }catch (Exception e){
            Toast t = Toast.makeText(getApplicationContext(),"ERRO AO CONVERTER VLAN",Toast.LENGTH_SHORT);//.show();
            t.show();
        }
        // textView.setText(String.valueOf(vlan));
        diologModelo();

    }

    //diolog vlan manual
    @Override
    public void aplicarTexts(String username, String password, String vlan) {
        if (username.isEmpty() || password.isEmpty()){
            Toast t = Toast.makeText(getApplicationContext(),"CAMPOS INVALIDOS",Toast.LENGTH_SHORT);//.show();
            t.show();
        }else {
            user = username;
            pass = password;
            this.vlan = vlan;
            diologModelo();
        }
    }

    //diolog modelo
    @Override
    public void aplicarModelo(int modelo) {

        switch (modelo){
            case 1:
                Toast t8145V5 = Toast.makeText(getApplicationContext(),"PROVISIONAMENTO EG8145V5",Toast.LENGTH_SHORT);//.show();
                t8145V5.show();
                Intent intent8145v5 = new Intent(this, Provisionamento8145v5.class);
                intent8145v5.putExtra("username", user);
                intent8145v5.putExtra("password", pass);
                intent8145v5.putExtra("vlan", this.vlan);
                startActivity(intent8145v5);
                break;
            case 2:
                Toast t8120l = Toast.makeText(getApplicationContext(),"PROVISIONAMENTO EG8120L",Toast.LENGTH_SHORT);//.show();
                t8120l.show();
                Intent intent8120l = new Intent(this, Provisionamento8120l.class);
                intent8120l.putExtra("username", user);
                intent8120l.putExtra("password", pass);
                intent8120l.putExtra("vlan", this.vlan);
                startActivity(intent8120l);
                break;
            case 3:
                Toast t8120l5 = Toast.makeText(getApplicationContext(),"PROVISIONAMENTO EG8120L5",Toast.LENGTH_SHORT);//.show();
                t8120l5.show();
                Intent intent8120l5 = new Intent(this, Provisionamento8120l5.class);
                intent8120l5.putExtra("username", user);
                intent8120l5.putExtra("password", pass);
                intent8120l5.putExtra("vlan", this.vlan);
                startActivity(intent8120l5);
                break;
            case 4:
                Toast t8245h5 = Toast.makeText(getApplicationContext(),"PROVISIONAMENTO EG8245H5",Toast.LENGTH_SHORT);//.show();
                t8245h5.show();
                Intent intent8245h5 = new Intent(this, Provisionamento8145v5.class);
                intent8245h5.putExtra("username", user);
                intent8245h5.putExtra("password", pass);
                intent8245h5.putExtra("vlan", this.vlan);
                startActivity(intent8245h5);
                break;
            case 5:
                Toast t8245w5 = Toast.makeText(getApplicationContext(),"PROVISIONAMENTO EG8245W5",Toast.LENGTH_SHORT);//.show();
                t8245w5.show();
                Intent intent8245w5 = new Intent(this, Provisionamento8145v5.class);
                intent8245w5.putExtra("username", user);
                intent8245w5.putExtra("password", pass);
                intent8245w5.putExtra("vlan", this.vlan);
                startActivity(intent8245w5);
                break;
        }

    }
}
