package com.gmissio.provisionamentotriway.conectividade;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.gmissio.provisionamentotriway.R;

public class Conectividade extends AppCompatActivity {

    private Context mContext;
    private Activity mActivity;
    public int statusScript = 0;
    public String statusButton = "PROVISIONAR";
    private LinearLayout mRootLayout;
    private WebView mWebView;
    private Button nextButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conectividade);

        mContext = getApplicationContext();
        mActivity = Conectividade.this;

        mRootLayout = findViewById(R.id.root_layout_conectividade);
        mWebView = findViewById(R.id.web_view_conectividade);

        String url = "http://192.168.18.1/html/bbsp/wan/wan.asp";//192.16.0.1

        mWebView.loadUrl(url);
        mWebView.getSettings().setJavaScriptEnabled(true);

        final String js = "javascript:" +

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


    }
}