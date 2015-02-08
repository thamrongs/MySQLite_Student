package com.buu.se.s55160026.mysqlite_student;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class DetailActivity extends Activity {
    private StudentOperations studentDBoperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        studentDBoperation = new StudentOperations(this);
        studentDBoperation.open();

        Intent data = getIntent();
        Student student = (Student) data.getSerializableExtra("student");
        EditText textviewName = (EditText) findViewById(R.id.editTextName);
        EditText textviewMail = (EditText) findViewById(R.id.editTextMail);
        EditText textviewPhone = (EditText) findViewById(R.id.editTextPhone);
        EditText textviewMemo = (EditText) findViewById(R.id.editTextMemo);

        textviewName.setText(student.getName());
        textviewMail.setText(student.getMail());
        textviewPhone.setText(student.getPhone());
        textviewMemo.setText(student.getMemo());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onButtonBackClick(View v){
        finish();
    }

    public void onButtonDeleteClick(View v){

        Intent data = getIntent();
        Student student = (Student) data.getSerializableExtra("student");
        studentDBoperation.deleteStudent(student);
        finish();
    }

    public void onButtonSaveClick(View v){
        Intent data = getIntent();
        Student student = (Student) data.getSerializableExtra("student");

        EditText textName = (EditText) findViewById(R.id.editTextName);
        EditText textMail = (EditText) findViewById(R.id.editTextMail);
        EditText textPhone = (EditText) findViewById(R.id.editTextPhone);
        EditText textMemo = (EditText) findViewById(R.id.editTextMemo);
        studentDBoperation.editStudent(student.getId(),textName.getText().toString(),
                textMail.getText().toString(), textPhone.getText().toString(), textMemo.getText().toString());
        finish();
    }
}
