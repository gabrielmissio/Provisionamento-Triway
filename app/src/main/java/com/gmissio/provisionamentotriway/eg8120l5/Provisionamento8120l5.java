package com.gmissio.provisionamentotriway.eg8120l5;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.ValueCallback;
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
        mActivity = com.gmissio.provisionamentotriway.eg8120l5.Provisionamento8120l5.this;

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
//        Bundle extras = getIntent().getExtras();
//        username = extras.getString("username");
//        password = extras.getString("password");
//        vlan = extras.getString("vlan");

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


                return true;
            }
        });

    }//fim onCreate


}
