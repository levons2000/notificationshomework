package com.example.hp.notificationapp.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.notificationapp.R;
import com.example.hp.notificationapp.models.UserNotification;
import com.example.hp.notificationapp.providers.UserDatas;
import com.squareup.picasso.Picasso;

@SuppressLint("ValidFragment")
public class InfoDialogFragment extends DialogFragment {
    public static final String NOTIFICATION_POSITION = "aaa";
    private Context context;
    private ImageView imageView;
    private TextView textName;
    private TextView textDay;
    private TextView textTime;
    private TextView textDescription;

    @SuppressLint("ValidFragment")
    public InfoDialogFragment(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_info, container, false);
        imageView = view.findViewById(R.id.info_image);
        textName = view.findViewById(R.id.info_name);
        textDay = view.findViewById(R.id.info_day);
        textTime = view.findViewById(R.id.info_time);
        textDescription = view.findViewById(R.id.info_desc);
        setAllInfo();
        return view;
    }

    private void setAllInfo() {
        Picasso.get().load(getNotification().getImgUrl()).into(imageView);
        textName.setText(getNotification().getName());
        textDay.setText(getNotification().getDay() + "." + getNotification().getMonth() + "." + getNotification().getDay());
        textTime.setText(getNotification().getHour() + ":" + getNotification().getMinute());
        textDescription.setText(getNotification().getDescription());
    }

    private UserNotification getNotification() {
        return UserDatas.list.get(UserDatas.position).getUserNotifications().get(getArguments().getInt(NOTIFICATION_POSITION));
    }

}
