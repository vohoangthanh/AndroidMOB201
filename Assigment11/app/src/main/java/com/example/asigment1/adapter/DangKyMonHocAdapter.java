package com.example.asigment1.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asigment1.R;
import com.example.asigment1.model.MonHoc;

import java.util.ArrayList;

public class DangKyMonHocAdapter extends RecyclerView.Adapter<DangKyMonHocAdapter.ViewHoler>{
    private Context context;
    private ArrayList<MonHoc> list;
    private int id;

    public DangKyMonHocAdapter(Context context, ArrayList<MonHoc> list, int id) {
        this.id = id;
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_dangkymonhoc,parent,false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        holder.txtCode.setText(String.valueOf(list.get(position).getCode()));
        holder.txtName.setText(list.get(position).getName());
        holder.txtTeacher.setText(list.get(position).getTeacher());

        if (list.get(position).getIsRegister() == id ){
            holder.btnDangKy.setText("Hủy đăng ký");
            holder.btnDangKy.setBackgroundColor(Color.RED);
        }else {
            holder.btnDangKy.setText("Đăng ký");
            holder.btnDangKy.setBackgroundColor(Color.GREEN);
        }

        holder.btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder{

        TextView txtCode ,txtName, txtTeacher;
        Button btnDangKy;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            txtCode = itemView.findViewById(R.id.txtCode);
            txtName = itemView.findViewById(R.id.txtName);
            txtTeacher = itemView.findViewById(R.id.txtTeacher);
            btnDangKy = itemView.findViewById(R.id.btnDangKy);
        }
    }
}
