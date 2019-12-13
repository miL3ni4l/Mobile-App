package com.e.tugasprogmob.Admin.Insert;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.e.tugasprogmob.Adapter.DosenAdapter;
import com.e.tugasprogmob.Admin.DaftarDosenActivity;
import com.e.tugasprogmob.Admin.DataDosenService;
import com.e.tugasprogmob.Admin.RetrofitClient;
import com.e.tugasprogmob.MainActivity;
import com.e.tugasprogmob.Model.Dosen;
import com.e.tugasprogmob.R;
import com.squareup.picasso.Picasso;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class InsertDosenActivity extends AppCompatActivity {
    EditText edtNama, edtNidn, edtAlamat, edtEmail, edtGelar, edtFoto, edtPathFoto;
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
        setContentView(R.layout.activity_insert_dosen);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new  String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            },FILE_ACCESS_REQUES_CODE);
        }

        part_image = findViewById(R.id.imgGambar);
        edtPathFoto = findViewById(R.id.txtFoto);

        edtNama = findViewById(R.id.txtNama);
        edtNidn = findViewById(R.id.txtNidn);
        edtAlamat = findViewById(R.id.txtAlamat);
        edtEmail = findViewById(R.id.txtEmail);
        edtGelar = findViewById(R.id.txtGelar);
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
                String[] minetype={"image/jpeg"};
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Open_gallery"),Request_Gallery);
            }
        });
    }
    private void tambah_dosen(){

        if(edtNama.getText().toString().equals("")|edtNidn.getText().toString().equals("")|edtAlamat.getText().toString().equals("")
                |edtEmail.getText().toString().equals("")|edtGelar.getText().toString().equals("")|edtFoto.getText().toString().equals(""))
        {
            Toast.makeText(InsertDosenActivity.this,"Isi semua data terlebih dahulu!",Toast.LENGTH_LONG);
        }else if(update) {
            String Image = ConvertingBitmapToString();
            Call<Dosen> call;
            call = dataDosenService.updateDosen_foto("72170155", id, edtNama.getText().toString(),
                    edtNidn.getText().toString(), edtAlamat.getText().toString(), edtEmail.getText().toString(),
                    edtGelar.getText().toString(), Image);
            call.enqueue(new Callback<Dosen>() {
                @Override
                public void onResponse(Call<Dosen> call, Response<Dosen> response) {
                    Intent intent = new Intent(InsertDosenActivity.this,DaftarDosenActivity.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onFailure(Call<Dosen> call, Throwable t) {
                    Toast.makeText(InsertDosenActivity.this,"Something wrong....",Toast.LENGTH_LONG).show();
                    //System.out.println(t.get);
                }
            });
        }
        else {
            String Image = ConvertingBitmapToString();
            Call<Dosen> call;
            call = dataDosenService.postDosen_foto("72170155", edtNama.getText().toString(),
                    edtNidn.getText().toString(), edtAlamat.getText().toString(), edtEmail.getText().toString(),
                    edtGelar.getText().toString(), Image);
            call.enqueue(new Callback<Dosen>() {
                @Override
                public void onResponse(Call<Dosen> call, Response<Dosen> response) {
                    Intent intent = new Intent(InsertDosenActivity.this,DaftarDosenActivity.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onFailure(Call<Dosen> call, Throwable t) {
                    Toast.makeText(InsertDosenActivity.this,"Something wrong....",Toast.LENGTH_LONG).show();
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
        edtNidn.setText(extras.getString("nidn"));
        edtAlamat.setText(extras.getString("alamat"));
        edtEmail.setText(extras.getString("email"));
        edtGelar.setText(extras.getString("gelar"));
        Image = extras.getString("foto");
        edtFoto.setText("terisi");
        Picasso.with(InsertDosenActivity.this)
                .load("https://kpsi.fti.ukdw.ac.id/progmob/" +extras.getString("foto"))
                .into(part_image);
        part_image.setVisibility(View.VISIBLE);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == Request_Gallery && resultCode == RESULT_OK && data != null){
            Uri path = data.getData();
            try{
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                part_image.setImageBitmap(bitmap);
                part_image.setVisibility(View.VISIBLE);
                //part_image.setEnabled(false);
                //edtPathFoto.setVisibility(View.VISIBLE);
                edtFoto.setText("Terisi");
                edtFoto.setError(null);




            }catch (IOException E){
                E.printStackTrace();
            }
        }
    }

    public  String ConvertingBitmapToString (){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        Imagebyte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(Imagebyte,Base64.DEFAULT);
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
    
    //TAMBAHAN TAS
    public void cek_if_null(){
        if (edtNidn.getText().toString().equals("")) {
            edtNidn.setError("Silahkan mengisi NIDN dosen");
            //valid_email = null;
        }
        if (edtNama.getText().toString().equals("")) {
            edtNama.setError("Silahkan mengisi nama dosen");
            //valid_email = null;
        }
        if (edtAlamat.getText().toString().equals("")) {
            edtAlamat.setError("Silahkan mengisi alamat dosen");
            //valid_email = null;
        }
        if (edtEmail.getText().toString().equals("")) {
            edtEmail.setError("Silahkan mengisi email dosen");
            //valid_email = null;
        }
        if (edtGelar.getText().toString().equals("")) {
            edtGelar.setError("Silahkan mengisi gelar dosen");
            //valid_email = null;
        }
        if (edtFoto.getText().toString().equals("")) {
            edtFoto.setError("Silahkan mengisi foto dosen");
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
                    edt.setError("Nama Dosen Kosong");
                    //valid_email = null;
                }
            }
                                       });
        edtNidn.addTextChangedListener(new TextWatcher() {

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
                Is_Not_Null(edtNidn); // pass your EditText Obj here.
            }
            public void Is_Not_Null(EditText edt) {
                if (edt.getText().toString().equals("")) {
                    edt.setError("NIDN Dosen Kosong");
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
                    edt.setError("Alamat Dosen Kosong");
                    //valid_email = null;
                }
            }
        });

        edtGelar.addTextChangedListener(new TextWatcher() {

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
                Is_Not_Null(edtGelar); // pass your EditText Obj here.
            }
            public void Is_Not_Null(EditText edt) {
                if (edt.getText().toString().equals("")) {
                    edt.setError("Gelar Dosen Kosong");
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
                    edt.setError("Foto Dosen Kosong");
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
