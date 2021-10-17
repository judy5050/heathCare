package com.example.healthcare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RecyclerCommunityAdapter extends RecyclerView.Adapter<RecyclerCommunityAdapter.ViewHolder> {
    private ArrayList<RecyclerCommunityItem> lists = new ArrayList<>();
    private String jwt;
    private int useridx;

    RecyclerCommunityAdapter(String jwt, int useridx){
        this.jwt = jwt;
        this.useridx = useridx;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_community_title;
        private TextView tv_community_date;
        private TextView tv_community_time;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            tv_community_title = (TextView)itemView.findViewById(R.id.tv_community_title);
            tv_community_date = (TextView)itemView.findViewById(R.id.tv_community_date);
            tv_community_time = (TextView)itemView.findViewById(R.id.tv_community_time);
        }
    }
    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.recycler_community, parent, false);

        RecyclerCommunityAdapter.ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        RecyclerCommunityItem item = lists.get(position);

        holder.tv_community_title.setText(item.getTitle());
        holder.tv_community_date.setText(item.getDate().substring(0,10));
        holder.tv_community_time.setText(item.getDate().substring(11));

        selItem(holder, item);

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public void addItem(long idx, String nickname, String message, String title, long useridx, String date){
        RecyclerCommunityItem item = new RecyclerCommunityItem();
        item.setIdx(idx);
        item.setNickname(nickname);
        item.setMessage(message);
        item.setTitle(title);
        item.setUseridx(useridx);
        item.setDate(date);

        lists.add(item);
    }

    private void selItem(ViewHolder holder, RecyclerCommunityItem item){
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lookup
                Intent intent = new Intent(v.getContext(), CommunityLookupActivity.class);
                intent.putExtra("useridx", useridx);
                intent.putExtra("idx", (int)item.getIdx());
                intent.putExtra("jwt", jwt);
                ((Activity)v.getContext()).startActivity(intent);
            }
        });
    }
}
