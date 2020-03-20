package com.example.tugas;

import android.app.job.JobParameters;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;


public class JobServiceMe extends android.app.job.JobService {
    private final String TAG = JobServiceMe.class.getSimpleName();
    private boolean jobCancelled = false;
    Context context;

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.i(TAG,"onStartJob:");
        context =  getApplicationContext();
        doBackground(params);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.i(TAG,"onStopJob: cancel");
        jobCancelled = true;
        return true;
    }

    private void doBackground(final JobParameters parameters){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;i++) {
                    Log.i(TAG, "run: " + i);

                    final int finalI = i;
                    Handler mHandler = new Handler(getMainLooper());
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"Berhasil"+String.valueOf(finalI), Toast.LENGTH_SHORT).show();
                        }
                    });

                    if (jobCancelled){
                        return;
                    }

                    try {
                        Thread.sleep(3000);
                    }catch (InterruptedException e){
                        Log.e(TAG,"InterruptedException: ",e.getCause());
                    }
                }
                Log.i(TAG,"run: JOB FINISHED");
            }
        }).start();
    }
}
