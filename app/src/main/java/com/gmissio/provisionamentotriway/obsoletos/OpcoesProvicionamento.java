package com.gmissio.provisionamentotriway.obsoletos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gmissio.provisionamentotriway.conectividade.Conectividade;
import com.gmissio.provisionamentotriway.R;
import com.gmissio.provisionamentotriway.diologs.DiologCidade;
import com.gmissio.provisionamentotriway.diologs.DiologProvisionamento;
import com.gmissio.provisionamentotriway.diologs.DiologProvisionamentoEmergencia;
import com.gmissio.provisionamentotriway.diologs.DiologSsidPassword;
import com.gmissio.provisionamentotriway.eg8145v5.Provisionamento8145v5;
import com.gmissio.provisionamentotriway.eg8145v5.SsidPassword;


public class OpcoesProvicionamento extends AppCompatActivity implements DiologProvisionamento.DiologProvisionamentoListener, DiologSsidPassword.DiologProvisionamentoListener, DiologCidade.TesteDiologListener, DiologProvisionamentoEmergencia.DiologProvisionamentoListener {

    private Button emergence;
    private  String user;
    private  String pass;
    private TextView textView;
    private Button teste;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.obsoleto_activity_opcoes_provisionamento8145v5);

        textView = findViewById(R.id.textView);
        emergence = findViewById(R.id.button3);

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


    }
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

    @Override
    public void aplicarCidade(int vlan) {
        try{
            String vlanid = String.valueOf(vlan);
            Intent intent = new Intent(this, Provisionamento8145v5.class);
            intent.putExtra("username", user);
            intent.putExtra("password", pass);
            intent.putExtra("vlan", vlanid);
            startActivity(intent);
        }catch (Exception e){
            Toast t = Toast.makeText(getApplicationContext(),"ERRO AO CONVERTER VLAN",Toast.LENGTH_SHORT);//.show();
            t.show();
        }
       // textView.setText(String.valueOf(vlan));
    }

    @Override
    public void aplicarTexts(String username, String password, String vlan) {
        if (username.isEmpty() || password.isEmpty() || vlan.isEmpty()){
            Toast t = Toast.makeText(getApplicationContext(),"CAMPOS INVALIDOS",Toast.LENGTH_SHORT);//.show();
            t.show();
        }else {
            Intent intent = new Intent(this, Provisionamento8145v5.class);
            intent.putExtra("username", username);
            intent.putExtra("password", password);
            intent.putExtra("vlan", vlan);
            startActivity(intent);
        }
    }
}
