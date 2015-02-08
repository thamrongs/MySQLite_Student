package com.buu.se.s55160026.mysqlite_student;

/**
 * Created by thamrongs on 2/3/15 AD.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Teacher on 3/2/2558.
 */
public class DataBaseWrapper extends SQLiteOpenHelper {
    public static final String STUDENTS = "Students";
    public static final String STUDENT_ID = "id";
    public static final String STUDENT_NAME = "name";
    public static final String STUDENT_MAIL = "mail";
    public static final String STUDENT_PHONE = "phone";
    public static final String STUDENT_MEMO = "memo";

    private static final String DATABASE_NAME = "Students.db";
    private static final int DATABASE_VERSION = 1;

    // creation SQLite statement
    private static final String DATABASE_CREATE = "CREATE TABLE " + STUDENTS
            + "(" + STUDENT_ID + " integer primary key autoincrement, "
            + STUDENT_NAME + " text not null, "
            + STUDENT_MAIL + " text,"
            + STUDENT_PHONE + " text,"
            + STUDENT_MEMO + " text );";

    public DataBaseWrapper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // you should do some logging in here
        // ..

        db.execSQL("DROP TABLE IF EXISTS " + STUDENTS);
        onCreate(db);
    }


}