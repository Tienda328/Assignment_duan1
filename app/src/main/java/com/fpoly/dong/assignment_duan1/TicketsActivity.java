package com.fpoly.dong.assignment_duan1;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class TicketsActivity extends AppCompatActivity {

    private EditText edtten;
    private EditText edtsdt;
    private EditText edtdiachi;
    private EditText edtsoluong;
    private Button btnmua;
    private Button btnhuy;



    private Toolbar toolbar;
    private Button btnmua1;
    private Button btnmua2;
    private Button btnmua3;
    private Button btnmua4;
    private Button btnmua5;
    private Button btnmua6;
    private Button btnmua7;
    private Button btnmua8;
    private Button btnmua9;
    private Button btnmua10;
    private Button btnmua11;
    private Button btnmua12;
    private Button btnmua13;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Mua ve");

        toolbar.setNavigationIcon(R.drawable.iconback);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnmua1 = (Button) findViewById(R.id.btnmua1);
        btnmua2 = (Button) findViewById(R.id.btnmua2);
        btnmua3 = (Button) findViewById(R.id.btnmua3);
        btnmua4 = (Button) findViewById(R.id.btnmua4);
        btnmua5 = (Button) findViewById(R.id.btnmua5);
        btnmua6 = (Button) findViewById(R.id.btnmua6);
        btnmua7 = (Button) findViewById(R.id.btnmua7);
        btnmua8 = (Button) findViewById(R.id.btnmua8);
        btnmua9 = (Button) findViewById(R.id.btnmua9);
        btnmua10 = (Button) findViewById(R.id.btnmua10);
        btnmua11 = (Button) findViewById(R.id.btnmua11);
        btnmua12 = (Button) findViewById(R.id.btnmua12);
        btnmua13 = (Button) findViewById(R.id.btnmua13);

        btnmua1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                kiemtra();

            }
        });
          btnmua2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kiemtra();
            }
        });
          btnmua3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kiemtra();
            }
        });
          btnmua4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kiemtra();
            }
        });
          btnmua5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kiemtra();
            }
        });
          btnmua6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kiemtra();
            }
        });
          btnmua7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kiemtra();
            }
        });
          btnmua8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kiemtra();
            }
        });
          btnmua9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kiemtra();
            }
        });
          btnmua10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kiemtra();
            }
        });
          btnmua11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kiemtra();
            }
        });
          btnmua12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kiemtra();
            }
        });
          btnmua13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kiemtra();
            }
        });



        }
    private void kiemtra(){
        final Dialog dialog = new Dialog(TicketsActivity.this);
        dialog.setContentView(R.layout.dialog_muave);
       Button btnmua =dialog.findViewById(R.id.btnmua);
        Button btnhuy = (Button) dialog.findViewById(R.id.btnhuy);

        edtten = (EditText) dialog.findViewById(R.id.edtten);
        edtsdt = (EditText) dialog.findViewById(R.id.edtsdt);
        edtdiachi = (EditText) dialog.findViewById(R.id.edtdiachi);
        edtsoluong = (EditText) dialog.findViewById(R.id.edtsoluong);

        btnmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ten = edtten.getText().toString().trim();
                final String sdt = edtsdt.getText().toString().trim();
                final String diachi =    edtdiachi.getText().toString().trim();
                final String soluong = edtsoluong.getText().toString().trim();

                if (ten.equals("") ) {
                    edtten.setError(getString(R.string.loi1));
                    edtten.requestFocus();
                    return;
                }
                if (ten.length()<5 || ten.length()>32) {
                    edtten.setError(getString(R.string.errormaxmin));
                    edtten.requestFocus();
                    return;
                }
//                    if (ten.length()>32){
//                    edtten.setError(getString(R.string.errormax));
//                    edtten.requestFocus();
//                    return;
//                }
                if (sdt.equals("") ) {
                    edtsdt.setError(getString(R.string.loi1));
                    edtsdt.requestFocus();

                    return;
                }
                if (!sdt.startsWith("+84") && !sdt.startsWith("0")) {
                    edtsdt.setError(getString(R.string.error_1));
                    edtsdt.requestFocus();
                    return;
                }
                if (sdt.length() != 10 && sdt.length() != 12) {
                    edtsdt.setError(getString(R.string.error_2));
                    edtsdt.requestFocus();
                    return;
                }
                if (diachi.equals("") ) {
                    edtdiachi.setError(getString(R.string.loi1));
                    edtdiachi.requestFocus();
                    return;
                }
                if (soluong.equals("") ) {
                    edtsoluong.setError(getString(R.string.loi1));
                    edtsoluong.requestFocus();
                    return;
                }
                final int soluong1 = Integer.valueOf(soluong);

                if (soluong1 > 5){
                    edtsoluong.setError(getString(R.string.errorsl));
                    edtsoluong.requestFocus();
                    return;
                }




                Toast.makeText(TicketsActivity.this, "Chúc mừng bạn đã mua thành công", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }

        });
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });








        dialog.show();
    }




}
