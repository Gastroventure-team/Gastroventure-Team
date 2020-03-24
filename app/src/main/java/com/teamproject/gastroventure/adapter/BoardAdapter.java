package com.teamproject.gastroventure.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teamproject.gastroventure.MainActivity;
import com.teamproject.gastroventure.R;
import com.teamproject.gastroventure.menu.board.BoardFragment;
import com.teamproject.gastroventure.vo.BoardVo;

import java.util.ArrayList;

/**
 * Created by 82108 on 2020-03-16.
 */
public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardViewHolder> {

    ArrayList<BoardVo> arrayList;
    Context context;
    BoardFragment boardFragment;
    MainActivity mainActivity;

    public interface OnClickListener {
        void onItemClicked(int position);
    }

    private OnClickListener lClickListener = null;

    public void setOnLongClickListener(OnClickListener listener) {
        lClickListener = listener;
    }

    public BoardAdapter(ArrayList<BoardVo> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        this.mainActivity = (MainActivity) context;
    }

    @NonNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.board_item, parent, false);
        BoardViewHolder holder = new BoardViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BoardViewHolder holder, final int position) {
        holder.boardVo = (BoardVo) arrayList.get(holder.getAdapterPosition());

        Log.d("12312321321", "넘어온거 사이즈가 " + arrayList.size());

        holder.board_num.setText(String.valueOf(arrayList.get(position).getBoard_num()));
        holder.board_title.setText(arrayList.get(position).getBoard_title());
        holder.board_date.setText(arrayList.get(position).getBoard_date());

    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class BoardViewHolder extends RecyclerView.ViewHolder {
        TextView board_num;
        TextView board_title;
        TextView board_date;
        BoardVo boardVo;

        public BoardViewHolder(@NonNull final View itemView) {
            super(itemView);
            this.board_num = itemView.findViewById(R.id.board_num);
            this.board_title = itemView.findViewById(R.id.board_title);
            this.board_date = itemView.findViewById(R.id.board_date);
            this.boardVo = new BoardVo();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boardFragment.onItemClick(Integer.parseInt(board_num.getText().toString()));
                    Log.d("123123", "position : " + board_num.getText().toString());

                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    String key = boardVo.getBoard_key();
                    Log.d("12312321321", key);
                    boardFragment.onLongClick(key);

                    return false;
                }

            });
        }
    }
}
