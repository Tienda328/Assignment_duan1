
package com.fpoly.dong.assignment_duan1;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fpoly.dong.assignment_duan1.adapter.NguoidungAdapter;
import com.fpoly.dong.assignment_duan1.databasa.DatabaseHelper;
import com.fpoly.dong.assignment_duan1.model.User;
import com.fpoly.dong.assignment_duan1.sqlDAO.UserDAO;

import java.util.List;


public class LoginActivity extends AppCompatActivity{
    private SharedPreferences sharedPreferences;
    private CheckBox cb;
    private EditText edtUserName, edtPassWord;
    private TextView txtForgotPassword;
    private NguoidungAdapter nguoiDungAdapter;
    private EditText email;
    private TextView txtMatKhau;
    private Button  dangnhap;
    private FloatingActionButton flbForMatText;

    private List<User> users;
    private DatabaseHelper databaseHelper;


    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        edtUserName = findViewById(R.id.edtUsername);
        edtPassWord = findViewById(R.id.edtPasswrod);
        cb= findViewById(R.id.cb);
        txtForgotPassword = findViewById(R.id.txtForgotpassword);
        dangnhap = findViewById(R.id.dangnhap);
        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KiemTra();
//                setCheckBox();
            }
        });

        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        userDAO = new UserDAO(databaseHelper);
        User user = new User(
                "dat",
                "123456",
                "Tien Dat",
                0,
                "09256421258");
        userDAO.insertUser(user);


        Click();

    }
//    void setCheckBox(){
//        SharedPreferences.Editor editor=sharedPreferences.edit();
//        if (cb.isChecked()){
//            editor.putString("TenNguoiDung",edtUserName.getText().toString().trim());
//            editor.putString("MatKhau",edtPassWord.getText().toString().trim());
//            editor.putBoolean("check",true);
//            editor.commit();
//
//        }else {
//            editor.putString("TenNguoiDung","");
//            editor.putString("MatKhau","");
//            editor.putBoolean("check",false);
//            editor.commit();
//        }
//    }

    private void Click() {
        edtUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtUserName.setTextSize(15f);
            }
        });
        edtPassWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtPassWord.setTextSize(15f);
            }
        });


        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(LoginActivity.this);
                dialog.setContentView(R.layout.dialog_quanmatkhau);

                Button gui = dialog.findViewById(R.id.gui);
                Button huy = dialog.findViewById(R.id.huy);
                email = dialog.findViewById(R.id.maila);
                txtMatKhau = dialog.findViewById(R.id.txtMatKhau);

                gui.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        String a = "(\\w)+\\@((\\w)+\\.)+(\\w{2,4})";
                        String b = email.getText().toString();

                        if (b.matches(a)) {
                            txtMatKhau.setText("Mật khẩu của bạn:deocodau");
                        } else {
                            email.setError("Email không hợp lệ");
                        }
                    }
                });

                huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });


                dialog.show();
            }
        });

    }
    private void KiemTra() {
        String name = edtUserName.getText().toString().trim();
        String pass = edtPassWord.getText().toString().trim();

        if (name.equals("")) {
            edtUserName.setError(getString(R.string.error_UserName));
            return;
        }

        String[] b = {"!", "~", "@", "#", "$", "%", "^", "&", "*", "*", "(", ")", "_", "-", "=", "+", "[", "]", ";", ":", "\\", "|", "?", "/", "<", ">", ".", ",", "'"};
        //Toast.makeText(this, ""+b.length, Toast.LENGTH_SHORT).show();
        for (String aB : b) {
            if (name.contains(aB)) {
                edtUserName.setError(getString(R.string.error_Ki_Tu_Dac_Bite));
                return;
            }

        }

        if (pass.equals("")) {
            edtPassWord.setError(getString(R.string.error_UserName));
            return;
        }

        for (String aB : b) {
            if (pass.contains(aB)) {

                edtPassWord.setError(getString(R.string.error_Ki_Tu_Dac_Bite));
                return;
            }

        }

        if (pass.length() < 6) {

            edtPassWord.setError(getString(R.string.error_PassWord_It_Hon_6Ki_Tu));
            return;
        }

        User user = userDAO.getUserByUsername(name);

        if (user == null){
            Toast.makeText(LoginActivity.this,
                    getString(R.string.notify_wrong_username_or_password),
                    Toast.LENGTH_SHORT).show();
        }else {

            // lay ra password tu DB cua User
            String passwordInDB = user.getPassword();

            // so sanh 2 mat khau, neu giong thi cho vao Home va nguoc lai
            if (passwordInDB.equals(pass)){
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                finish();
            }else {
                Toast.makeText(LoginActivity.this,
                        getString(R.string.notify_wrong_username_or_password),
                        Toast.LENGTH_SHORT).show();
            }
        }

    }


    public void dangky(View view) {
       addUser();
    }
    public void addUser() {
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("Đăng Ký");

        dialog.setContentView(R.layout.dialog_add_user);

        final EditText edtPassWord;
        EditText edtConfirmPassword;
        final EditText edtEmail;
        final EditText edittennguoidung;
        final EditText edtUserName;

        edtUserName = dialog.findViewById(R.id.edtUserName);
        edtPassWord = dialog.findViewById(R.id.edtPassWord);
        edtConfirmPassword = dialog.findViewById(R.id.edtConfirmPassword);
        edtEmail = dialog.findViewById(R.id.editEmail);
        edittennguoidung = dialog.findViewById(R.id.edittennguoidung);


        dialog.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                User user = new User();
                user.setUsername(edtUserName.getText().toString().trim());
                user.setEmail(edtEmail.getText().toString().trim());
                user.setTenNguoiDung(edittennguoidung.getText().toString().trim());
                user.setPassword(edtPassWord.getText().toString().trim());

                userDAO.insertUser(user);

                // cap nhat len giao dien
                // add vao vi tri dau tien
//                users.add(0,user);
//                nguoiDungAdapter.notifyDataSetChanged();


                Toast.makeText(LoginActivity.this,
                        getString(R.string.notify_add_successful), Toast.LENGTH_SHORT).show();
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
}

