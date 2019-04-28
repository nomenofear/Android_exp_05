package com.example.implicitintent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private EditText edt;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getView();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              goToMyBrowser();
            }
        });
    }

    public void getView() {
        btn = (Button) findViewById(R.id.btn_visit);
        edt = (EditText) findViewById(R.id.et_uri);
    }

    public void goToMyBrowser() {
        if(!edt.getText().toString().toLowerCase().startsWith("http://") || ! edt.getText().toString().toLowerCase().startsWith("https://")){
            uri = Uri.parse("http://" +  edt.getText().toString());
        }
        else
            uri = Uri.parse(edt.getText().toString());
      //  Log.d("S", edt.getText().toString());
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        // sendIntent.setAction(Intent.ACTION_SEND);
        //  intent.putExtra(Intent.EXTRA_TEXT, uri);
        startActivity(Intent.createChooser(intent,"请选择你的浏览器"));
    }


}
