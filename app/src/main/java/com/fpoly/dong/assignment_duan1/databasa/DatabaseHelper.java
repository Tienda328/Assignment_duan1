package com.fpoly.dong.assignment_duan1.databasa;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fpoly.dong.assignment_duan1.Constant;

public class DatabaseHelper extends SQLiteOpenHelper implements Constant {


public DatabaseHelper(Context context) {
    super(context, "Fc", null, 1);

}

    public Cursor getData(String sql){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        return sqLiteDatabase.rawQuery(sql,null);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE_USER);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        onCreate(sqLiteDatabase);

    }
}
