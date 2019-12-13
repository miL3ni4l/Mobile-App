package com.e.tugasprogmob.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.e.tugasprogmob.Adapter.DosenAdapter;
import com.e.tugasprogmob.Adapter.MhsAdapter;
import com.e.tugasprogmob.Admin.Insert.InsertDosenActivity;
import com.e.tugasprogmob.Admin.Insert.InsertMhsActivity;
import com.e.tugasprogmob.Model.Dosen;
import com.e.tugasprogmob.Model.Mahasiswa;
import com.e.tugasprogmob.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarMahasiswaActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MhsAdapter mahasiswaAdapter;
    private ArrayList<Mahasiswa> mahasiswaArrayList;
    DataDosenService dataDosenService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_mahasiswa);
        mahasiswaArrayList = new ArrayList<>();
        dataDosenService = RetrofitClient.getRetrofitInstance()
                .create(DataDosenService.class);
        recyclerView = findViewById(R.id.rvdd);
        Call<ArrayList<Mahasiswa>> call = dataDosenService.getMhsAll("72170155");// memanggil data yang sudah ada
        call.enqueue(new Callback<ArrayList<Mahasiswa>>() {
            @Override
            public void onResponse(Call<ArrayList<Mahasiswa>> call, Response<ArrayList<Mahasiswa>> response) {
                mahasiswaArrayList = response.body();
                mahasiswaAdapter = new MhsAdapter(response.body());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DaftarMahasiswaActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(mahasiswaAdapter);


            }

            @Override
            public void onFailure(Call<ArrayList<Mahasiswa>> call, Throwable t) {
                Toast.makeText(DaftarMahasiswaActivity.this,"Something wrong....",Toast.LENGTH_LONG).show();
                //System.out.println(t.get);
            }
        });
        registerForContextMenu(recyclerView);
    }

    /*private void addData() {
        mahasiswaArrayList = new ArrayList<>();
        mahasiswaArrayList.add(new Dosen("NIDN","NAMA MHS","email","alamat"));
        mahasiswaArrayList.add(new Dosen("NIDN","NAMA MHS","email","alamat"));
    }*/

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
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Mahasiswa dosen = mahasiswaArrayList.get(item.getGroupId());
        if (item.getTitle() == "Update"){
            Intent intent = new Intent(DaftarMahasiswaActivity.this, InsertMhsActivity.class);
            intent.putExtra("id",dosen.getId());
            intent.putExtra("nama",dosen.getNama());
            intent.putExtra("nim",dosen.getNim());
            intent.putExtra("alamat",dosen.getAlamat());
            intent.putExtra("email",dosen.getEmail());
            intent.putExtra("foto",dosen.getFoto());
            intent.putExtra("update",true);
            startActivity(intent);
        }else if (item.getTitle() == "Delete") {
            DataDosenService service = RetrofitClient.getRetrofitInstance().create(DataDosenService.class);
            Call<Mahasiswa> call = service.delMhs(
                    "72170155",dosen.getId());
            call.enqueue(new Callback<Mahasiswa>() {
                @Override
                public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                    Intent intent = new Intent(DaftarMahasiswaActivity.this, DaftarMahasiswaActivity.class);
                    startActivity(intent);

                }

                @Override
                public void onFailure(Call<Mahasiswa> call, Throwable t) {
                    Toast.makeText(DaftarMahasiswaActivity.this, "Failed...", Toast.LENGTH_LONG).show();
                }

            });
        }

        return super.onContextItemSelected(item);
    }
}
