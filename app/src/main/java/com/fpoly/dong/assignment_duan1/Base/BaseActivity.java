package com.fpoly.dong.assignment_duan1.Base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.fpoly.dong.assignment_duan1.HomeActivity;
import com.fpoly.dong.assignment_duan1.NewsActivity;
import com.fpoly.dong.assignment_duan1.R;
import com.fpoly.dong.assignment_duan1.SquadActivity;
import com.fpoly.dong.assignment_duan1.TicketsActivity;

public class BaseActivity extends AppCompatActivity {
    private BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int prevNav = getSelectedNav();
            int currentNav = item.getItemId();
            if (currentNav == prevNav)
                return false;
            switch (item.getItemId()) {
                case R.id.home:
                    Intent intent1 = new Intent(BaseActivity.this, HomeActivity.class);
                    startActivity(intent1);
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.tintuc:
                    Intent intent2 = new Intent(BaseActivity.this, NewsActivity.class);
                    startActivity(intent2);
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.ve:
                    Intent intent3 = new Intent(BaseActivity.this, TicketsActivity.class);
                    startActivity(intent3);
                    overridePendingTransition(0, 0);
                    return true;
                    case R.id.squad:
                    Intent intent4 = new Intent(BaseActivity.this, SquadActivity.class);
                    startActivity(intent4);
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        }

    };

    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation);

        navigationView = (BottomNavigationView) findViewById(R.id.bottomnavigation);
        navigationView.setOnNavigationItemSelectedListener(listener);
    }

    public View setContentLayout(int layoutID)
    {
        FrameLayout contentLayout = (FrameLayout) findViewById(R.id.fragment_container);
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(layoutID, contentLayout, true);
    }

    public void setSelected(int optionID)
    {
        navigationView.getMenu().findItem(optionID).setChecked(true);
        getSharedPreferences(getPackageName(), MODE_PRIVATE).edit().putInt("selectedNav",optionID).commit();
    }

    public int getSelectedNav()
    {
        return getSharedPreferences(getPackageName(), MODE_PRIVATE).getInt("selectedNav", R.id.home);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

}
