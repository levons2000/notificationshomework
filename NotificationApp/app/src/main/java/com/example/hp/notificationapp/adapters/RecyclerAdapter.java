package com.example.hp.notificationapp.adapters;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.notificationapp.R;
import com.example.hp.notificationapp.activities.MainActivity;
import com.example.hp.notificationapp.activities.NotificationsActivity;
import com.example.hp.notificationapp.fragments.InfoDialogFragment;
import com.example.hp.notificationapp.models.UserNotification;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private Context context;
    private List<UserNotification> list;


    public RecyclerAdapter(Context context, List<UserNotification> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Picasso.get().load(list.get(position).getImgUrl()).into(holder.circleImageView);
        holder.name.setText(list.get(position).getName());
        holder.day.setText(list.get(position).getMonth() + "." + list.get(position).getDay() + "." + list.get(position).getYear());
        holder.time.setText(list.get(position).getHour() + ":" + list.get(position).getMinute());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void removeItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        TextView name;
        TextView day;
        TextView time;

        public MyViewHolder(View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.notification_image);
            name = itemView.findViewById(R.id.notification_name);
            day = itemView.findViewById(R.id.notification_day);
            time = itemView.findViewById(R.id.notification_time);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    InfoDialogFragment infoDialogFragment = new InfoDialogFragment(context);
                    Bundle bundle = new Bundle();
                    bundle.putInt(InfoDialogFragment.NOTIFICATION_POSITION, getAdapterPosition());
                    infoDialogFragment.setArguments(bundle);
                    infoDialogFragment.show(((NotificationsActivity)context).getSupportFragmentManager(), "tag");
                }
            });
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Calendar calendar = Calendar.getInstance();
                        Thread.sleep(((list.get(list.size() - 1).getDay() - (calendar.getTime().getDay() - 2)) * 86400000) + ((list.get(list.size() - 1).getHour() - calendar.getTime().getHours()) * 3600000) + ((list.get(list.size() - 1).getMinute() - calendar.getTime().getMinutes()) * 60000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    NotificationChannel channel = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        channel = new NotificationChannel("channelId", "name", NotificationManager.IMPORTANCE_DEFAULT);
                    }
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "channelId")
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle(list.get(list.size() - 1).getName())
                            .setContentText(list.get(list.size() - 1).getDescription())
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                    Intent resultIntent = new Intent();
                    Intent[] intents = {resultIntent};
                    PendingIntent resultPendingIntent = PendingIntent.getActivities(context, 0, intents, 0);
                    mBuilder.setContentIntent(resultPendingIntent);
                    NotificationManager notificationManager = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        notificationManager = ((NotificationsActivity) context).getSystemService(NotificationManager.class);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            notificationManager.createNotificationChannel(channel);
                        }
                        notificationManager.notify(0, mBuilder.build());
                    }

                }
            });
            thread.start();
        }
    }
}
