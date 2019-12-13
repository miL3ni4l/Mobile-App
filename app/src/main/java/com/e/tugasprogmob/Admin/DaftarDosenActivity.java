package com.e.tugasprogmob.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.e.tugasprogmob.Adapter.DosenAdapter;
import com.e.tugasprogmob.Admin.Insert.InsertDosenActivity;
import com.e.tugasprogmob.Admin.Insert.InsertMhsActivity;
import com.e.tugasprogmob.MainActivity;
import com.e.tugasprogmob.Model.Dosen;
import com.e.tugasprogmob.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarDosenActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DosenAdapter mahasiswaAdapter;
    private ArrayList<Dosen> mahasiswaArrayList;
    DataDosenService dataDosenService;
    public static DaftarDosenActivity ma;
    public boolean update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_dosen);
        mahasiswaArrayList = new ArrayList<>();
        dataDosenService = RetrofitClient.getRetrofitInstance()
                .create(DataDosenService.class);
        ma=this;
        getAllDataDosen();
        recyclerView = findViewById(R.id.rvdd);

        Call<ArrayList<Dosen>> call = dataDosenService.getDosenAll("0168");// memanggil data yang sudah ada
        call.enqueue(new Callback<ArrayList<Dosen>>() {
            @Override
            public void onResponse(Call<ArrayList<Dosen>> call, Response<ArrayList<Dosen>> response) {
                mahasiswaArrayList = response.body();
                mahasiswaAdapter = new DosenAdapter(response.body());

                //for(Dosen dosen:response.body())
                //{
                    //mahasiswaArrayList.add(new Dosen(dosen.getNidn(),dosen.getNama(),dosen.getGelar(),dosen.getEmail(),dosen.getAlamat()));
                   // mahasiswaArrayList = response.body();
                    //mahasiswaAdapter = new DosenAdapter(dosen);
                    /*System.out.println("Nama :"+dosen.getNama());
                    System.out.println("NIDN :"+dosen.getNidn());
                    System.out.println(mahasiswaArrayList);*/
               // }



                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DaftarDosenActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(mahasiswaAdapter);


            }

            @Override
            public void onFailure(Call<ArrayList<Dosen>> call, Throwable t) {
                Toast.makeText(DaftarDosenActivity.this,"Something wrong....",Toast.LENGTH_LONG).show();
                //System.out.println(t.get);
            }
        });
        registerForContextMenu(recyclerView);

    }

    public void getAllDataDosen()
    {

    }

    private void addData() {
        //mahasiswaArrayList = new ArrayList<>();
        mahasiswaArrayList.add(new Dosen("NIDN - NAMA DOSEN","Gelar","email","alamat"));
        mahasiswaArrayList.add(new Dosen("NIDN - NAMA DOSEN","Gelar","email","alamat"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.utama,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(DaftarDosenActivity.this, InsertDosenActivity.class);
        switch (item.getItemId()){
            case R.id.item1:
                //update = false;
                startActivity(intent);
                return true;
            /*case R.id.item2:
                intent.putExtra("update",true);
                intent.putExtra("nama","sapa");
                //update = true;
                startActivity(intent);
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Dosen dosen = mahasiswaArrayList.get(item.getGroupId());
        if (item.getTitle() == "Update"){
            Intent intent = new Intent(DaftarDosenActivity.this,InsertDosenActivity.class);
            intent.putExtra("id",dosen.getId());
            intent.putExtra("nama",dosen.getNama());
            intent.putExtra("nidn",dosen.getNidn());
            intent.putExtra("alamat",dosen.getAlamat());
            intent.putExtra("email",dosen.getEmail());
            intent.putExtra("gelar",dosen.getGelar());
            intent.putExtra("foto",dosen.getFoto());
            intent.putExtra("update",true);
            startActivity(intent);
        }else if (item.getTitle() == "Delete") {
            DataDosenService service = RetrofitClient.getRetrofitInstance().create(DataDosenService.class);
            Call<Dosen> call = service.delDosen(
                    "72170155",dosen.getId());
            call.enqueue(new Callback<Dosen>() {
                @Override
                public void onResponse(Call<Dosen> call, Response<Dosen> response) {
                    Intent intent = new Intent(DaftarDosenActivity.this, DaftarDosenActivity.class);
                    startActivity(intent);

                }

                @Override
                public void onFailure(Call<Dosen> call, Throwable t) {
                    Toast.makeText(DaftarDosenActivity.this, "Failed...", Toast.LENGTH_LONG).show();
                }

            });
        }

        return super.onContextItemSelected(item);
    }
}
