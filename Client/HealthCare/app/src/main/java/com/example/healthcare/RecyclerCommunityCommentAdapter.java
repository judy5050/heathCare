package com.example.healthcare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RecyclerCommunityCommentAdapter extends RecyclerView.Adapter<RecyclerCommunityCommentAdapter.ViewHolder>{
    private ArrayList<RecyclerCommunityCommentItem> lists = new ArrayList<>();

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_community_comment_id;
        private TextView tv_community_comment_content;
        private TextView tv_community_comment_date;
        private TextView tv_community_comment_time;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            tv_community_comment_id = (TextView)itemView.findViewById(R.id.tv_community_comment_id);
            tv_community_comment_content = (TextView)itemView.findViewById(R.id.tv_community_comment_content);
            tv_community_comment_date = (TextView)itemView.findViewById(R.id.tv_community_comment_date);
            tv_community_comment_time = (TextView)itemView.findViewById(R.id.tv_community_comment_time);
        }
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerCommunityCommentAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.recycler_community_comment, parent, false);

        RecyclerCommunityCommentAdapter.ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerCommunityCommentAdapter.ViewHolder holder, int position) {
        RecyclerCommunityCommentItem item = lists.get(position);

        holder.tv_community_comment_id.setText(item.getUserNickName());
        holder.tv_community_comment_content.setText(item.getCommentMsg());
        holder.tv_community_comment_date.setText(item.getCreatedDate().substring(0,10));
        holder.tv_community_comment_time.setText(item.getCreatedDate().substring(11));

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public void addItem(int commentIdx, int userIdx, String userNickName, String commentMsg, String createdDate){
        RecyclerCommunityCommentItem item = new RecyclerCommunityCommentItem();

        item.setCommentIdx(commentIdx);
        item.setUserIdx(userIdx);
        item.setUserNickName(userNickName);
        item.setCommentMsg(commentMsg);
        item.setCreatedDate(createdDate);

        lists.add(item);
    }


}
