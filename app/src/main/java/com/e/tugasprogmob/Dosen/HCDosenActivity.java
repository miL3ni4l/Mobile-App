package com.e.tugasprogmob.Dosen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.e.tugasprogmob.R;
import com.e.tugasprogmob.SplashActivity;

public class HCDosenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hcdosen);

        View.OnClickListener a = new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HCDosenActivity.this, DataDiriActivity.class);
                startActivity(intent);
            }
        };
        Button dd = (Button)findViewById(R.id.button);
        dd.setOnClickListener(a);

        View.OnClickListener b = new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HCDosenActivity.this, KrsDsnActivity.class);
                startActivity(intent);
            }
        };
        Button krs = (Button)findViewById(R.id.button2);
        krs.setOnClickListener(b);

        View.OnClickListener c = new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HCDosenActivity.this, MatkulDsnActivity.class);
                startActivity(intent);
            }
        };
        Button matkul = (Button)findViewById(R.id.button3);
        matkul.setOnClickListener(c);

        View.OnClickListener d = new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = getSharedPreferences("prefs_file",MODE_PRIVATE);
                SharedPreferences.Editor edit = prefs.edit();
                edit.putString("isLogin",null);
                edit.commit();
                Intent intent = new Intent(HCDosenActivity.this, SplashActivity.class);
                startActivity(intent);
            }
        };
        Button LO = (Button)findViewById(R.id.button4);
        LO.setOnClickListener(d);
    }
}
