package com.e.tugasprogmob.Admin;

import com.e.tugasprogmob.Model.Dosen;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DataDosenService {
    @GET("api/progmob/dosen/{nim_progmob}")
    Call<ArrayList<Dosen>> getDosenAll(@Path("nim_progmob") String nimProgmob);
    @FormUrlEncoded
    @POST("api/progmob/dosen/create")
    Call<Dosen> postDosen(
                                @Field("nim_progmob") String nimProgmob,
                                @Field("nama") String nama,
                                @Field("nidn") String nidn,
                                @Field("alamat") String alamat,
                                @Field("email") String email,
                                @Field("gelar") String gelar,
                                @Field("foto") String foto);
    @FormUrlEncoded
    @POST("api/progmob/dosen/createfoto")
    Call<Dosen> postDosen_foto(
            @Field("nim_progmob") String nimProgmob,
            @Field("nama") String nama,
            @Field("nidn") String nidn,
            @Field("alamat") String alamat,
            @Field("email") String email,
            @Field("gelar") String gelar,
            @Field("foto") String foto);
    @FormUrlEncoded
    @POST("api/progmob/dosen/update")
    Call<Dosen> updateDosen(
            @Field("nim_progmob") String nimProgmob,
            @Field("id") int id,
            @Field("nama") String nama,
            @Field("nidn") String nidn,
            @Field("alamat") String alamat,
            @Field("email") String email,
            @Field("gelar") String gelar,
            @Field("foto") String foto);
    @FormUrlEncoded
    @POST("api/progmob/dosen/updatewithfoto")
    Call<Dosen> updateDosen_foto(
            @Field("nim_progmob") String nimProgmob,
            @Field("id") int id,
            @Field("nama") String nama,
            @Field("nidn") String nidn,
            @Field("alamat") String alamat,
            @Field("email") String email,
            @Field("gelar") String gelar,
            @Field("foto") String foto);
    @FormUrlEncoded
    @POST("api/progmob/dosen/delete")
    Call<Dosen> delDosen(@Field("nim_progmob") String nimProgmob,
                         @Field("id") int id);
}
