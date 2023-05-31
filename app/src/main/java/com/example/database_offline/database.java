package com.example.database_offline;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class database extends SQLiteOpenHelper {
    public database(@Nullable Context context) {
        super(context, "Contactbook", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String create="create table contact(id Integer primary key autoincrement,name text,number text)";
            sqLiteDatabase.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addcontact(String addname, String addnumber) {
        String insert="insert into contact(name,number) values('"+addname+"','"+addnumber+"')";
        SQLiteDatabase liteDatabase=getWritableDatabase();
        liteDatabase.execSQL(insert);
    }

    public Cursor showcontact() {
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        String view="select * from contact";
        Cursor cursor=sqLiteDatabase.rawQuery(view,null);
        return cursor;
    }

    public void updatecontact(int id, String addname, String addnumber) {
        String up="update contact set name='"+addname+"',number='"+addnumber+"' where id="+id+"";
        SQLiteDatabase liteDatabase=getWritableDatabase();
        liteDatabase.execSQL(up);
    }

    public void deletconcact() {
        SQLiteDatabase database1=getWritableDatabase();
        String dele="delete from contact ";
        database1.execSQL(dele);
    }
}
