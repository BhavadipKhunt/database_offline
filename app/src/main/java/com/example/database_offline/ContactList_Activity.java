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
    private EditText nametxt, numbertxt;
    private Button add,update,delet;

//    ArrayList<Integer> id=new ArrayList<>();
//    ArrayList<String>Nameview=new ArrayList<>();
//    ArrayList<String>Numberview=new ArrayList<>();
    ArrayList<Contacts_Data> contactList=new ArrayList<>();
    RecyclerView recyclerView;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        fab=findViewById(R.id.fab);
        recyclerView=findViewById(R.id.recyclerView);
        LinearLayoutManager manager=new LinearLayoutManager(ContactList_Activity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        database db=new database(ContactList_Activity.this);
        showData(db);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(ContactList_Activity.this);
                dialog.setContentView(R.layout.activity_main);
                nametxt=dialog.findViewById(R.id.name_txt);
                numbertxt=dialog.findViewById(R.id.number_txt);
                add=dialog.findViewById(R.id.add_btn);
                update=dialog.findViewById(R.id.update_btn);
                delet=dialog.findViewById(R.id.delet_btn);
                dialog.show();
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String addname = nametxt.getText().toString(); // bbb
                        String addnumber = numbertxt.getText().toString(); //888
                        db.addcontact(addname,addnumber);//
                        contactList.clear();
                        showData(db);
                        dialog.dismiss();
                    }
                });


            }
        });



    }

    private void showData(database db) {
        cursor=db.showcontact();

        while (cursor.moveToNext())
        {
            Contacts_Data contacts_data=new Contacts_Data();
            contacts_data.setId(cursor.getInt(0));
            contacts_data.setName(cursor.getString(1));
            contacts_data.setNumber(cursor.getString(2));
            contactList.add(contacts_data);

        }
        //Log.d("TTT", "showData: Name="+Nameview);
        recycler_adapter adapter=new recycler_adapter(ContactList_Activity.this,contactList);
        recyclerView.setAdapter(adapter);

    }
}