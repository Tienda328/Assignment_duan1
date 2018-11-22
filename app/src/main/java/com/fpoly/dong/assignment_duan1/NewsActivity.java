package com.fpoly.dong.assignment_duan1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
    }

    public void newbongda(View view) {
        Intent intent =new Intent(Intent.ACTION_VIEW,Uri.parse("http://bongdanet.vn/"));
        startActivity(intent);
    }
}
