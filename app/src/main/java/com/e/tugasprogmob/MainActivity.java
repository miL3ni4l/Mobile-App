package com.e.tugasprogmob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;


import com.e.tugasprogmob.Admin.DaftarDosenActivity;
import com.e.tugasprogmob.Admin.HCAdminActivity;
import com.e.tugasprogmob.Dosen.HCDosenActivity;

public class MainActivity extends AppCompatActivity {

    private EditText email;

    private String valid_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitializeUI();

        SharedPreferences prefs = getSharedPreferences("prefs_file",MODE_PRIVATE);

        String statusLogin = prefs.getString("isLogin","");
        TextView ddd = findViewById(R.id.textView6);
        ddd.setText(statusLogin);


        if (statusLogin.equals("admin")){
            Intent intent = new Intent(MainActivity.this, HCAdminActivity.class);
            startActivity(intent);
        }else if(statusLogin.equals("dosen")){
            Intent intent = new Intent(MainActivity.this, HCDosenActivity.class);
            startActivity(intent);
        }

        View.OnClickListener a = new View.OnClickListener()
        {
            SharedPreferences prefs = MainActivity.this.getSharedPreferences("prefs_file",MODE_PRIVATE);
            SharedPreferences.Editor edit = prefs.edit();
            EditText pass = (EditText) findViewById(R.id.pass);
            EditText email = (EditText) findViewById(R.id.etEmail);
            @Override
            public void onClick(View view) {
                if(pass.getText().toString().equals("admin")&&email.getText().toString().endsWith("@staff.ukdw.ac.id")) {
                    edit.putString("isLogin","admin");
                    edit.commit();
                    Intent intent = new Intent(MainActivity.this, HCAdminActivity.class);
                    startActivity(intent);
                }else if(pass.getText().toString().equals("dosen")&&email.getText().toString().endsWith("@si.ukdw.ac.id")){
                    edit.putString("isLogin","dosen");
                    edit.commit();
                    Intent intent = new Intent(MainActivity.this, HCDosenActivity.class);
                    startActivity(intent);
                }else {
                    finish();
                    startActivity(getIntent());
                }
            }
        };
        Button dd = (Button)findViewById(R.id.LogIn);
        dd.setOnClickListener(a);
    }

    public void InitializeUI() {
        email = (EditText) findViewById(R.id.etEmail);

        email.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

                // TODO Auto-generated method stub
                Is_Valid_Email(email); // pass your EditText Obj here.
            }

            public void Is_Valid_Email(EditText edt) {
                if (edt.getText().toString() == null) {
                    edt.setError("Invalid Email Address");
                    valid_email = null;
                } else if (isEmailValid(edt.getText().toString()) == false) {
                    edt.setError("Invalid Email Address");
                    valid_email = null;
                } else {
                    valid_email = edt.getText().toString();
                }
            }

            boolean isEmailValid(CharSequence email) {
                return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                        .matches();
            } // end of TextWatcher (email)
        });
    }
}
