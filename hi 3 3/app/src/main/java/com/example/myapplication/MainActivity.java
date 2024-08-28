package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;

import com.example.myapplication.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private BackKeyClickHandler backKeyClickHandler;
    ActionBarDrawerToggle barDrawerToggle;
    DrawerLayout drawerLayout;
    FirebaseAuth mAuth;

    LinearLayout btn1, btn2, btn3, btn4;

    TextView btn1_1;
    TextView btn2_1;
    TextView btn3_1;
    TextView btn4_1;

    @SuppressLint("MissingInflatedId")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(" ");

        String selectedDate = getIntent().getStringExtra("selectedDate");
        backKeyClickHandler = new BackKeyClickHandler(this);
        mAuth = FirebaseAuth.getInstance();
        drawerLayout=findViewById(R.id.drawer_layout);

        btn1 = (LinearLayout) findViewById(R.id.layout_1);
        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                Intent project1 = new Intent(MainActivity.this, SchoolMapActivity.class);
                startActivity(project1);
            }
        });

        btn1_1 = (TextView) findViewById(R.id.frag_button1_1);
        btn1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent project1 = new Intent(MainActivity.this, SchoolMapActivity.class);
                startActivity(project1);
            }
        });
        btn2 = (LinearLayout) findViewById(R.id.layout_2);

        btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                Intent project2 = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(project2);
            }
        });

        btn2_1 = (TextView) findViewById(R.id.frag_button2_1);
        btn2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent project1 = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(project1);
            }
        });

        btn3 = (LinearLayout) findViewById(R.id.layout_3);

        btn3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                Intent project3 = new Intent(MainActivity.this, ChatBotActivity.class);
                startActivity(project3);
            }
        });
        btn3_1 = (TextView) findViewById(R.id.frag_button3_1);
        btn3_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent project1 = new Intent(MainActivity.this, ChatBotActivity.class);
                startActivity(project1);
            }
        });


        FirebaseUser user = mAuth.getCurrentUser();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        barDrawerToggle= new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name,R.string.app_name);
        //ActionBar(제목줄)의 홈or업버튼의 위치에 토글아이콘이 보이게끔...
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //삼선아이콘 모양으로 보이도록
        //토글버튼의 동기를 맞추기
        barDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            backKeyClickHandler.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        barDrawerToggle.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.homepage) {

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.yu.ac.kr/main/index.do"));
            startActivity(intent);
        }
        if (id == R.id.portal) {

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://portal.yu.ac.kr/sso/login.jsp"));
            startActivity(intent);

        }
        if (id == R.id.foodmenu) {

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://hcms.yu.ac.kr/main/life/cafeteria-menu.do"));
            startActivity(intent);

        }
        if (id == R.id.union) {

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/yuch_56?igshid=NTc4MTIwNjQ2YQ=="));
            startActivity(intent);

        }

        if (id == R.id.loginOutBtn) {
            mAuth.signOut();
            finish();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }


}
