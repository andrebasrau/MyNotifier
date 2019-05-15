package com.example.myalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private int notifid = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.set).setOnClickListener(this);
        findViewById(R.id.cancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText text = findViewById(R.id.editText);
        TimePicker timepicker = findViewById(R.id.timePicker);

        Intent intent = new Intent (MainActivity.this, AlarmReceiver.class);
        intent.putExtra ("notificationid", notifid);
        intent.putExtra ("todo", text.getText().toString());
        PendingIntent alarmIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
        switch (v.getId()){
            case R.id.set:
                int hour = timepicker.getCurrentHour();
                int minute = timepicker.getCurrentMinute();

                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hour);
                startTime.set (Calendar.MINUTE, minute);
                startTime.set (Calendar.SECOND, 0);
                long alarmStartTime = startTime.getTimeInMillis();
                alarm.set (AlarmManager.RTC_WAKEUP, alarmStartTime, alarmIntent);
                Toast.makeText (this, "Done!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cancel:
                alarm.cancel (alarmIntent);
                Toast.makeText (this, "Canceled", Toast.LENGTH_SHORT).show();

                break;
        }
    }
}
