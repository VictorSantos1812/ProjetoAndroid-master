package com.LembretesVictor.projeto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

public class Calendario extends AppCompatActivity {

    private static final String TAG = "Calendar";

    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);
        calendarView = (CalendarView) findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                if(month >= 8) {
                    String date = dayOfMonth + "/" + (month + 1) + "/" + year;
                    Intent intent = new Intent(Calendario.this, LembreteActivity.class);
                    intent.putExtra("date", date);
                    startActivity(intent);
                }else{
                    String date = dayOfMonth + "/0" + (month + 1) + "/" + year;
                    Intent intent = new Intent(Calendario.this, LembreteActivity.class);
                    intent.putExtra("date", date);
                    startActivity(intent);
                }
            }
        });
    }
}