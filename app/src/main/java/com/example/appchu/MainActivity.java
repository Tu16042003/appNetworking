package com.example.appchu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.appchu.Fragment.HomeFragment;
import com.example.appchu.Fragment.SearchFragment;
import com.example.appchu.Fragment.BookFragment;
import com.example.appchu.Fragment.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

//    private ActionBar toolbar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // bottomnavigation
//        toolbar = getSupportActionBar();


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Fragment fragment = new HomeFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.frame_container, fragment, null)
                .commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = new HomeFragment();
            FragmentManager manager = getSupportFragmentManager();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new HomeFragment();

                    break;
                case R.id.navigation_book:
                    fragment = new BookFragment();

                    break;
                case R.id.navigation_search:
                    fragment = new SearchFragment();

                    break;
                case R.id.navigation_profile:
                    fragment = new UserFragment();
                    break;
               
            }
            manager.beginTransaction()
                    .replace(R.id.frame_container, fragment, null)
                    .commit();
            return true;
        }
    };
}