package com.example.tugas;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class WifiConnect extends Application {

    public static final String CONNECT_WIFI = "Connect";
    public static final String DISCONNECT_WIFI = "Disconnect";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotification();
    }

    private void createNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel connect = new NotificationChannel(
                    CONNECT_WIFI,
                    "Connect",
                    NotificationManager.IMPORTANCE_HIGH);//importance high diprioritas tingkat tinggi
            connect.setDescription("Connect");

            NotificationChannel disconnect = new NotificationChannel(
                    DISCONNECT_WIFI,
                    "Disconnect",
                    NotificationManager.IMPORTANCE_LOW);
            disconnect.setDescription("Disconnect");

            NotificationManager manager = getSystemService(NotificationManager.class);
            assert manager != null;
            manager.createNotificationChannel(connect);
            manager.createNotificationChannel(disconnect);
        }
    }
}
