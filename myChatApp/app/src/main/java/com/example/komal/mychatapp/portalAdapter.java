package com.example.komal.mychatapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class portalAdapter extends RecyclerView.Adapter<portalAdapter.ViewHolder> {

private Context context;
private List<portalDetails> portalDetailsList;


        int position;
public portalAdapter(Context context,List<portalDetails> details){
        this.context=context;
        this.portalDetailsList=details;
        }

@Override
public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
final View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cards_layout,parent,false);

        return new ViewHolder(itemView);
        }


@Override
public void onBindViewHolder(ViewHolder holder,final int position) {
        //this.position=position;
        holder.userName.setText(portalDetailsList.get(position).getUserName());
        holder.Tag.setText(portalDetailsList.get(position).getTag());
        holder.Question.setText(portalDetailsList.get(position).getQuestion());

}


@Override
public int getItemCount() {
        return portalDetailsList.size();
        }


public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView userName;
    public TextView Tag;
    public TextView Question;



    public ViewHolder(View itemView) {
        super(itemView);
        userName =(TextView)itemView.findViewById(R.id.userName);
        Tag=(TextView)itemView.findViewById(R.id.tag);
        Question=itemView.findViewById(R.id.question_view);
    }


}
}