package com.gmissio.provisionamentotriway.eg8120l;

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


public class Provisionamento8120l extends AppCompatActivity {


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
        setContentView(R.layout.activity_provisionamento8120l);

        nextButton = findViewById(R.id.buttonnext_sem_wifi);
        nextButton.setText(statusButton);
        mContext = getApplicationContext();
        mActivity = Provisionamento8120l.this;

        mRootLayout = findViewById(R.id.root_layout_sem_wifi);
        mWebView = findViewById(R.id.web_view_sem_wifi);

        String url = "http://192.168.18.1/html/bbsp/layer3/layer3.asp";//192.16.0.1

        mWebView.loadUrl(url);
        mWebView.getSettings().setJavaScriptEnabled(true);

        final String js = "javascript:" +
                "document.getElementById('txt_Username').value='Epadmin';" +
                "document.getElementById('txt_Password').value='adminEp';" +
                "SubmitForm();" +
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
                        statusButton = "ACESSO REMOTO";
                        nextButton.setText(statusButton);
                        RemoteAcess();
                        statusScript++;
                        break;
                    case 2:
                        statusButton = "CONFIGURAÇÃO WAN";
                        nextButton.setText(statusButton);
                        WanConfiguration();
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
                        statusButton = "HABILITAR LOGS";
                        nextButton.setText(statusButton);
                        HabilitarLogs1();
                        statusScript++;
                        break;
                    case 6:
                        statusButton = "HABILITAR LOGS";
                        nextButton.setText(statusButton);
                        HabilitarLogs2();
                        statusScript++;
                        break;
                    case 7:
                        statusButton = "SALVAR CONFIGURAÇÕES";
                        nextButton.setText(statusButton);
                        SalvarConfiguracoes();
                        statusScript++;
                        break;
//                    default:
//                        nextButton.setText(statusButton);
//                        statusButton = "fim";
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
                        15000);
                break;
            case 2:
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
                        28000);
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
                        10000);
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
                        8000);
                break;
            case 6:
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
                        10000);
                break;
            case 7:
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
        mActivity = Provisionamento8120l.this;

        mRootLayout = findViewById(R.id.root_layout_sem_wifi);
        mWebView = findViewById(R.id.web_view_sem_wifi);

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

                "radioPolicy = document.getElementById('PriorityPolicy2'); radioPolicy.checked = true; OnChangeUI(this);" +//teste

                "document.getElementById('UserName').value='"+username+"';" +
                "document.getElementById('Password').value='"+password+"';" +

             //   "checkLcp = document.getElementById('LcpEchoReqCheck'); checkLcp.checked = true;" +

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
        mActivity = Provisionamento8120l.this;

        mRootLayout = findViewById(R.id.root_layout_sem_wifi);
        mWebView = findViewById(R.id.web_view_sem_wifi);

        String url = "http://192.168.18.1/html/bbsp/acl/acl.asp";//192.16.0.1

        mWebView.loadUrl(url);
        mWebView.getSettings().setJavaScriptEnabled(true);

        final String js = "javascript: " +
                "checkHttp = document.getElementById('httpwan'); checkHttp.checked = true;" +
                "document.getElementById('bttnApply').click();" +
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
        mActivity = Provisionamento8120l.this;

        mRootLayout = findViewById(R.id.root_layout_sem_wifi);
        mWebView = findViewById(R.id.web_view_sem_wifi);

        String url = "http://192.168.18.1/html/bbsp/layer3/layer3.asp";//192.16.0.1

        mWebView.loadUrl(url);
        mWebView.getSettings().setJavaScriptEnabled(true);

        final String js = "javascript: " +
                "checLan1 = document.getElementById('cb_Lan1'); checLan1.checked = true;" +
                "checLan2 = document.getElementById('cb_Lan2'); checLan2.checked = true;" +
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
        mActivity = Provisionamento8120l.this;

        mRootLayout = findViewById(R.id.root_layout_sem_wifi);
        mWebView = findViewById(R.id.web_view_sem_wifi);

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
        mActivity = Provisionamento8120l.this;

        mRootLayout = findViewById(R.id.root_layout_sem_wifi);
        mWebView = findViewById(R.id.web_view_sem_wifi);

        String url = "http://192.168.18.1/html/bbsp/lanaddress/lanaddress.asp";//192.16.0.1

        mWebView.loadUrl(url);
        mWebView.getSettings().setJavaScriptEnabled(true);

        final String js = "javascript: " +
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
        mActivity = Provisionamento8120l.this;

        mRootLayout = findViewById(R.id.root_layout_sem_wifi);
        mWebView = findViewById(R.id.web_view_sem_wifi);

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
        mActivity = Provisionamento8120l.this;

        mRootLayout = findViewById(R.id.root_layout_sem_wifi);
        mWebView = findViewById(R.id.web_view_sem_wifi);

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
        mActivity = Provisionamento8120l.this;

        mRootLayout = findViewById(R.id.root_layout_sem_wifi);
        mWebView = findViewById(R.id.web_view_sem_wifi);

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
