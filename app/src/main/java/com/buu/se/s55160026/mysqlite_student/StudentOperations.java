package com.buu.se.s55160026.mysqlite_student;

/**
 * Created by thamrongs on 2/3/15 AD.
 */

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class StudentOperations {
    // Database fields
    private DataBaseWrapper dbHelper;
    private String[] STUDENT_TABLE_COLUMNS = {
            DataBaseWrapper.STUDENT_ID,
            DataBaseWrapper.STUDENT_NAME,
            DataBaseWrapper.STUDENT_MAIL,
            DataBaseWrapper.STUDENT_PHONE,
            DataBaseWrapper.STUDENT_MEMO
    };
    private SQLiteDatabase database;

    public StudentOperations(Context context) {
        dbHelper = new DataBaseWrapper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Student addStudent(String name) {

        ContentValues values = new ContentValues();

        values.put(DataBaseWrapper.STUDENT_NAME, name);

        long studId = database.insert(DataBaseWrapper.STUDENTS, null, values);

        // now that the student is created return it ...
        Cursor cursor = database.query(DataBaseWrapper.STUDENTS,
                STUDENT_TABLE_COLUMNS, DataBaseWrapper.STUDENT_ID + " = "
                        + studId, null, null, null, null);

        cursor.moveToFirst();

        Student newStudent = parseStudent(cursor);
        cursor.close();
        return newStudent;
    }

    public Student addStudent(String name, String mail, String phone, String memo) {

        ContentValues values = new ContentValues();

        values.put(DataBaseWrapper.STUDENT_NAME, name);
        values.put(DataBaseWrapper.STUDENT_MAIL, mail);
        values.put(DataBaseWrapper.STUDENT_PHONE, phone);
        values.put(DataBaseWrapper.STUDENT_MEMO, memo);

        long studId = database.insert(DataBaseWrapper.STUDENTS, null, values);

        // now that the student is created return it ...
        Cursor cursor = database.query(DataBaseWrapper.STUDENTS,
                STUDENT_TABLE_COLUMNS, DataBaseWrapper.STUDENT_ID + " = "
                        + studId, null, null, null, null);

        cursor.moveToFirst();

        Student newStudent = parseStudent(cursor);
        cursor.close();
        return newStudent;
    }
    public void editStudent(long id,String name, String mail, String phone, String memo) {

        ContentValues values = new ContentValues();

        values.put(DataBaseWrapper.STUDENT_NAME, name);
        values.put(DataBaseWrapper.STUDENT_MAIL, mail);
        values.put(DataBaseWrapper.STUDENT_PHONE, phone);
        values.put(DataBaseWrapper.STUDENT_MEMO, memo);

        database.update(DataBaseWrapper.STUDENTS, values, "id = ?", new String[] { ""+id });;


    }

    public void deleteStudent(Student student) {
        long id = student.getId();
        System.out.println("Student deleted with id: " + id);
        database.delete(DataBaseWrapper.STUDENTS, DataBaseWrapper.STUDENT_ID
                + " = " + id, null);
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<Student>();

        Cursor cursor = database.query(DataBaseWrapper.STUDENTS,
                STUDENT_TABLE_COLUMNS, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Student student = parseStudent(cursor);
            students.add(student);
            cursor.moveToNext();
        }

        cursor.close();
        return students;
    }

    public List<Student> getAllStudentsWithSQL() {
        List<Student> students = new ArrayList<Student>();

        Cursor cursor= database.rawQuery("SELECT * FROM students",null);


        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Student student = parseStudent(cursor);
            students.add(student);
            cursor.moveToNext();
        }

        cursor.close();
        return students;
    }




    private Student parseStudent(Cursor cursor) {
        Student student = new Student();
        student.setId((cursor.getInt(0)));
        student.setName(cursor.getString(1));
        student.setMail(cursor.getString(2));
        student.setPhone(cursor.getString(3));
        student.setMemo(cursor.getString(4));
        return student;
    }
}