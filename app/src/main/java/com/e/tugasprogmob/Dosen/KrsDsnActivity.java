package com.e.tugasprogmob.Dosen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.e.tugasprogmob.Adapter.KRSAdapter;
import com.e.tugasprogmob.Model.KRS;
import com.e.tugasprogmob.R;

import java.util.ArrayList;

public class KrsDsnActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private KRSAdapter mahasiswaAdapter;
    private ArrayList<KRS> mahasiswaArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_krs_dsn);

        addData();
        recyclerView = findViewById(R.id.rvmatkulmhs);
        mahasiswaAdapter = new KRSAdapter(mahasiswaArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(KrsDsnActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mahasiswaAdapter);
    }

    private void addData() {
        mahasiswaArrayList = new ArrayList<>();
        mahasiswaArrayList.add(new KRS("KODE - MATKUL","Hari","Sesi","Jumlah SKS","Dosen pengampu","Jumlah Mahasiswa"));
        mahasiswaArrayList.add(new KRS("KODE - MATKUL","Hari","Sesi","Jumlah SKS","Dosen pengampu","Jumlah Mahasiswa"));
    }
}
