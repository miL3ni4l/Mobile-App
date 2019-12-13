package com.e.tugasprogmob.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.e.tugasprogmob.Adapter.KRSAdapter;
import com.e.tugasprogmob.Admin.Insert.InsertMatkulActivity;
import com.e.tugasprogmob.Admin.Insert.InsertMhsActivity;
import com.e.tugasprogmob.Model.KRS;
import com.e.tugasprogmob.R;

import java.util.ArrayList;

public class MatkulAdminActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private KRSAdapter mahasiswaAdapter;
    private ArrayList<KRS> mahasiswaArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matkul_admin);

        addData();
        recyclerView = findViewById(R.id.rvmatkulmhs);
        mahasiswaAdapter = new KRSAdapter(mahasiswaArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MatkulAdminActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mahasiswaAdapter);
    }

    private void addData() {
        mahasiswaArrayList = new ArrayList<>();
        mahasiswaArrayList.add(new KRS("KODE - MATKUL","Hari","Sesi","Jumlah SKS","",""));
        mahasiswaArrayList.add(new KRS("KODE - MATKUL","Hari","Sesi","Jumlah SKS","",""));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.utama,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(MatkulAdminActivity.this, InsertMatkulActivity.class);
        switch (item.getItemId()){
            case R.id.item1:
                startActivity(intent);
                return true;
            /*case R.id.item2:
                startActivity(intent);
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
