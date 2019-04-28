package com.example.exp_05;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    WebView wb ;
    String url = "";
    EditText ed ;
    Button btnGo;
    Button btnBack;
    Button btnForward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wb = (WebView) findViewById(R.id.myWebView);
        ed = (EditText) findViewById(R.id.edtttUri);
        btnGo = (Button) findViewById(R.id.btnGo);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnForward = (Button) findViewById(R.id.btnForeward);

        if(getIntent() != null) {
            url = getIntent().getData().toString();
        }
        ed.setText(url);

        setWb();

        if("".equals(url) || url==null)
            wb.loadUrl("www.github.com");
        else
            wb.loadUrl(url);

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wb.loadUrl(ed.getText().toString());
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    wb.goBack();
                    ed.setText(wb.getUrl());
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    wb.goForward();
                    ed.setText(wb.getUrl());
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setWb() {

        wb.getSettings().setJavaScriptEnabled(true);
        wb.setWebViewClient(new WebViewClient(){
            //可以让url在当前应用的webView中加载，不再调用系统浏览器
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return  true;
            }
        });
    }

}
