package com.fpoly.dong.assignment_duan1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fpoly.dong.assignment_duan1.Base.BaseActivity;

public class TicketsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_tickets);
    }
    @Override
    protected void onResume() {
        super.onResume();
        setSelected(R.id.ve);

    }

}
