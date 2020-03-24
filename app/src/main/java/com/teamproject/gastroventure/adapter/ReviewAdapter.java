package com.teamproject.gastroventure.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.teamproject.gastroventure.R;
import com.teamproject.gastroventure.datainterface.ReviewInterface;
import com.teamproject.gastroventure.vo.ReviewVo;

import java.util.ArrayList;

/**
 * Created by 82108 on 2020-03-12.
 */
public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.BoardViewHolder> {

    private ArrayList<ReviewVo> reviewList;
    private Context context;
   // private ReviewFragment reviewFragment;
    private ReviewInterface reviewInterface;

    public ReviewAdapter(ArrayList<ReviewVo> arrayList, Context context, ReviewInterface reviewInterface) {
        this.reviewList = arrayList;
        this.context = context;
        this.reviewInterface = reviewInterface;
    }

    @NonNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
        BoardViewHolder boardViewHolder = new BoardViewHolder(view);

        //reviewFragment = new ReviewFragment();

        return boardViewHolder;
    }

    @Override
    //각 아이템들에 대한 매칭을 시켜준다
    public void onBindViewHolder(@NonNull BoardViewHolder holder, int position) {
        //holder.reviewVo = (ReviewVo) reviewList.get(position);

        Glide.with(holder.itemView)
                .load(reviewList.get(position).getMenu_image())
                .into(holder.review_img);
        holder.review_store_name.setText(reviewList.get(position).getStore_name());
        holder.review_menu.setText(reviewList.get(position).getMenu());
        holder.review_rating.setRating((float) reviewList.get(position).getRating_num());
    }

    @Override
    public int getItemCount() {
        return (reviewList != null ? reviewList.size() : 0);
    }

    public class BoardViewHolder extends RecyclerView.ViewHolder {
        ImageView review_img;
        TextView review_store_name;
        TextView review_menu;
        RatingBar review_rating;

        public BoardViewHolder(@NonNull View itemView) {
            super(itemView);

            this.review_img = itemView.findViewById(R.id.iv_review_image);
            this.review_store_name = itemView.findViewById(R.id.tv_review_store_name);
            this.review_menu = itemView.findViewById(R.id.tv_review_menu);
            this.review_rating = itemView.findViewById(R.id.review_list_rating);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String key = reviewList.get(getAdapterPosition()).getReview_key();

                    reviewInterface.dataDetail(key);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    String key = reviewList.get(getAdapterPosition()).getReview_key();
                    Log.d("리뷰키!!!!", key);

                    reviewInterface.dataRemove(key);

                    //reviewFragment.dataRemove(context, key);
                    return false;
                }
            });
        }
    }
}
