package com.buu.se.s55160026.mysqlite_student;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class AddActivity extends Activity {

    private StudentOperations studentDBoperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        studentDBoperation = new StudentOperations(this);
        studentDBoperation.open();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
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

    public void onButtonSaveClick(View v){

        EditText textName = (EditText) findViewById(R.id.editTextName);
        EditText textMail = (EditText) findViewById(R.id.editTextMail);
        EditText textPhone = (EditText) findViewById(R.id.editTextPhone);
        EditText textMemo = (EditText) findViewById(R.id.editTextMemo);
        Student stud = studentDBoperation.addStudent(textName.getText().toString(),
                textMail.getText().toString(), textPhone.getText().toString(), textMemo.getText().toString());

        finish();
    }
}
