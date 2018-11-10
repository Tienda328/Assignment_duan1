package com.fpoly.dong.assignment_duan1;

public interface Constant {
    /*Bang User*/
    // Ten Bang
    String TABLE_USER = "User";

    // Ten Cot
    String COLUMN_USERNAME = "userName";
    String COLUMN_PASSWORD = "Password";
    String COLUMN_EMAIL = "Email";
    String COLUMN_NAME = "hoTen";

    // Cau lenh tao bang
    String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + "(" +

            COLUMN_USERNAME + " NVARCHAR(50) PRIMARY KEY," +

            COLUMN_PASSWORD + " NVARCHAR(50)," +

            COLUMN_EMAIL+ " NCHAR(10)," +

            COLUMN_NAME + " NVARCHAR(50)" +

            ")";



}
