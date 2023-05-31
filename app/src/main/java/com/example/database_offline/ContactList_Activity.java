package com.example.database_offline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ContactList_Activity extends AppCompatActivity {

    FloatingActionButton fab;
    private EditText name, number;
    private Button add,update,delet,show;

    ArrayList<Integer> id=new ArrayList<>();
    ArrayList<String>Nameview=new ArrayList<>();
    ArrayList<String>Numberview=new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        fab=findViewById(R.id.fab);
        recyclerView=findViewById(R.id.recyclerView);
        LinearLayoutManager manager=new LinearLayoutManager(ContactList_Activity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        database db=new database(ContactList_Activity.this);




        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dialog=new Dialog(ContactList_Activity.this);
                dialog.setContentView(R.layout.activity_main);

                name=dialog.findViewById(R.id.name_txt);
                number=dialog.findViewById(R.id.number_txt);
                add=dialog.findViewById(R.id.add_btn);
                update=dialog.findViewById(R.id.update_btn);
                delet=dialog.findViewById(R.id.delet_btn);


                dialog.show();
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String addname=name.getText().toString();
                        String addnumber=number.getText().toString();
                        db.addcontact(addname,addnumber);
                        Cursor cursor=db.showcontact();
                        while (cursor.moveToNext())
                        {
                            id.add(cursor.getInt(0));
                            Nameview.add(cursor.getString(1));
                            Numberview.add(cursor.getString(2));
                        }
//                        recycler_adapter adapter=new recycler_adapter(ContactList_Activity.this,id,Nameview,Numberview);
//                        recyclerView.setAdapter(adapter);
                        dialog.dismiss();
                    }
                });
                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String addname=name.getText().toString();
                        String addnumber=number.getText().toString();
                        db.updatecontact(id.get(1),addname,addnumber);
                        Cursor cursor=db.showcontact();
                        while (cursor.moveToNext())
                        {
                            id.add(cursor.getInt(0));
                            Nameview.add(cursor.getString(1));
                            Numberview.add(cursor.getString(2));
                        }
//                        recycler_adapter adapter=new recycler_adapter(ContactList_Activity.this,id,Nameview,Numberview);
//                        recyclerView.setAdapter(adapter);
                        dialog.dismiss();
                    }
                });
                delet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        db.deletconcact();
                        Cursor cursor=db.showcontact();
                        while (cursor.moveToNext())
                        {
                            id.add(cursor.getInt(0));
                            Nameview.add(cursor.getString(1));
                            Numberview.add(cursor.getString(2));
                        }

                        dialog.dismiss();
                    }
                });
            }
        });
        Cursor cursor=db.showcontact();
        while (cursor.moveToNext())
        {
            id.add(cursor.getInt(0));
            Nameview.add(cursor.getString(1));
            Numberview.add(cursor.getString(2));
        }

        recycler_adapter adapter=new recycler_adapter(ContactList_Activity.this,id,Nameview,Numberview);
        recyclerView.setAdapter(adapter);

    }
}