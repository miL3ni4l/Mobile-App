package com.e.tugasprogmob.Admin.Insert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.e.tugasprogmob.Admin.DaftarDosenActivity;
import com.e.tugasprogmob.Admin.DaftarMahasiswaActivity;
import com.e.tugasprogmob.Admin.DataDosenService;
import com.e.tugasprogmob.Admin.RetrofitClient;
import com.e.tugasprogmob.Model.Dosen;
import com.e.tugasprogmob.Model.Mahasiswa;
import com.e.tugasprogmob.R;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class InsertMhsActivity extends AppCompatActivity {
    EditText edtNama, edtNim, edtAlamat, edtEmail, edtFoto, edtPathFoto;
    Button btnSimpan, btnBack;
    DataDosenService dataDosenService;
    DaftarDosenActivity a;
    boolean update = false;
    int id;
    final  int Request_Gallery = 58;
    Bitmap bitmap;
    ImageView part_image;
    final int FILE_ACCESS_REQUES_CODE=58;
    byte[] Imagebyte;
    String Image, valid_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_mhs);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new  String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            },FILE_ACCESS_REQUES_CODE);
        }

        part_image = findViewById(R.id.imgGambar);
        edtPathFoto = findViewById(R.id.txtFoto);

        edtNama = findViewById(R.id.txtNama);
        edtNim = findViewById(R.id.txtNidn);
        edtAlamat = findViewById(R.id.txtAlamat);
        edtEmail = findViewById(R.id.txtEmail);
        edtFoto = findViewById(R.id.txtFoto);



        cek_update();
        cek_if_null();

        InitializeUI();

        dataDosenService = RetrofitClient.getRetrofitInstance()
                .create(DataDosenService.class);
        btnSimpan = findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tambah_dosen();
            }
        });
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                String[] minetype={"satu/jpeg"};
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Open_gallery"),Request_Gallery);
            }
        });
    }

    private void tambah_dosen(){

        if(edtNama.getText().toString().equals("")|edtNim.getText().toString().equals("")|edtAlamat.getText().toString().equals("")
                |edtEmail.getText().toString().equals("")|edtFoto.getText().toString().equals(""))
        {
            Toast.makeText(InsertMhsActivity.this,"Isi semua data terlebih dahulu!",Toast.LENGTH_LONG);
        }else if(update) {
            //String Image = ConvertingBitmapToString();
            Call<Mahasiswa> call;
            call = dataDosenService.updateMhs("72170155", id, edtNama.getText().toString(),
                    edtNim.getText().toString(), edtAlamat.getText().toString(), edtEmail.getText().toString(), edtFoto.getText().toString());
            call.enqueue(new Callback<Mahasiswa>() {
                @Override
                public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                    Intent intent = new Intent(InsertMhsActivity.this,DaftarMahasiswaActivity.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onFailure(Call<Mahasiswa> call, Throwable t) {
                    Toast.makeText(InsertMhsActivity.this,"Something wrong....",Toast.LENGTH_LONG).show();
                    //System.out.println(t.get);
                }
            });
        }
        else {

            Call<Mahasiswa> call;
            call = dataDosenService.postMhs("72170155", edtNama.getText().toString(),
                    edtNim.getText().toString(), edtAlamat.getText().toString(), edtEmail.getText().toString(), edtFoto.getText().toString());
            call.enqueue(new Callback<Mahasiswa>() {
                @Override
                public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                    Intent intent = new Intent(InsertMhsActivity.this, DaftarMahasiswaActivity.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onFailure(Call<Mahasiswa> call, Throwable t) {
                    Toast.makeText(InsertMhsActivity.this,"Something wrong....",Toast.LENGTH_LONG).show();
                    //System.out.println(t.get);
                }
            });
        }
    }

    void cek_update()
    {
        Bundle extras = getIntent().getExtras();
        if (extras == null){
            return;
        }
        update = extras.getBoolean("update");
        edtNama.setText(extras.getString("nama"));
        id = extras.getInt("id");
        edtNim.setText(extras.getString("nim"));
        edtAlamat.setText(extras.getString("alamat"));
        edtEmail.setText(extras.getString("email"));
        Image = extras.getString("foto");
        edtFoto.setText(extras.getString("foto"));
        /*Picasso.with(InsertMhsActivity.this)
                .load("https://kpsi.fti.ukdw.ac.id/progmob/" +extras.getString("foto"))
                .into(part_image);
        part_image.setVisibility(View.VISIBLE);*/
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case FILE_ACCESS_REQUES_CODE:
                if(grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED){

                }
                break;
        }
    }

    public void cek_if_null(){
        if (edtNim.getText().toString().equals("")) {
            edtNim.setError("Silahkan mengisi nim mahasiswa");
            //valid_email = null;
        }
        if (edtNama.getText().toString().equals("")) {
            edtNama.setError("Silahkan mengisi nama mahasiswa");
            //valid_email = null;
        }
        if (edtAlamat.getText().toString().equals("")) {
            edtAlamat.setError("Silahkan mengisi alamat mahasiswa");
            //valid_email = null;
        }
        if (edtEmail.getText().toString().equals("")) {
            edtEmail.setError("Silahkan mengisi email mahasiswa");
            //valid_email = null;
        }
        if (edtFoto.getText().toString().equals("")) {
            edtFoto.setError("Silahkan mengisi Foto mahasiswa");
            //valid_email = null;
        }
    }

    public void InitializeUI() {

        edtNama.addTextChangedListener(new TextWatcher() {

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
                Is_Not_Null(edtNama);
            }
            public void Is_Not_Null(EditText edt) {
                if (edt.getText().toString().equals("")) {
                    edt.setError("Silahkan mengisi nama mahasiswa");
                    //valid_email = null;
                }
            }
        });
        edtNim.addTextChangedListener(new TextWatcher() {

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
                Is_Not_Null(edtNim); // pass your EditText Obj here.
            }
            public void Is_Not_Null(EditText edt) {
                if (edt.getText().toString().equals("")) {
                    edt.setError("Silahkan mengisi nim mahasiswa");
                    //valid_email = null;
                }
            }
        });

        edtAlamat.addTextChangedListener(new TextWatcher() {

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
                Is_Not_Null(edtAlamat); // pass your EditText Obj here.
            }
            public void Is_Not_Null(EditText edt) {
                if (edt.getText().toString().equals("")) {
                    edt.setError("Silahkan mengisi Alamat mahasiswa");
                    //valid_email = null;
                }
            }
        });

        edtFoto.addTextChangedListener(new TextWatcher() {

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
                Is_Not_Null(edtFoto); // pass your EditText Obj here.
            }
            public void Is_Not_Null(EditText edt) {
                if (edt.getText().toString().equals("")) {
                    edt.setError("Silahkan mengisi Foto mahasiswa");
                    //valid_email = null;
                }
            }
        });

        edtEmail.addTextChangedListener(new TextWatcher() {

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
                Is_Valid_Email(edtEmail);
            }

            public void Is_Valid_Email(EditText edt) {
                if (edt.getText().toString().equals("")) {
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
            }
        });
    }
}
