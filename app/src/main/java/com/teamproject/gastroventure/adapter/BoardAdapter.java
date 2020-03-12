package com.teamproject.gastroventure.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teamproject.gastroventure.R;
import com.teamproject.gastroventure.vo.BoardVo;

import java.util.ArrayList;

/**
 * Created by 82108 on 2020-03-12.
 */
public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardViewHolder> {

    private ArrayList<BoardVo> arrayList;
    private Context context;


    public BoardAdapter(ArrayList<BoardVo> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    //board_item에 대한 View를 생성
    @NonNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.board_item,parent,false);
        BoardViewHolder holder = new BoardViewHolder(view);
        return holder;
    }

    @Override
    //각 아이템들에 대한 매칭을 시켜준다
    public void onBindViewHolder(@NonNull BoardViewHolder holder, int position) {
        holder.board_num.setText(arrayList.get(position).getBoard_num());
        holder.board_title.setText(arrayList.get(position).getBoard_title());
        holder.board_date.setText(arrayList.get(position).getBoard_date());
    }

    @Override
    public int getItemCount() {
        return (arrayList != null? arrayList.size() : 0);

    }

    public class BoardViewHolder extends RecyclerView.ViewHolder {
        TextView board_num;
        TextView board_title;
        TextView board_date;
        public BoardViewHolder(@NonNull View itemView) {
            super(itemView);

            this.board_num = itemView.findViewById(R.id.board_num);
            this.board_title = itemView.findViewById(R.id.board_title);
            this.board_date = itemView.findViewById(R.id.board_date);
        }
    }
}
