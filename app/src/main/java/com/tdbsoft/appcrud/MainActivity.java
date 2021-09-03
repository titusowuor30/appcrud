package com.tdbsoft.appcrud;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mydb;
    EditText editId,editName,editSirname,editMarks;
    Button addButton,loadData,updateData,deleteData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb=new DatabaseHelper(this);
        editId=(EditText)findViewById(R.id.Id);
        editName=(EditText) findViewById(R.id.Name);
        editSirname=(EditText) findViewById(R.id.Sirnamwe);
        editMarks=(EditText) findViewById(R.id.Marks);
        addButton=(Button) findViewById(R.id.Add);
        loadData=(Button) findViewById(R.id.LoadData);
        updateData=(Button) findViewById(R.id.Update);
        deleteData=(Button) findViewById(R.id.Delete);
        AddData();
        LoadData();
        UpdatedData();
        DeletedData();
    }
    public void AddData(){
        addButton.setOnClickListener(
            new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted=mydb.insertData(editName.getText().toString(),editSirname.getText().toString(),editMarks.getText().toString());
                if(isInserted=true){
                    Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(MainActivity.this,"An error occured",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void UpdatedData(){
        updateData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isUpdated=mydb.updateData(editId.getText().toString(),editName.getText().toString(),editSirname.getText().toString(),editMarks.getText().toString());
                        if(isUpdated=true){
                            Toast.makeText(MainActivity.this,"Data Updated",Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(MainActivity.this,"An error occured",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    public void DeletedData(){
        deleteData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int isDeeted=mydb.deletData(editId.getText().toString());
                        if(isDeeted>0){
                            Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(MainActivity.this,"An error occured",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    public void LoadData(){
        loadData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res=mydb.getAllData();
                        if(res.getCount()==0){
                            showDataAsMessage("Error!","No data found!");
                            return;
                        }
                        StringBuffer buffer=new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("Id:"+res.getString(0)+"\n");
                            buffer.append("Name:"+res.getString(1)+"\n");
                            buffer.append("Sir Name:"+res.getString(2)+"\n");
                            buffer.append("Marks:"+res.getString(3)+"\n\n");
                        }
                        //show data
                        showDataAsMessage("Student Details",buffer.toString());
                    }
                });
    }
    public void showDataAsMessage(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}