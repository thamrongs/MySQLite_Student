package com.buu.se.s55160026.mysqlite_student;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.app.ListActivity;
import android.widget.ListView;

import java.util.List;


public class MainActivity extends ListActivity {

    private StudentOperations studentDBoperation;

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        ArrayAdapter<Student> adapter = (ArrayAdapter<Student>) getListAdapter();
        Student stud = null;
        stud = (Student) getListAdapter().getItem(position);
        Intent data = new Intent(MainActivity.this, DetailActivity.class);
        data.putExtra("student",stud);
        startActivity(data);

/*      // For Send Multivalues
        ArrayList<Student> studentlist = new ArrayList<Student>();
        Student s;
        s = new Student();
        studentlist.add(s);
        s = new Student();
        studentlist.add(s);*/


/*      // Delete student
        stud = (Student) getListAdapter().getItem(position);
        studentDBoperation.deleteStudent(stud);
        adapter.remove(stud);

        String msg = "You delete selected " + ((TextView) v).getText();
        msg += " at position " + String.valueOf(position);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();*/
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentDBoperation = new StudentOperations(this);
        studentDBoperation.open();

        List<Student> values = studentDBoperation.getAllStudentsWithSQL();

        ArrayAdapter<Student> adapter = new ArrayAdapter<Student>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


//    public void addUser(View view) {
//
//        ArrayAdapter<Student> adapter = (ArrayAdapter<Student>) getListAdapter();
//
//        EditText textName = (EditText) findViewById(R.id.editTextName);
//        EditText textMail = (EditText) findViewById(R.id.editTextMail);
//        EditText textPhone = (EditText) findViewById(R.id.editTextMail);
//        EditText textMemo = (EditText) findViewById(R.id.editTextMail);
//        Student stud = studentDBoperation.addStudent(textName.getText().toString(),
//                textMail.getText().toString(), textPhone.getText().toString(), textMemo.getText().toString());
//
//        adapter.add(stud);
//        startActivityForResult(new Intent(this, AddActivity.class),1);
//
//    }
    public void addUser(View view){
        startActivityForResult(new Intent(this, AddActivity.class),1);
    }

    @Override
    protected void onPause() {
        super.onPause();
        studentDBoperation.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        studentDBoperation.open();

        List<Student> values = studentDBoperation.getAllStudentsWithSQL();

        ArrayAdapter<Student> adapter = new ArrayAdapter<Student>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }


    public void deleteFirstUser(View view) {

        ArrayAdapter<Student> adapter = (ArrayAdapter<Student>) getListAdapter();
        Student stud = null;

        if (getListAdapter().getCount() > 0) {
            stud = (Student) getListAdapter().getItem(0);
            studentDBoperation.deleteStudent(stud);
            adapter.remove(stud);
        }

    }
}
