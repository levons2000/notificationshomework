package com.example.hp.notificationapp.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hp.notificationapp.R;
import com.example.hp.notificationapp.fragments.LoginFragment;

public class RegisterActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fragmentManager = getSupportFragmentManager();

        setFragment(new LoginFragment());
    }

    public void setFragment(Fragment fragment) {
        fragmentManager.beginTransaction().replace(R.id.register_container, fragment).commit();
    }
}
