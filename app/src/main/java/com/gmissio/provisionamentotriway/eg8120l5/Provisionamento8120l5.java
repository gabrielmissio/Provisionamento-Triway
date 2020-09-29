package com.gmissio.provisionamentotriway.eg8120l5;

/*
document.querySelector('#ForbidLedId #LedSwitchEnable').click();
* */

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gmissio.provisionamentotriway.R;

//import android.support.v7.app.AppCompatActivity;


public class Provisionamento8120l5 extends AppCompatActivity {


    private Context mContext;
    private Activity mActivity;
    public int statusScript = 0;
    public String statusButton = "PROVISIONAR";
    private LinearLayout mRootLayout;
    private WebView mWebView;
    private Button nextButton;

    private String username;
    private String password;
    private String vlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provisionamento8120l5);

        nextButton = findViewById(R.id.buttonnext_8120l5);
        nextButton.setText(statusButton);
        mContext = getApplicationContext();
        mActivity = Provisionamento8120l5.this;

        mRootLayout = findViewById(R.id.root_layout_8120l5);
        mWebView = findViewById(R.id.web_view_8120l5);

        String url = "http://192.168.18.1/html/bbsp/wan/wan.asp";//192.16.0.1

        mWebView.loadUrl(url);
        mWebView.getSettings().setJavaScriptEnabled(true);

        final String js = "javascript:" +
                "document.getElementById('txt_Username').value='Epadmin';" +
                "document.getElementById('txt_Password').value='adminEp';" +
                "document.getElementById('loginbutton').click();" +
                "";


        mWebView.setWebViewClient(new WebViewClient(){
            /*
                void onPageFinished (WebView view, String url)
                    Notify the host application that a page has finished loading. This method is
                    called only for main frame. When onPageFinished() is called, the rendering
                    picture may not be updated yet. To get the notification for the new Picture,
                    use onNewPicture(WebView, Picture).

                Parameters
                    view WebView : The WebView that is initiating the callback.
                    url String : The url of the page.
            */
            public void onPageFinished(WebView view, String url){
                if(Build.VERSION.SDK_INT >= 19){
                    view.evaluateJavascript(js, new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String s) {
                        }
                    });
                }

            }
        });

        //setar username, password a vlan id
        Bundle extras = getIntent().getExtras();
        username = extras.getString("username");
        password = extras.getString("password");
        vlan = extras.getString("vlan");

        nextButton.setEnabled(false);
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        nextButton.setEnabled(true);
                    }
                },
                3000);


        nextButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Toast t = Toast.makeText(getApplicationContext(),"PROVISIONAMENTO POR PARTES",Toast.LENGTH_SHORT);//
                t.show();

                switch (statusScript){
                    case 0:
                        statusButton = "HABILITAR PORTAS LAN";
                        nextButton.setText(statusButton);
                        LanPorts();
                        statusScript++;
                        break;
                    case 1:
                        statusButton = "CONFIGURAÇÃO WAN";
                        nextButton.setText(statusButton);
                        WanConfiguration();
                        statusScript++;
                        break;
                    case 2:
                        statusButton = "ACESSO REMOTO";
                        nextButton.setText(statusButton);
                        RemoteAcess();
                        statusScript++;
                        break;
                    case 3:
                        statusButton = "DHCP";
                        nextButton.setText(statusButton);
                        DhcpOnt();
                        statusScript++;
                        break;
                    case 4:
                        statusButton = "DHCP IPV6";
                        nextButton.setText(statusButton);
                        DhcpOntv6();
                        statusScript++;
                        break;
                    case 5:
                        statusButton = "HABILITAR ACESSO";
                        nextButton.setText(statusButton);
                        RemoteAcessCombo();
                        statusScript++;
                        break;
                    case 6:
                        statusButton = "HABILITAR LOGS";
                        nextButton.setText(statusButton);
                        HabilitarLogs1();
                        statusScript++;
                        break;
                    case 7:
                        statusButton = "HABILITAR LOGS";
                        nextButton.setText(statusButton);
                        HabilitarLogs2();
                        statusScript++;
                        break;
                    case 8:
                        statusButton = "SALVAR CONFIGURAÇÕES";
                        nextButton.setText(statusButton);
                        SalvarConfiguracoes();
                        statusScript++;
                        break;
                    default:
                        nextButton.setText(statusButton);
                        statusButton = "fim";
                }


                return true;
            }
        });
    }//fim onCreate

    public void provicionamentoRapido(){
        nextButton.setEnabled(false);
        switch (statusScript){
            case 0:
                statusButton = "HABILITAR PORTAS LAN";
                nextButton.setText(statusButton);
                LanPorts();
                statusScript++;
                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                provicionamentoRapido();
                            }
                        },
                        6000);
                break;
            case 1:
                statusButton = "CONFIGURAÇÃO WAN";
                nextButton.setText(statusButton);
                WanConfiguration();
                statusScript++;
                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                provicionamentoRapido();
                            }
                        },
                        22000);
                break;
            case 2:
                statusButton = "ACESSO REMOTO";
                nextButton.setText(statusButton);
                RemoteAcess();
                statusScript++;
                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                provicionamentoRapido();
                            }
                        },
                        35000);
                break;
            case 3:
                statusButton = "DHCP";
                nextButton.setText(statusButton);
                DhcpOnt();
                statusScript++;
                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                provicionamentoRapido();
                            }
                        },
                        8000);
                break;
            case 4:
                statusButton = "DHCP IPV6";
                nextButton.setText(statusButton);
                DhcpOntv6();
                statusScript++;
                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                provicionamentoRapido();
                            }
                        },
                        12000);
                break;
            case 5:
                statusButton = "HABILITAR ACESSO";
                nextButton.setText(statusButton);
                RemoteAcessCombo();
                statusScript++;
                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                provicionamentoRapido();
                            }
                        },
                        15000);
                break;
            case 6:
                statusButton = "HABILITAR LOGS";
                nextButton.setText(statusButton);
                HabilitarLogs1();
                statusScript++;
                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                provicionamentoRapido();
                            }
                        },
                        12000);
                break;
            case 7:
                statusButton = "HABILITAR LOGS";
                nextButton.setText(statusButton);
                HabilitarLogs2();
                statusScript++;
                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                provicionamentoRapido();
                            }
                        },
                        12000);
                break;
            case 8:
                statusButton = "SALVAR CONFIGURAÇÕES";
                nextButton.setText(statusButton);
                SalvarConfiguracoes();
                statusScript++;
                break;
            default:
                nextButton.setText(statusButton);
                statusButton = "fim";
        }
    }
    public void nextButton(View view){
        provicionamentoRapido();
    }

    public void WanConfiguration(){
        /**/
        mContext = getApplicationContext();
        mActivity = Provisionamento8120l5.this;

        mRootLayout = findViewById(R.id.root_layout_8120l5);
        mWebView = findViewById(R.id.web_view_8120l5);

        String url = "http://192.168.18.1/html/bbsp/wan/wan.asp";//192.16.0.1

        mWebView.loadUrl(url);
        mWebView.getSettings().setJavaScriptEnabled(true);

        final String js = "javascript: " +
                "clickAdd('wanInstTable_head');" +
                "document.getElementById('VlanId').value='"+vlan+"';" +
                "radioEncapMode = document.getElementById('EncapMode2'); radioEncapMode.checked = true; OnChangeEncapMode(this);" +
                "comboProtocolType = document.getElementById('ProtocolType'); comboProtocolType.selectedIndex = 2;" +
                "comboWanMode = document.getElementById('WanMode');" +
                "comboServiceType = document.getElementById('ServiceList'); comboServiceType.selectedIndex = 2;" +
                "comboPolicy = document.getElementById('PriorityPolicy'); comboPolicy.selectedIndex = 1;" +
                "document.getElementById('UserName').value='"+username+"';" +
                "document.getElementById('Password').value='"+password+"';" +
                "document.getElementById('ButtonApply').click();" +
                "";

        mWebView.setWebViewClient(new WebViewClient(){

            public void onPageFinished(WebView view, String url){
                if(Build.VERSION.SDK_INT >= 19){
                    view.evaluateJavascript(js, new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String s) {
                        }
                    });
                }

            }
        });
    }

    public void RemoteAcess(){
        /**/
        mContext = getApplicationContext();
        mActivity = Provisionamento8120l5.this;

        mRootLayout = findViewById(R.id.root_layout_8120l5);
        mWebView = findViewById(R.id.web_view_8120l5);

        String url = "http://192.168.18.1/html/bbsp/portacl/newacl.asp";//192.16.0.1

        mWebView.loadUrl(url);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient());


        final String js = "javascript: " +
               // "checkAcess = document.getElementById('portaclwhite').checked = true;" +
                "clickAdd('PortAclConfigList_head');" +
                    "comboPortType = document.getElementById('PortType'); comboPortType.selectedIndex = 1;" +
                    "document.getElementById('priority').value='1';" +
                    "checkTelnet = document.getElementById('Protocol1'); checkTelnet.checked = true;" +
                    "checkHttp = document.getElementById('Protocol2'); checkHttp.checked = true;" +
                    "checkSsh = document.getElementById('Protocol3'); checkSsh.checked = true;" +
                    "document.getElementById('btnApply_ex').click();" +
                "";

        mWebView.setWebViewClient(new WebViewClient(){

            public void onPageFinished(WebView view, String url){
                if(Build.VERSION.SDK_INT >= 19){
                    view.evaluateJavascript(js, new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String s) {
                        }
                    });
                }

            }
        });
    }

    public void RemoteAcessCombo(){
        /**/
        mContext = getApplicationContext();
        mActivity = Provisionamento8120l5.this;

        mRootLayout = findViewById(R.id.root_layout_8120l5);
        mWebView = findViewById(R.id.web_view_8120l5);

        String url = "http://192.168.18.1/html/bbsp/portacl/newacl.asp";//192.16.0.1

        mWebView.loadUrl(url);
        mWebView.getSettings().setJavaScriptEnabled(true);

        final String js = "javascript: " +
                "checkAcess = document.getElementById('portaclwhite').checked = true;" +
                "EnablePortAclForm(this);"+
                "";

        mWebView.setWebViewClient(new WebViewClient(){

            public void onPageFinished(WebView view, String url){
                if(Build.VERSION.SDK_INT >= 19){
                    view.evaluateJavascript(js, new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String s) {
                        }
                    });
                }

            }
        });
    }

    public void LanPorts(){
        /**/
        mContext = getApplicationContext();
        mActivity = Provisionamento8120l5.this;

        mRootLayout = findViewById(R.id.root_layout_8120l5);
        mWebView = findViewById(R.id.web_view_8120l5);

        String url = "http://192.168.18.1/html/bbsp/layer3/layer3.asp";//192.16.0.1

        mWebView.loadUrl(url);
        mWebView.getSettings().setJavaScriptEnabled(true);

        final String js = "javascript: " +
                "checLan1 = document.getElementById('cb_Lan1'); checLan1.checked = true;" +
                "checLan2 = document.getElementById('cb_Lan2'); checLan2.checked = true;" +
//                "checLan3 = document.getElementById('cb_Lan3'); checLan3.checked = true;" +
//                "checLan4 = document.getElementById('cb_Lan4'); checLan4.checked = true;" +
                "document.getElementById('Apply').click();" +
                "";

        mWebView.setWebViewClient(new WebViewClient(){

            public void onPageFinished(WebView view, String url){
                if(Build.VERSION.SDK_INT >= 19){
                    view.evaluateJavascript(js, new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String s) {
                        }
                    });
                }

            }
        });
    }

    public void DhcpOnt(){
        /**/
        mContext = getApplicationContext();
        mActivity = Provisionamento8120l5.this;

        mRootLayout = findViewById(R.id.root_layout_8120l5);
        mWebView = findViewById(R.id.web_view_8120l5);

        String url = "http://192.168.18.1/html/bbsp/dhcpservercfg/dhcp2.asp";//192.16.0.1

        mWebView.loadUrl(url);
        mWebView.getSettings().setJavaScriptEnabled(true);

        final String js = "javascript: " +
                "checkDhcpDelay = document.getElementById('dhcpL2relay'); checkDhcpDelay.checked = false;" +
                "checkDhcpOption = document.getElementById('dhcpMainOption125'); checkDhcpOption.checked = false;" +
                "comboLeased = document.getElementById('maindhcpLeasedTimeFrag'); comboLeased.selectedIndex = 1;" +//
                "document.getElementById('dnsMainPri').value='177.22.81.228';" +
                "document.getElementById('dnsMainSec').value='177.22.83.228';" +
                "ApplyConfig();" +
                "";

        mWebView.setWebViewClient(new WebViewClient(){

            public void onPageFinished(WebView view, String url){
                if(Build.VERSION.SDK_INT >= 19){
                    view.evaluateJavascript(js, new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String s) {
                        }
                    });
                }

            }
        });
    }
    public void DhcpOntv6(){
        /**/
        mContext = getApplicationContext();
        mActivity = Provisionamento8120l5.this;

        mRootLayout = findViewById(R.id.root_layout_8120l5);
        mWebView = findViewById(R.id.web_view_8120l5);

        String url = "http://192.168.18.1/html/bbsp/lanaddress/lanaddress.asp";//192.16.0.1

        mWebView.loadUrl(url);
        mWebView.getSettings().setJavaScriptEnabled(true);

        final String js = "javascript: " +
                "comboPreFixMode = document.getElementById('PreFixModeList');" +
                "comboParentPrefix = document.getElementById('WanNameList'); comboParentPrefix.selectedIndex = 1;" +
                "comboDnsSource = document.getElementById('Ipv6landnsList'); comboDnsSource.selectedIndex = 1;" +
                "comboWanName = document.getElementById('Ipv6wanname'); comboWanName.selectedIndex = 1;" +
                "comboAllocationMode = document.getElementById('ResourceAllocModeList'); comboAllocationMode.selectedIndex = 1;" +
                "comboUlaMode = document.getElementById('ULAmodeDEFHIDE'); comboUlaMode.selectedIndex = 2;" +
                "document.getElementById('ButtonApply').click();" +

                //     "ApplyConfig();" +
                "";

        mWebView.setWebViewClient(new WebViewClient(){

            public void onPageFinished(WebView view, String url){
                if(Build.VERSION.SDK_INT >= 19){
                    view.evaluateJavascript(js, new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String s) {
                        }
                    });
                }

            }
        });
    }

    public void SalvarConfiguracoes(){
        /**/
        mContext = getApplicationContext();
        mActivity = Provisionamento8120l5.this;

        mRootLayout = findViewById(R.id.root_layout_8120l5);
        mWebView = findViewById(R.id.web_view_8120l5);

        String url = "http://192.168.18.1/html/ssmp/cfgfile/cfgfile.asp";//192.16.0.1

        mWebView.loadUrl(url);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient());


        final String js = "javascript: " +
                "document.getElementById('btnsaveandreboot').click();" +
                //   "confirm();" +
                "";

        mWebView.setWebViewClient(new WebViewClient(){

            public void onPageFinished(WebView view, String url){
                if(Build.VERSION.SDK_INT >= 19){
                    view.evaluateJavascript(js, new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String s) {
                        }
                    });
                }

            }
        });
    }

    public void HabilitarLogs1(){
        /**/
        mContext = getApplicationContext();
        mActivity = Provisionamento8120l5.this;

        mRootLayout = findViewById(R.id.root_layout_8120l5);
        mWebView = findViewById(R.id.web_view_8120l5);

        String url = "http://192.168.18.1/html/ssmp/sntp/sntp.asp";//192.16.0.1

        mWebView.loadUrl(url);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient());


        final String js = "javascript: " +
                "check = document.getElementById('ntpEnabled');" +
                "if(check.checked == false){" +
                "check.click();" +
                "SubmitForm();" +
                "}"+
                //   "confirm();" +
                "";

        mWebView.setWebViewClient(new WebViewClient(){

            public void onPageFinished(WebView view, String url){
                if(Build.VERSION.SDK_INT >= 19){
                    view.evaluateJavascript(js, new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String s) {
                        }
                    });
                }

            }
        });
    }

    public void HabilitarLogs2(){
        /**/
        mContext = getApplicationContext();
        mActivity = Provisionamento8120l5.this;

        mRootLayout = findViewById(R.id.root_layout_8120l5);
        mWebView = findViewById(R.id.web_view_8120l5);

        String url = "http://192.168.18.1/html/ssmp/sntp/sntp.asp";//192.16.0.1

        mWebView.loadUrl(url);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient());


        final String js = "javascript: " +
                "comboPrimarySNTP = document.getElementById('ntpServer1'); comboPrimarySNTP.selectedIndex = 9;" +
                "comboSecondarySNTP = document.getElementById('ntpServer2'); comboSecondarySNTP.selectedIndex = 8;" +
                "comboWan = document.getElementById('WanName'); comboWan.selectedIndex = 1;" +
                "comboTimeZone = document.getElementById('cboTimeZone'); comboTimeZone.selectedIndex = 19;" +
                "SubmitForm();" +
                //   "confirm();" +
                "";

        mWebView.setWebViewClient(new WebViewClient(){

            public void onPageFinished(WebView view, String url){
                if(Build.VERSION.SDK_INT >= 19){
                    view.evaluateJavascript(js, new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String s) {
                        }
                    });
                }

            }
        });
    }


}
