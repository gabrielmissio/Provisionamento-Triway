package com.gmissio.provisionamentotriway.eg8145v5;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;

import com.gmissio.provisionamentotriway.R;

import androidx.appcompat.app.AppCompatActivity;

public class SsidPassword extends AppCompatActivity {

    private Context mContext;
    private Activity mActivity;
    public int statusScript = 0;
    public String statusButton = "CONFIGURAR REDE 5.8";
    private LinearLayout mRootLayout;
    private WebView mWebView;
    private Button nextButton;

    private String ssid2;
    private String password2;
    private String ssid5;
    private String password5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssid_password);

        nextButton = findViewById(R.id.buttonnext_ssid_password);
        nextButton.setText(statusButton);
        mContext = getApplicationContext();
        mActivity = SsidPassword.this;

        mRootLayout = findViewById(R.id.root_layout_ssid_password);
        mWebView = findViewById(R.id.web_view_ssid_password);

        String url = "http://192.168.18.1/html/bbsp/wan/wan.asp";//192.16.0.1

        mWebView.loadUrl(url);
        mWebView.getSettings().setJavaScriptEnabled(true);

        final String js = "javascript:" +
                "function a (){" +
                "document.getElementById('txt_Username').value='Epadmin';" +
                "setTimeout(b, 0);" +
                "}" +
                "function b (){" +
                "document.getElementById('txt_Password').value='adminEp';" +
                "setTimeout(c, 0);" +
                "}" +
                "function c (){" +
                "document.getElementById('loginbutton').click();" +
                //     "setTimeout(test, 5000);" +
                "}" +
                "a();";


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
        ssid2 = extras.getString("ssid2");
        password2 = extras.getString("password2");
        ssid5 = extras.getString("ssid5");
        password5 = extras.getString("password5");


    }//fim do oncreate

    public void nextButtonSsid(View view){

        switch (statusScript){
            case 0:
                statusButton = "CONFIGURAR REDE 2.4";
                nextButton.setText(statusButton);
                SsidPassword5();
                statusScript++;
                break;
            case 1:
                statusButton = "FIM";
                nextButton.setText(statusButton);
                SsidPassword2();
                statusScript++;
                break;
            default:
                nextButton.setText(statusButton);
                statusButton = "FIM";
        }

        // Provision();

    }



    public void SsidPassword2(){
        /**/
        mContext = getApplicationContext();
        mActivity = SsidPassword.this;

        mRootLayout = findViewById(R.id.root_layout_ssid_password);
        mWebView = findViewById(R.id.web_view_ssid_password);

        String url = "http://192.168.18.1/html/amp/wlanbasic/WlanBasic.asp?2G";//192.16.0.1

        mWebView.loadUrl(url);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient());


        final String js = "javascript: " +
                "document.getElementById('wlSsid').value='"+ssid2+"';" +
                "document.getElementById('wlWpaPsk').value='"+password2+"';" +
                "document.getElementById('btnApplySubmit').click();" +
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

    public void SsidPassword5(){
        /**/
        mContext = getApplicationContext();
        mActivity = SsidPassword.this;

        mRootLayout = findViewById(R.id.root_layout_ssid_password);
        mWebView = findViewById(R.id.web_view_ssid_password);

        String url = "http://192.168.18.1/html/amp/wlanbasic/WlanBasic.asp?5G";//192.16.0.1

        mWebView.loadUrl(url);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient());


        final String js = "javascript: " +
                "document.getElementById('wlSsid').value='"+ssid5+"';" +
                "document.getElementById('wlWpaPsk').value='"+password5+"';" +
                "document.getElementById('btnApplySubmit').click();" +
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
