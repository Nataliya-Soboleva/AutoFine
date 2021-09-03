package com.example.autofine.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.autofine.fragments.MainPageFragment;
import com.example.autofine.R;
import com.example.autofine.fragments.SupportFragment;
import com.example.autofine.fragments.UserAgreementFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private FragmentManager fm;
    private ImageButton btnSearch;
    private ImageButton btnFines;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        fm = getSupportFragmentManager();
    }

    private void initViews() {

        btnFines = findViewById(R.id.btnFines);
        btnSearch = findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(this);
        btnFines.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int menuId = item.getItemId();

        switch (menuId)
        {
            case R.id.action_main:{

                showMainPageFragment();
                break;
            }
            case R.id.action_support:
            {
               showSupportFragment();
                break;
            }
            case R.id.action_user_agreement:
            {
                showUserAgreementFragment();
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void showMainPageFragment() {
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.containerContent, new MainPageFragment());//заменяет старый фрагмент новым
        ft.commit();

    }

    private void showUserAgreementFragment() {

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.containerContent, new UserAgreementFragment());//заменяет старый фрагмент новым
        ft.commit();
    }

    private void showSupportFragment() {
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.containerContent, new SupportFragment());//заменяет старый фрагмент новым
        ft.commit();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btnSearch:
            {
                showMainPageFragment();
                break;
            }
            case R.id.btnFines:
            {
                startFinesActivity();
                break;
            }
        }
    }
    private void startFinesActivity() {

        Intent checkFinesActivityIntent = new Intent(this, CheckFinesActivity.class);

        startActivity(checkFinesActivityIntent);
    }
}