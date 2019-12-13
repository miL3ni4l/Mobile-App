package com.e.tugasprogmob.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.e.tugasprogmob.Adapter.DosenAdapter;
import com.e.tugasprogmob.Admin.Insert.InsertMhsActivity;
import com.e.tugasprogmob.Model.Dosen;
import com.e.tugasprogmob.Model.Mahasiswa;
import com.e.tugasprogmob.R;

import java.util.ArrayList;

public class DaftarMahasiswaActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DosenAdapter mahasiswaAdapter;
    private ArrayList<Dosen> mahasiswaArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_mahasiswa);

        addData();
        recyclerView = findViewById(R.id.rvdd);
        mahasiswaAdapter = new DosenAdapter(mahasiswaArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DaftarMahasiswaActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mahasiswaAdapter);
    }

    private void addData() {
        mahasiswaArrayList = new ArrayList<>();
        mahasiswaArrayList.add(new Dosen("NIDN","NAMA MHS","email","alamat"));
        mahasiswaArrayList.add(new Dosen("NIDN","NAMA MHS","email","alamat"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.utama,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(DaftarMahasiswaActivity.this, InsertMhsActivity.class);
        switch (item.getItemId()){
            case R.id.item1:
                startActivity(intent);
                return true;
            /*ase R.id.item2:
                startActivity(intent);
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
