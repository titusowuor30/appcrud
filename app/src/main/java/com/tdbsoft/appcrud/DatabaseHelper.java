package com.tdbsoft.appcrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="student.db";
    public static final String TABLE_NAME="student_table";
    public static final String COL_1="ID";
    public static final String COL_2="NAME";
    public static final String COL_3="SIRNAME";
    public static final String COL_4="MARKS";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
           db.execSQL("CREATE TABLE "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT UNIQUE NOT NULL,SIRNAME TEXT,MARKS INTEGER NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
     db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
     onCreate(db);
    }
    public boolean insertData(String name,String sirname,String marks){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contval=new ContentValues();
        contval.put(COL_2,name);
        contval.put(COL_3,sirname);
        contval.put(COL_4,marks);
        long result=db.insert(TABLE_NAME,null,contval);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return res;
    }
    public boolean updateData(String id,String name,String sirname,String marks){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contval=new ContentValues();
        contval.put(COL_1,id);
        contval.put(COL_2,name);
        contval.put(COL_3,sirname);
        contval.put(COL_4,marks);
        db.update(TABLE_NAME,contval,"ID = ?",new String[]{id});
        return true;
    }
    public Integer deletData(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?",new String[]{id});
    }
}
