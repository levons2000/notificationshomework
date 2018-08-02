package com.example.hp.notificationapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.hp.notificationapp.R;
import com.example.hp.notificationapp.activities.RegisterActivity;

public class ViewPagerAdapter extends PagerAdapter {

    private int[] array = {R.drawable.wakeup, R.drawable.lunch, R.drawable.work};
    private Context context;

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return array.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v = LayoutInflater.from(context).inflate(R.layout.view_pager_item_style, container, false);
        ImageView imageView = v.findViewById(R.id.view_pager_image);
        Button skipButton = v.findViewById(R.id.skip_pager);
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RegisterActivity.class);
                context.startActivity(intent);
            }
        });
        imageView.setImageResource(array[position]);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
