package com.example.zac.lab9;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn_start;
    private TextView run_time;
    private BroadcastReceiver myBroadcasReciver;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_start = (Button) findViewById(R.id.send);
        run_time = (TextView) findViewById(R.id.message);

        btn_start.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(MainActivity.this , MyService.class);
                startService(i);
                Toast.makeText(MainActivity.this , "Srevice Activated" , Toast.LENGTH_SHORT).show();

            }
        });

        myBroadcasReciver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle myBundle = intent.getExtras();
                int myInt = myBundle.getInt("backgroud_service");

                run_time.setText("後台Service執行" + myInt + "sec");
         }
        };


        IntentFilter intentFilter = new IntentFilter("MyMessage");
        registerReceiver(myBroadcasReciver , intentFilter);







    }
}
