package com.e.tugasprogmob.Admin;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;

    public static final String link = "https://kpsi.fti.ukdw.ac.id/";

    public static Retrofit getRetrofitInstance(){
        if(retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(link)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
