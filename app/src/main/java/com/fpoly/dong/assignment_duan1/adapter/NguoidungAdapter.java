package com.fpoly.dong.assignment_duan1.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
                    AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    builder.setTitle("Xóa");
                    builder.setMessage("Bạn có muốn xóa không?");
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            userDAO.deleteUser(userlist.get(position).getUsername());
                            userlist.remove(position);
                            notifyDataSetChanged();
                            Toast.makeText(context, "Đã Xóa", Toast.LENGTH_SHORT).show();
                        }
                    });

                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    builder.show();
                }

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setTitle(userlist.get(position).getTenNguoiDung());

                dialog.setContentView(R.layout.dialog_edit_user);

                final EditText edtPassWord;
                final EditText edtConfirmPassword;
                final EditText edtName;
                final EditText edtPhone;

                edtPassWord = dialog.findViewById(R.id.edtPassWord);
                edtConfirmPassword = dialog.findViewById(R.id.edtConfirmPassword);
                edtName = dialog.findViewById(R.id.edtName);
                edtPhone = dialog.findViewById(R.id.editEmail);

                edtName.setText(userlist.get(position).getTenNguoiDung());
                edtPhone.setText(userlist.get(position).getEmail());


                dialog.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("NewApi")
                    @Override
                    public void onClick(View view) {
                        final String pass = edtPassWord.getText().toString().trim();
                        final String confin = edtConfirmPassword.getText().toString().trim();

                        User user = new User();
                        user.setUsername(userlist.get(position).getUsername());
                        user.setTenNguoiDung(edtName.getText().toString().trim());
                        user.setEmail(edtPhone.getText().toString().trim());

                        userDAO.updateUser(user);
                        String[] b = {"!", "~", "@", "#", "$", "%", "^", "&", "*", "*", "(", ")", "_", "-", "=", "+", "[", "]", ";", ":", "\\", "|", "?", "/", "<", ">", ".", ",", "'"};
                        if (pass.equals("")){
                            edtPassWord.setError("vui long nhap");
                            edtPassWord.requestFocus();
                            return;
                        }
                        if (pass.length() < 6) {

                            edtPassWord.setError("phai có ít nhất 6 kí tự  ");
                            edtPassWord.requestFocus();
                            return;
                        }
                        for (String aB : b) {
                            if (pass.contains(aB)) {
                                edtPassWord.setError("không được có kí tự đặc biệt");
                                edtPassWord.requestFocus();
                                return;
                            }

                        }

                        if (confin.equals("")){
                            edtConfirmPassword.setError("không được để trống");
                            edtConfirmPassword.requestFocus();
                            return;
                        }
                        if (!confin.equals(pass)){
                            edtConfirmPassword.setError("không khớp mật khẩu");
                            edtConfirmPassword.requestFocus();
                            return;
                        }


                        // cap nhat thay doi len giao dien
                        userlist.get(position).setTenNguoiDung(edtName.getText().toString().trim());
                        userlist.get(position).setEmail(edtPhone.getText().toString().trim());
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
            }        });

    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }
}
