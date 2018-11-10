package com.fpoly.dong.assignment_duan1.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.acer.duanmau.holder.My_holder;
import com.example.acer.duanmau.model.User;
import com.example.acer.duanmau.sqlitedao.UserDAO;
import com.tinh.dev.myapplication.R;

import java.util.List;

@SuppressWarnings("UnusedAssignment")
public class NguoidungAdapter extends RecyclerView.Adapter<My_holder> {
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
    public My_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item_cardview,parent,false);
        return new My_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull My_holder holder, final int position) {
        User nguoiDung=userlist.get(position);
        holder.txtChinh.setText(nguoiDung.getTenNguoiDung());
        holder.txtPhu.setText(nguoiDung.getSDT()+"");

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
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

                dialog.setContentView(R.layout.dialog_edit_user);

                EditText edtPassWord;
                EditText edtConfirmPassword;
                final EditText edtName;
                final EditText edtPhone;

                edtPassWord = dialog.findViewById(R.id.edtPassWord);
                edtConfirmPassword = dialog.findViewById(R.id.edtConfirmPassword);
                edtName = dialog.findViewById(R.id.edtName);
                edtPhone = dialog.findViewById(R.id.edtPhone);

                edtName.setText(userlist.get(position).getTenNguoiDung());
                edtPhone.setText(userlist.get(position).getSDT());


                dialog.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        User user = new User();
                        user.setUsername(userlist.get(position).getUsername());
                        user.setTenNguoiDung(edtName.getText().toString().trim());
                        user.setSDT(edtPhone.getText().toString().trim());

                        userDAO.updateUser(user);

                        // cap nhat thay doi len giao dien
                        userlist.get(position).setTenNguoiDung(edtName.getText().toString().trim());
                        userlist.get(position).setSDT(edtPhone.getText().toString().trim());
                        notifyDataSetChanged();

                        Toast.makeText(context,context.getString(R.string.notify_save_successful),Toast.LENGTH_SHORT).show();
                        dialog.dismiss();


                    }
                });
                dialog.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }
}
