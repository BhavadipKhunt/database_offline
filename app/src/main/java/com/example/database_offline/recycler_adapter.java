package com.example.database_offline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recycler_adapter extends RecyclerView.Adapter<recycler_adapter.User_holder> {
    Context context;
    ArrayList<Contacts_Data> contactList;
    public recycler_adapter(ContactList_Activity context, ArrayList<Contacts_Data> contactList) {
        this.context=context;
        this.contactList=contactList;
    }

    @NonNull
    @Override
    public recycler_adapter.User_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.contact_iteam,parent,false);
        User_holder userHolder=new User_holder(view);
        return userHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull recycler_adapter.User_holder holder, int position) {
        holder.textView.setText(""+contactList.get(position).getName());
        holder.textView1.setText(""+contactList.get(position).getNumber());
        holder.optionMenu.setOnClickListener(new View.OnClickListener() {
            database db=new database(context);
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context,holder.optionMenu);
                popupMenu.getMenuInflater().inflate(R.menu.menu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if(menuItem.getItemId()==R.id.delete)
                        {
                            db.deletconcact(contactList.get(holder.getAdapterPosition()).getId());
                            contactList.remove(holder.getAdapterPosition());
                            notifyDataSetChanged();
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class User_holder extends RecyclerView.ViewHolder {
        TextView textView,textView1;
        ImageButton optionMenu;
        public User_holder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.name_textview);
            textView1=itemView.findViewById(R.id.number_textview);
            optionMenu=itemView.findViewById(R.id.optionMenu);

        }
    }
}
