package com.example.gads2020leaderboardapplicationproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SkillIQAdapter extends RecyclerView.Adapter<SkillIQAdapter.MyViewHolder> {
    List<SkillIQLeadersData> skillIQLeadersDataList;
    Context context;

    public SkillIQAdapter(List<SkillIQLeadersData> skillIQLeadersDataList, Context context) {
        this.skillIQLeadersDataList = skillIQLeadersDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.skilliq_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(skillIQLeadersDataList.get(position).getName());
        holder.score.setText(String.valueOf(skillIQLeadersDataList.get(position).getScore()) + " skill IQ Score, ");
        holder.country.setText(skillIQLeadersDataList.get(position).getCountry());
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(skillIQLeadersDataList.get(position).getBadgeUrl())
                .into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked" + skillIQLeadersDataList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return skillIQLeadersDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        TextView country;
        TextView score;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageIQ);
            name = (TextView) itemView.findViewById(R.id.nameIQ);
            country = (TextView) itemView.findViewById(R.id.countryIQ);
            score = (TextView) itemView.findViewById(R.id.skillIQ);
            cardView = (CardView) itemView.findViewById(R.id.cardIQ);
        }
    }
}
