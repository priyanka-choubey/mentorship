package com.example.komal.mychatapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context context;
    private List<userDetails> userDetailsList;
    int position;
    public UserAdapter(Context context,List<userDetails> details){
        this.context=context;
        this.userDetailsList=details;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_content_user,parent,false);
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                UsersDetailsActivity.chatWith = String.valueOf(userDetailsList.get(position).username);
//
//                Intent intent=new Intent(context, ChatRoomActivity.class);
//                context.startActivity(intent);
//            }
//        });
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {
        //this.position=position;
        holder.userName.setText(userDetailsList.get(position).getUsername());
        holder.Designation.setText(userDetailsList.get(position).getDesignation());

        holder.userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UsersDetailsActivity.chatWith = String.valueOf(userDetailsList.get(position).username);

                Intent intent=new Intent(context, ChatRoomActivity.class);
                context.startActivity(intent);

            }
        });

    }


    @Override
    public int getItemCount() {
        return userDetailsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView userName;
        public TextView Designation;


        public ViewHolder(View itemView) {
            super(itemView);
            userName =(TextView)itemView.findViewById(R.id.listview_item_title);
            Designation=(TextView)itemView.findViewById(R.id.listview_item_short_description);

        }


    }
}
