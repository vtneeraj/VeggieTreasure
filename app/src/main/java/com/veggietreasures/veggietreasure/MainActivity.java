package com.veggietreasures.veggietreasure;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT=4000;

    WebView webView;

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }else{
            super.onBackPressed();
            /*AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder
                    .setTitle("Exiting the Application")
                    .setMessage("Are you sure?")
                    .setIcon(R.mipmap.ic_launcher)
                    //.setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //Yes button clicked, do something
                            Toast.makeText(MainActivity.this, "Yes button pressed",
                                    Toast.LENGTH_SHORT).show();
                            finish();

                        }
                    })
                    .setNegativeButton("No", null)						//Do nothing on no
                    .show();*/
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView=(WebView) findViewById(R.id.webView);

        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        WebSettings webSettings=webView.getSettings();

        webSettings.setJavaScriptEnabled(true);
        //For WebView site performance
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);
        webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);
        webSettings.setEnableSmoothTransition(true);
        //For WebView site performance

        webView.setWebViewClient(new WebViewClient());

        webView.loadUrl("http://www.veggietreasures.com");
    }


}
