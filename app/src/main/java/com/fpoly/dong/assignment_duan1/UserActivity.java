package com.fpoly.dong.assignment_duan1;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fpoly.dong.assignment_duan1.adapter.NguoidungAdapter;
import com.fpoly.dong.assignment_duan1.databasa.DatabaseHelper;
import com.fpoly.dong.assignment_duan1.model.User;
import com.fpoly.dong.assignment_duan1.sqlDAO.UserDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserActivity extends AppCompatActivity {
    private RecyclerView recyclerviewNguoiDung;
    private LinearLayoutManager linearLayoutManager;
    private NguoidungAdapter nguoiDungAdapter;
    private FloatingActionButton flbAdd;

    private List<User> users;
    private Toolbar toolbar;


    private DatabaseHelper databaseHelper;
    private UserDAO userDAO;

//    private EditText edtUserName;
//    private EditText edtPassWord;
//    private EditText edtConfirmPassword;
//    private EditText edittennguoidung;
//    private EditText editEmail;
//    private Button btnSave;
//    private Button btnCancel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Người dùng");
        toolbar.setNavigationIcon(R.drawable.iconback);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        databaseHelper = new DatabaseHelper(this);
        userDAO = new UserDAO(databaseHelper);

        flbAdd=findViewById(R.id.flbAdd);

        flbAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });

//        addkiemtra();
        AnhXa();
        AddRecyclerview();
    }

//    private void addkiemtra() {
//        edtUserName = (EditText) findViewById(R.id.edtUserName);
//        edtPassWord = (EditText) findViewById(R.id.edtPassWord);
//        edtConfirmPassword = (EditText) findViewById(R.id.edtConfirmPassword);
//        edittennguoidung = (EditText) findViewById(R.id.edittennguoidung);
//        editEmail = (EditText) findViewById(R.id.editEmail);
//        btnSave = (Button) findViewById(R.id.btnSave);
//        btnCancel = (Button) findViewById(R.id.btnCancel);
//    }

    private void AddRecyclerview(){
        users = userDAO.getAllUsers();
        nguoiDungAdapter = new NguoidungAdapter(this,users,userDAO);

        recyclerviewNguoiDung.setLayoutManager(linearLayoutManager);
        recyclerviewNguoiDung.setHasFixedSize(true);
        recyclerviewNguoiDung.setAdapter(nguoiDungAdapter);
    }

    private void AnhXa() {
        recyclerviewNguoiDung = findViewById(R.id.recyclerview_NguoiDung);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        users = new ArrayList<>();
        users.clear();
    }

    public void addUser() {
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("Thêm Người Dùng");

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

//                String username = edtUserName.getText().toString().trim();
//                String password = edtPassWord.getText().toString().trim();
//                String ConfirmPassword = edtPassWord.getText().toString().trim();
//                String edtEmail = edtPassWord.getText().toString().trim();
//                String edittennguoidung = edtPassWord.getText().toString().trim();
//
//                if (edtUserName.equals("")) {
//                    edtUserName.setError(getString(R.string.error_UserName));
//                    return;
//                }
//
//                String[] b = {"!", "~", "@", "#", "$", "%", "^", "&", "*", "*", "(", ")", "_", "-", "=", "+", "[", "]", ";", ":", "\\", "|", "?", "/", "<", ">", ".", ",", "'"};
//                //Toast.makeText(this, ""+b.length, Toast.LENGTH_SHORT).show();
//                for (String aB : b) {
//                    if (username.contains(aB)) {
//                        edtUserName.setError(getString(R.string.error_Ki_Tu_Dac_Bite));
//                        return;
//                    }
//
//                }
//
//                if (password.equals("")) {
//                    edtPassWord.setError(getString(R.string.error_UserName));
//                    return;
//                }
//
//                for (String aB : b) {
//                    if (password.contains(aB)) {
//
//                        edtPassWord.setError(getString(R.string.error_Ki_Tu_Dac_Bite));
//                        return;
//                    }
//
//                }
//                if (ConfirmPassword.equals("")) {
//                    edtPassWord.setError(getString(R.string.error_UserName));
//                    return;
//                }
//
//                if (ConfirmPassword!=password) {
//
//                    edtPassWord.setError(getString(R.string.phaigiongpassword));
//                    return;
//                }
//
//
//
//                if (password.length() < 6) {
//
//                    edtPassWord.setError(getString(R.string.error_PassWord_It_Hon_6Ki_Tu));
//                    return;
//                }
//
//                User user = userDAO.getUserByUsername(username);
//
//                if (user == null){
//                    Toast.makeText(UserActivity.this,
//                            getString(R.string.notify_wrong_username_or_password),
//                            Toast.LENGTH_SHORT).show();
//                }else {
//
//                    // lay ra password tu DB cua User
//                    String passwordInDB = user.getPassword();
//
//                    // so sanh 2 mat khau, neu giong thi cho vao Home va nguoc lai
//                    if (passwordInDB.equals(password)){
//                        startActivity(new Intent(UserActivity.this, HomeActivity.class));
//                        finish();
//                    }else {
//                        Toast.makeText(UserActivity.this,
//                                getString(R.string.notify_wrong_username_or_password),
//                                Toast.LENGTH_SHORT).show();
//                    }
//                }
//


                User user = new User();
                user.setUsername(edtUserName.getText().toString().trim());
                user.setEmail(edtEmail.getText().toString().trim());
                user.setTenNguoiDung(edittennguoidung.getText().toString().trim());
                user.setPassword(edtPassWord.getText().toString().trim());

                userDAO.insertUser(user);

                // cap nhat len giao dien
                // add vao vi tri dau tien
                users.add(0,user);
                nguoiDungAdapter.notifyDataSetChanged();


                Toast.makeText(UserActivity.this,
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
