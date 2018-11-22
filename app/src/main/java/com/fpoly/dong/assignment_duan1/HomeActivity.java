package com.fpoly.dong.assignment_duan1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class HomeActivity extends AppCompatActivity {
    private ImageView anhve;
    private ImageView new1;
    private ImageView squad;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        anhve = (ImageView) findViewById(R.id.anhve);
        new1 = (ImageView) findViewById(R.id.new1);
        squad = (ImageView) findViewById(R.id.squad);
        anhve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(HomeActivity.this,TicketsActivity.class);
                startActivity(intent1);

            }
        });
        new1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(HomeActivity.this,NewsActivity.class);
                startActivity(intent2);
            }
        });
        squad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(HomeActivity.this,SquadActivity.class);
                startActivity(intent3);
            }
        });

    }

}
