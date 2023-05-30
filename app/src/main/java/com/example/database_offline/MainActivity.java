package com.example.database_offline;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
EditText name,number;
Button add,update,delet,view;
ArrayList<Integer>id=new ArrayList<>();
ArrayList<String>Nameview=new ArrayList<>();
ArrayList<String>Numberview=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.name_txt);
        number=findViewById(R.id.number_txt);
        add=findViewById(R.id.add_btn);
        update=findViewById(R.id.update_btn);
        delet=findViewById(R.id.delet_btn);
        view=findViewById(R.id.view_btn);

        database db=new database(MainActivity.this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addname=name.getText().toString();
                String addnumber=number.getText().toString();
                db.addcontact(addname,addnumber);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id1=2;
                String addname=name.getText().toString();
                String addnumber=number.getText().toString();
                db.updatecontact(id1,addname,addnumber);
            }
        });
        delet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int deletid=2;
                db.deletconcact(deletid);
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Cursor cursor=db.showcontact();
            while (cursor.moveToNext())
            {
                id.add(cursor.getInt(0));
                Nameview.add(cursor.getString(1));
                Numberview.add(cursor.getString(2));
            }
            }
        });
    }
}