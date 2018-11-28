package com.fpoly.dong.assignment_duan1;

import android.app.Dialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
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


        AnhXa();
        AddRecyclerview();
    }
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
