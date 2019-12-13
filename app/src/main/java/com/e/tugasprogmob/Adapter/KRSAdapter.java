package com.e.tugasprogmob.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.tugasprogmob.Model.KRS;
import com.e.tugasprogmob.R;

import java.util.ArrayList;

public class KRSAdapter extends RecyclerView.Adapter<KRSAdapter.ViewHolder> {

    private ArrayList<KRS> dataList;

    public KRSAdapter(ArrayList<KRS> dataList){
        this.dataList=dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_matkul,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtKode.setText(dataList.get(position).getKode());
        holder.txtHari.setText(dataList.get(position).getHari());
        holder.txtSesi.setText(dataList.get(position).getSesi());
        holder.txtSks.setText(dataList.get(position).getSks());
        holder.txtDosen.setText(dataList.get(position).getDosen());
        holder.txtJumlah.setText(dataList.get(position).getJumlah());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtKode, txtHari, txtSesi, txtSks, txtDosen, txtJumlah;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtKode = itemView.findViewById(R.id.txtNIDN);
            txtHari = itemView.findViewById(R.id.txtGelar);
            txtSesi = itemView.findViewById(R.id.txtEmail);
            txtSks = itemView.findViewById(R.id.txtAlamat);
            txtDosen = itemView.findViewById(R.id.txtDosen);
            txtJumlah = itemView.findViewById(R.id.txtJumlah);

        }
    }
}
