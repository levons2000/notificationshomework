package com.example.hp.notificationapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.notificationapp.R;
import com.example.hp.notificationapp.activities.RegisterActivity;
import com.example.hp.notificationapp.models.UserNotification;
import com.example.hp.notificationapp.models.UserProfile;
import com.example.hp.notificationapp.providers.UserDatas;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        final EditText name = view.findViewById(R.id.reg_name);
        final EditText email = view.findViewById(R.id.reg_email);
        final EditText number = view.findViewById(R.id.phone_number);
        final EditText password = view.findViewById(R.id.reg_password);
        Button loginButton = view.findViewById(R.id.reg_login_button);
        Button registerButton = view.findViewById(R.id.reg_register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!UserDatas.isEmailUsed(email.getText().toString())) {
                    UserDatas.list.add(new UserProfile(name.getText().toString(), email.getText().toString(), number.getText().toString(), password.getText().toString(), new ArrayList<UserNotification>()));
                    ((RegisterActivity) getActivity()).setFragment(new LoginFragment());
                } else {
                    Toast.makeText(getActivity(), "This email already used", Toast.LENGTH_SHORT).show();
                }
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((RegisterActivity) getActivity()).setFragment(new LoginFragment());
            }
        });
        return view;
    }

}
