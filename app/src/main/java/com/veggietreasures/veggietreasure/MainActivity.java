package com.veggietreasures.veggietreasure;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity {

    private AdView mAdView;

    private InterstitialAd mInterstitialAd;

    WebView webView;

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
          if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();

                    }
                });
            }
            webView.goBack();
        }else{
           if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();

                    }
                });
            }
            //buildDialog(MainActivity.this,"Thanks for visiting us","Thanks").show();
            super.onBackPressed();
        }



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Check if device is connected to internet
        if(!isDeviceConnected(MainActivity.this))
            buildDialog(MainActivity.this,"Please check your connections, Mobile data or wifi to use this app. Press ok the exit.","OK").show();
        else{
            Toast.makeText(MainActivity.this,"Welcome to Veggie Treasures", Toast.LENGTH_SHORT).show();
            setContentView(R.layout.activity_main);

            webView=(WebView) findViewById(R.id.webView);
            WebSettings webSettings=webView.getSettings();

            webSettings.setJavaScriptEnabled(true);

            //For WebView site performance
            webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
            webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            webView.getSettings().setAppCacheEnabled(true);
            webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            webSettings.setDomStorageEnabled(true);
            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
            webSettings.setUseWideViewPort(true);
            webSettings.setSavePassword(true);
            webSettings.setSaveFormData(true);
            webSettings.setEnableSmoothTransition(true);
            //For WebView site performance

            webView.setWebViewClient(new com.veggietreasures.veggietreasure.VtWebClient(){
                @Override
                public void onPageFinished(WebView view, String url) {
                    //hide loading image
                    findViewById(R.id.progressBar1).setVisibility(View.GONE);
                    //show webview
                    findViewById(R.id.webView).setVisibility(View.VISIBLE);
                    super.onPageFinished(view, url);
                }}
            );

            webView.loadUrl("http://www.veggietreasures.com");



            //Ad units - Banner
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //Ad units - Interstitial

            // Prepare the Interstitial Ad
        mInterstitialAd = new InterstitialAd(MainActivity.this);
        // Insert the Ad Unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.vt_InterstitialAdunit));

        mInterstitialAd.loadAd(adRequest);

            // Prepare an Interstitial Ad Listener
        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                // Call displayInterstitial() function
                displayInterstitial();
            }
        });
        }
    }


    //Function to show Interstitial ads
    public void displayInterstitial() {
    // If Ads are loaded, show Interstitial else show nothing.
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }


    //Function to check netowrk conectivity
    public boolean isDeviceConnected(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(null!=networkInfo && networkInfo.isConnectedOrConnecting()){
            android.net.NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if((null!=mobile && mobile.isConnectedOrConnecting()) || (null!=wifi && wifi.isConnectedOrConnecting()) )
                return true;
            else
                return false;
        }else
            return false;
    }

    //Function to show Alert when device not connected
    public AlertDialog.Builder buildDialog(Context context, String msg, final String buttonCaption){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setMessage(msg)
        .setIcon(R.mipmap.ic_launcher)
        .setPositiveButton(buttonCaption, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                if(buttonCaption.equals("Thanks"))
                   Toast.makeText(MainActivity.this, "Do visit again",Toast.LENGTH_SHORT).show();
                if(buttonCaption.equals("OK"))
                    finish();
            }

        });
        return builder;


    }


}
