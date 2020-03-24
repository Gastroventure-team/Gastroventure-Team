package com.teamproject.gastroventure.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.teamproject.gastroventure.R;
import com.teamproject.gastroventure.util.DialogSampleUtil;
import com.teamproject.gastroventure.vo.ReviewInsertImgVo;

import java.util.ArrayList;

public class ReviewModifyImgAdapter extends RecyclerView.Adapter<ReviewModifyImgAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<ReviewInsertImgVo> list;

    public ReviewModifyImgAdapter(Context context, ArrayList<ReviewInsertImgVo> list) {
        super();
        this.context = context;
        this.list = list;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView memo_image;

        public MyViewHolder(View itemView) {
            super(itemView);
            memo_image = itemView.findViewById(R.id.memo_image);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Handler handler = new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            if (msg.what == 1) {//Yes

                            }
                        }
                    };

                    DialogSampleUtil.showConfirmDialog(context, "", "이미지를 삭제하시겠습니까?", handler);

                }
            });
        }
    }

    private void removeItemView(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, list.size());
    }

    private void removeMemo(String img_uri) {
        Toast.makeText(context.getApplicationContext(), img_uri + " 이미지 삭제 완료", Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

//        ImageItem item = list.get(position);
//
//        Uri uri = Uri.parse(item.getImage());
//
//        holder.memo_image.setImageURI(uri);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}