package com.example.tugas.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.tugas.JobServiceMe;
import com.example.tugas.Fragment.PageAdapter;
import com.example.tugas.R;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import static com.example.tugas.WifiConnect.CONNECT_WIFI;
import static com.example.tugas.WifiConnect.DISCONNECT_WIFI;

public class PageUtamaActivity extends AppCompatActivity {
    private static final String TAG = "PageUtamaActivity";
    private static String MY_FLAG = "MY_FLAG";
    private NotificationManagerCompat notificationCompat;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_utama);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tab);
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager()));

        notificationCompat = NotificationManagerCompat.from(this);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabCount();


//        mengambil nilai dari home

//        Bundle bundle = getIntent().getExtras();
//        if(bundle != null){
//            String isiBundle = bundle.getString("KEY","");
//            Toast.makeText(getApplicationContext(),isiBundle,Toast.LENGTH_SHORT).show();
//        }
        tampil();

    }

    private void tampil(){
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String isiBundle = bundle.getString("KEY","");
            Toast.makeText(getApplicationContext(),isiBundle,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        registerReceiver(wificheck, new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(wificheck);
    }

    private BroadcastReceiver wificheck = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int wifiStateExtra = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,
                    WifiManager.WIFI_STATE_UNKNOWN);
            switch (wifiStateExtra){
                case WifiManager.WIFI_STATE_ENABLED:
                    android.app.Notification notification = new NotificationCompat.Builder(PageUtamaActivity.this,CONNECT_WIFI)
                            .setSmallIcon(R.drawable.ic_wifi_connect)
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                            .setContentTitle("Koneksi Wifi")
                            .setContentText("Wifi Terhubung")
                            .build();
                    notificationCompat.notify(1,notification);
                    break;
                case WifiManager.WIFI_STATE_DISABLED:
                    android.app.Notification notification2 = new NotificationCompat.Builder(PageUtamaActivity.this,DISCONNECT_WIFI)
                            .setSmallIcon(R.drawable.ic_signal_off)
                            .setPriority(NotificationCompat.PRIORITY_LOW)
                            .setContentTitle("Koneksi Wifi")
                            .setContentText("Wifi Terputus")
                            .build();
                    notificationCompat.notify(2,notification2);
                    break;
            }
        }
    };

    public void ScheduleJob(View view){
        ComponentName componentName = new ComponentName(getApplicationContext(), JobServiceMe.class);
        JobInfo info = new JobInfo.Builder(123,componentName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic(15 * 60 * 1000)
                .build();
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = Objects.requireNonNull(scheduler).schedule(info);
        if(resultCode == JobScheduler.RESULT_SUCCESS){
            Log.i(TAG,"scheduleJob: Job Scheduled");
        }else{
            Log.i(TAG,"scheduleJob: Job Scheduled failed");
        }
    }

    public void cancelJob(View view){
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        Objects.requireNonNull(scheduler).cancel(123);
        Log.i(TAG,"cancelJob");
    }


}
