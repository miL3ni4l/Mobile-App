package com.e.tugasprogmob.Adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.e.tugasprogmob.Model.Mahasiswa;
import com.e.tugasprogmob.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MhsAdapter extends RecyclerView.Adapter<MhsAdapter.ViewHolder>{
    private ArrayList<Mahasiswa> dataList;
    private Context context;

    public MhsAdapter(ArrayList<Mahasiswa> dataList){
        this.dataList=dataList;
    }

    @NonNull
    @Override
    public MhsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_dosen,parent,false);
        context = parent.getContext();
        return new MhsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MhsAdapter.ViewHolder holder, int position) {
        holder.txtNim.setText(dataList.get(position).getNim());
        holder.txtNama.setText(dataList.get(position).getNama());
        holder.txtEmail.setText(dataList.get(position).getEmail());
        holder.txtAlamat.setText(dataList.get(position).getAlamat());
        holder.img.getLayoutParams().width=200;
        holder.img.getLayoutParams().height=200;
        if (dataList.get(position).getFoto()!=null){
            Picasso.with(this.context)
                    .load("https://kpsi.fti.ukdw.ac.id/progmob/" +dataList.get(position).getFoto())
                    .into(holder.img);
        }
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnCreateContextMenuListener{
        private TextView txtNim,txtNama, txtEmail, txtAlamat;
        private ImageView img;
        private CardView cd;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNim = itemView.findViewById(R.id.txtNIDN);
            txtNama = itemView.findViewById(R.id.txtNama);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            txtAlamat = itemView.findViewById(R.id.txtAlamat);
            img = itemView.findViewById(R.id.imgFoto);
            cd = itemView.findViewById(R.id.rvdd);
            itemView.setOnCreateContextMenuListener(this);

        }
        @Override
        public void onCreateContextMenu(ContextMenu Menu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            Menu.setHeaderTitle("Update atau Delete?");
            Menu.add(this.getAdapterPosition(),view.getId(),0,"Update");
            Menu.add(this.getAdapterPosition(),view.getId(),0,"Delete");
        }

    }
}
