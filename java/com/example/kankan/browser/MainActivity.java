package com.example.kankan.browser;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btngo,btnnext,btnBack,btnHistory,btnRefresh;
    private WebView webView;
    private EditText Url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btngo=findViewById(R.id.go);
        btnnext=findViewById(R.id.next);
        btnBack=findViewById(R.id.back);
        btnHistory=findViewById(R.id.history);
        btnRefresh=findViewById(R.id.refresh);

        webView=findViewById(R.id.webView);

        Url=findViewById(R.id.editText);

        webView.setWebViewClient(new OurViewClient());

        webView.getSettings().setJavaScriptEnabled(true);

        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        try
        {
            webView.loadUrl("https://www.google.com");
        }catch (Exception e)
        {
            Toast.makeText(this,"Error occure",Toast.LENGTH_SHORT).show();
        }

        btnRefresh.setOnClickListener(this);
        btnHistory.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnnext.setOnClickListener(this);
        btngo.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.go:
                String theWebside=Url.getText().toString();
                webView.loadUrl(theWebside);
                //hiding keyboard

                InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(Url.getWindowToken(),0);
                break;
            case R.id.next:

                if(webView.canGoForward())
                {
                    webView.goForward();
                }

                break;
            case R.id.back:

                if(webView.canGoBack())
                {
                    webView.goBack();
                }
                break;
            case R.id.history:
                webView.clearHistory();
                break;
            case R.id.refresh:
                webView.reload();
                break;
        }
    }
}
