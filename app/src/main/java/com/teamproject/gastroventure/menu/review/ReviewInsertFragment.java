package com.teamproject.gastroventure.menu.review;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.teamproject.gastroventure.MainActivity;
import com.teamproject.gastroventure.R;
import com.teamproject.gastroventure.vo.ReviewVo;

public class ReviewInsertFragment extends Fragment {
    private View view;

    private MainActivity main;


    private FirebaseDatabase reviewDatabase;
    private DatabaseReference databaseReference;

    private EditText et_store_name;
    private EditText et_menu;
    private EditText et_review_content;
    private RatingBar review_rating;
    private Button btn_image_add;
    private Button btn_review_insert;
    private Button btn_review_cancel;

    private Double rating_num = 0.0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_review_insert_form, container, false);

        main = (MainActivity) getActivity();

        et_store_name = view.findViewById(R.id.review_insert_name);
        et_menu = view.findViewById(R.id.review_insert_name);
        et_review_content = view.findViewById(R.id.review_insert_content);

        review_rating = view.findViewById(R.id.review_insert_rating);

        btn_image_add = view.findViewById(R.id.review_insert_image);
        btn_review_insert = view.findViewById(R.id.review_insert_btn);
        btn_review_cancel = view.findViewById(R.id.review_cancel_btn);

        reviewDatabase = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동
        databaseReference = reviewDatabase.getReference(); // DB 테이블 연결

        review_rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rating_num = Double.parseDouble(String.valueOf(rating));
            }
        });

        btn_image_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_review_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String store_name = et_store_name.getText().toString();
                String menu = et_menu.getText().toString();
                String review_content = et_review_content.getText().toString();

                if(store_name.isEmpty()){
                    Toast.makeText(getContext(), "상호명을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(menu.isEmpty()){
                    Toast.makeText(getContext(), "메뉴를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(review_content.isEmpty()){
                    review_content = "";
                }

                if(rating_num == 0.0){
                    Toast.makeText(getContext(), "별점은 1점 이상 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                ReviewVo reviewVo = new ReviewVo();
                reviewVo.setStore_name(store_name);
                reviewVo.setMenu(menu);
                reviewVo.setReview_content(review_content);
                reviewVo.setRating_num(rating_num);


                databaseReference.child("Review").push().setValue(reviewVo); // child 는 컬럼의 기본키?

                main.replaceFragment(new ReviewFragment());
            }
        });

        btn_review_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.replaceFragment(new ReviewFragment());
            }
        });

        return view;
    }
}
