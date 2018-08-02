package com.example.hp.notificationapp.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;

import com.example.hp.notificationapp.R;
import com.example.hp.notificationapp.models.UserNotification;
import com.example.hp.notificationapp.providers.UserDatas;

import java.util.Calendar;

public class AddNotificationActivity extends AppCompatActivity {

    private int[] arrayOfDate = new int[5];
    private String[] notStringDate = new String[3];
    private EditText name;
    private EditText description;
    private EditText imgPath;
    private ImageButton addNot;
    private Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notification);
        name = findViewById(R.id.add_not_name);
        description = findViewById(R.id.add_not_desc);
        imgPath = findViewById(R.id.add_not_img);
        addNot = findViewById(R.id.add_notification);
        showDatePicker();
        showTimePicker();
        getAllText();

    }


    private void showDatePicker() {
        final ImageButton picker = findViewById(R.id.add_not_month);
        picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatePickerDialog dialog = new DatePickerDialog(AddNotificationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        arrayOfDate[0] = i;
                        arrayOfDate[1] = i1;
                        arrayOfDate[2] = i2;
                        calendar.set(Calendar.YEAR, i);
                        calendar.set(Calendar.MONTH, i1);
                        calendar.set(Calendar.DAY_OF_MONTH, i2);
                    }
                },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));

                dialog.show();
            }
        });

    }

    private void showTimePicker() {
        final ImageButton button = findViewById(R.id.add_not_hour);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final TimePickerDialog dialog = new TimePickerDialog(AddNotificationActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        arrayOfDate[3] = i;
                        arrayOfDate[4] = i1;
                    }
                }, calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        true);
                dialog.show();
            }
        });
    }

    private void getAllText() {
        addNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notStringDate[0] = imgPath.getText().toString();
                notStringDate[1] = name.getText().toString();
                notStringDate[2] = description.getText().toString();
                UserDatas.list.get(UserDatas.position).getUserNotifications().add(new UserNotification(notStringDate[0], notStringDate[1], notStringDate[2], arrayOfDate[0], arrayOfDate[1], arrayOfDate[2], arrayOfDate[3], arrayOfDate[4]));
                Intent intent = new Intent(AddNotificationActivity.this, NotificationsActivity.class);
                startActivity(intent);
            }
        });
    }


}
