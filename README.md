# About 

> 使用隐式Intent调用WebView自定义浏览器

## 一.实现效果
> pic1旧图，重传
![WebView自定义浏览器](https://github.com/nomenofear/Android_exp_05/blob/master/app/imgs/Snipaste_2019-04-23_21-34-17.png "WebView自定义浏览器")

![输入网址调用自定义浏览器](https://github.com/nomenofear/Android_exp_05/blob/master/app/imgs/Snipaste_2019-04-23_21-35-50.png "输入网址调用自定义浏览器")



## 二.关键代码

> 启动程序的layout

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="match_parent">
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="horizontal">

    <EditText
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:hint="请输入网址"
        android:id="@+id/et_uri"
        android:textSize="20sp"
        />

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="访问"
        android:id="@+id/btn_visit"
        />

    </LinearLayout>
</LinearLayout>
```

> 启动程序MainActivity.java

 ```java
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
 ```

 > WebView自定义浏览器Mainifest.xml <intent-filter>中添加

 ```xml
    <intent-filter>
                 <data android:scheme="http"/>
                 <data android:scheme="https" />
                 <action android:name="android.intent.action.VIEW"/>
                 <category android:name="android.intent.category.DEFAULT" />
 
                 <category android:name="android.intent.category.BROWSABLE" />
             </intent-filter>
 ```

> Mainifest.xml中添加

 ```xml
 <uses-permission android:name="android.permission.INTERNET"></uses-permission>
 ```

> WebView自定义浏览器的layout.xml

 ```xml
     
 <?xml version="1.0" encoding="utf-8"?>
 <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
     android:orientation="vertical" android:layout_width="match_parent"
     android:layout_height="match_parent">
     <LinearLayout
         android:layout_width="match_parent"
         android:layout_height="wrap_content"
         android:focusable="true"
         android:focusableInTouchMode="true"
         android:orientation="horizontal">
         <EditText
             android:id="@+id/edtttUri"
             android:layout_width="0dp"
             android:layout_height="wrap_content"
             android:singleLine="true"
             android:maxLines="1"
             android:scrollHorizontally="false"
             android:layout_weight="1"/>
         <Button
             android:id="@+id/btnGo"
             android:layout_width="wrap_content"
             android:layout_height="wrap_content"
             android:text="转到"/>
     </LinearLayout>
     <LinearLayout
         android:layout_width="match_parent"
         android:layout_height="wrap_content"
         android:orientation="horizontal">
         <Button
             android:id="@+id/btnBack"
             android:layout_width="0dp"
             android:layout_height="wrap_content"
             android:layout_weight="1"
             android:text="后退"/>
         <Button
             android:id="@+id/btnForeward"
             android:layout_width="0dp"
             android:layout_height="wrap_content"
             android:layout_weight="1"
             android:text="前进"/>
 
     </LinearLayout>
 
 
     <WebView
         android:layout_width="match_parent"
         android:layout_height="match_parent"
         android:id="@+id/myWebView">
 
     </WebView>
 
 </LinearLayout>
 ```





## End
