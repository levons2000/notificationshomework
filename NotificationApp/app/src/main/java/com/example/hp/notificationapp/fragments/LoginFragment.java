package com.example.hp.notificationapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.hp.notificationapp.R;
import com.example.hp.notificationapp.activities.NotificationsActivity;
import com.example.hp.notificationapp.activities.RegisterActivity;
import com.example.hp.notificationapp.providers.UserDatas;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    public static final String KEY_USER = "key_user";
    private boolean isPassMode = true;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        Button loginButton = view.findViewById(R.id.lg_login_button);
        Button regButton = view.findViewById(R.id.lg_register_button);
        final EditText email = view.findViewById(R.id.email_input);
        final EditText password = view.findViewById(R.id.password_input);
        final ImageButton passButton = view.findViewById(R.id.password_mode);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UserDatas.getUserId(email.getText().toString(), password.getText().toString()) != -1) {
                    Intent intent = new Intent(getActivity(), NotificationsActivity.class);
                    UserDatas.position = UserDatas.getUserId(email.getText().toString(), password.getText().toString());
                    getActivity().startActivity(intent);
                } else {
                    email.setText("");
                    password.setText("");
                    Toast.makeText(getActivity(), "Bad Login or Password", Toast.LENGTH_SHORT).show();
                }

            }
        });
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((RegisterActivity) getActivity()).setFragment(new RegisterFragment());
            }
        });
        passButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPassMode){
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isPassMode = true;
                }else {
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isPassMode = false;
                }
            }
        });
        return view;
    }

}
