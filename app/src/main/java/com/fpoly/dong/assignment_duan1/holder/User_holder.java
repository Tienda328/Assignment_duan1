package com.fpoly.dong.assignment_duan1.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fpoly.dong.assignment_duan1.R;


public class User_holder extends RecyclerView.ViewHolder {
    public final TextView txtname,txtemail;
    public final ImageView imgView;

    public final ImageView imgDelete;
    public final ImageView imgedit;


    public User_holder(View itemView) {
        super(itemView);
        imgView = itemView.findViewById(R.id.imgView);
        txtname = itemView.findViewById(R.id.txtname);
        txtemail = itemView.findViewById(R.id.txtemail);
        imgDelete = itemView.findViewById(R.id.imgDelete);
        imgedit = itemView.findViewById(R.id.imgEdit);



    }
}
