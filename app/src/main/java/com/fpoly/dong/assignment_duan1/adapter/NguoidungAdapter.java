package com.fpoly.dong.assignment_duan1.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.fpoly.dong.assignment_duan1.R;
import com.fpoly.dong.assignment_duan1.holder.User_holder;
import com.fpoly.dong.assignment_duan1.model.User;
import com.fpoly.dong.assignment_duan1.sqlDAO.UserDAO;


import java.util.List;

@SuppressWarnings("UnusedAssignment")
public class NguoidungAdapter extends RecyclerView.Adapter<User_holder> {
    private final Context context;

    private final List<User> userlist;
    private final UserDAO userDAO;


    public NguoidungAdapter(Context context, List<User> userlist, UserDAO userDAO) {
        this.context = context;
        this.userlist = userlist;
        this.userDAO = userDAO;
    }

    @NonNull
    @Override
    public User_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.cardview_user,viewGroup,false);
        return new User_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  User_holder holder, final int position) {
        User nguoiDung=userlist.get(position);
        holder.txtname.setText(nguoiDung.getTenNguoiDung());
        holder.txtemail.setText(nguoiDung.getEmail()+"");

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userlist.size()<=1){
                    Toast.makeText(context, "Không thể Xóa", Toast.LENGTH_SHORT).show();
                }else {
                    userDAO.deleteUser(userlist.get(position).getUsername());
                    userlist.remove(position);
                    notifyDataSetChanged();
                }

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setTitle(userlist.get(position).getTenNguoiDung());


            }
        });

    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }
}
