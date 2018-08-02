package com.example.hp.notificationapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hp.notificationapp.R;
import com.example.hp.notificationapp.adapters.RecyclerAdapter;
import com.example.hp.notificationapp.helper.RecyclerItemTouchHelper;
import com.example.hp.notificationapp.helper.RecyclerItemTouchHelperListener;
import com.example.hp.notificationapp.models.UserNotification;
import com.example.hp.notificationapp.providers.UserDatas;

import java.util.List;


public class NotificationsActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private int progress = 0;
    private List<UserNotification> list;
    private TextView textView;
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressBar = findViewById(R.id.load_recycler);
        textView = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recycler_notifications);
        setRecyclerView();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotificationsActivity.this, AddNotificationActivity.class);
                startActivity(intent);
            }
        });

        startProgressBar();

    }

    private void startProgressBar() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (progress < 100) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(String.valueOf(progress));
                        }
                    });
                    progressBar.setProgress(progress);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        Log.d("Interrupted exceptiom", e.getMessage());
                    }
                    progress += 1;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setVisibility(View.INVISIBLE);
                        progressBar.setVisibility(View.INVISIBLE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
        thread.start();
    }

    private void setRecyclerView() {
        list = UserDatas.list.get(UserDatas.position).getUserNotifications();
        recyclerAdapter = new RecyclerAdapter(NotificationsActivity.this, list);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(NotificationsActivity.this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(NotificationsActivity.this, DividerItemDecoration.VERTICAL));
        ItemTouchHelper.SimpleCallback item = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, new RecyclerItemTouchHelperListener() {
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
                if (viewHolder instanceof RecyclerAdapter.MyViewHolder) {
                    recyclerAdapter.removeItem(viewHolder.getAdapterPosition());
                    recyclerAdapter.notifyDataSetChanged();
                }
            }
        });
        new ItemTouchHelper(item).attachToRecyclerView(recyclerView);

    }


}
