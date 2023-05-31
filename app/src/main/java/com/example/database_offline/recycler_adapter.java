package com.example.database_offline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recycler_adapter extends RecyclerView.Adapter<recycler_adapter.User_holder> {
    Context context;
         ArrayList<Integer> id;
         ArrayList<String> nameview;
         ArrayList<String> numberview;
    public recycler_adapter(Context context, ArrayList<Integer> id, ArrayList<String> nameview, ArrayList<String> numberview)
    {
                this.context=context;
                this.id=id;
                this.nameview=nameview;
                this.numberview=numberview;
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
        holder.textView.setText(""+nameview.get(position));
        holder.textView1.setText(""+numberview.get(position));
    }

    @Override
    public int getItemCount() {
        return nameview.size();
    }

    public class User_holder extends RecyclerView.ViewHolder {
        TextView textView,textView1;
        public User_holder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.name_textview);
            textView1=itemView.findViewById(R.id.number_textview);
        }
    }
}
