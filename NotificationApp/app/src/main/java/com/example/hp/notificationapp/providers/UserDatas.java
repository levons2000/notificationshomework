package com.example.hp.notificationapp.providers;

import com.example.hp.notificationapp.models.UserProfile;

import java.util.ArrayList;
import java.util.List;

public class UserDatas {
    public static List<UserProfile> list = new ArrayList<>();
    public static int position;

    public static int getUserId(String email, String password) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getEmail().equals(email) && list.get(i).getPassword().equals(password)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean isEmailUsed(String email) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
}
