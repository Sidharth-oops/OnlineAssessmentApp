package com.example.quizc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {
    Context context;
    ArrayList<score>arraycontact;

    public adapter(Context context, ArrayList<score> arraycontact){
        this.context=context;
        this.arraycontact=arraycontact;

    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.activity_history_main,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String score= String.valueOf(arraycontact.get(position).s);
        String genre=arraycontact.get(position).genre;
        holder.score.setText("SCORE="+score);
        holder.g.setText("GENRE="+genre);
        if(genre.equals("JAVASCRIPT")){
            holder.imageView.setImageResource(R.drawable.javascript);
        }
        if(genre.equals("HTML")){
            holder.imageView.setImageResource(R.drawable.html);
        }
        if(genre.equals("LINUX")){
            holder.imageView.setImageResource(R.drawable.duck);
        }
        if(genre.equals("SQL")){
            holder.imageView.setImageResource(R.drawable.sql);
        }


    }

    @Override
    public int getItemCount() {
        return arraycontact.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder {
        TextView g;
        TextView score;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            g=itemView.findViewById(R.id.tvGenreHI);
            score=itemView.findViewById(R.id.tvScoreHI);
            imageView=itemView.findViewById(R.id.ivHistoryItem);
        }


    }
}
