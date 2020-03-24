package com.teamproject.gastroventure.menu.review;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.teamproject.gastroventure.MainActivity;
import com.teamproject.gastroventure.R;
import com.teamproject.gastroventure.adapter.ReviewAdapter;
import com.teamproject.gastroventure.vo.ReviewVo;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReviewDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReviewDetailFragment extends Fragment {
    private static final String SELECT_KEY = "select_key";
    private final String CHILE_NAME = "Review";

    private MainActivity main;
    private View view;

    private TextView detail_store_name;
    private TextView detail_menu;
    private TextView detail_content;

    private RatingBar detail_review_rating;

    private Button detail_modify_btn;
    private Button detail_cancel_btn;

    private FirebaseDatabase reviewDatabase;
    private DatabaseReference databaseReference;

    private String select_key;

    public ReviewDetailFragment() {}

    public static ReviewDetailFragment newInstance(String select_key) {
        ReviewDetailFragment fragment = new ReviewDetailFragment();
        Bundle args = new Bundle();
        args.putString(SELECT_KEY, select_key);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            select_key = getArguments().getString(SELECT_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        main = (MainActivity)getActivity();

        view = inflater.inflate(R.layout.fragment_review_detail, container, false);
        Log.d(SELECT_KEY,"key = " + select_key);

        detail_store_name = view.findViewById(R.id.review_detail_store_name);
        detail_menu = view.findViewById(R.id.review_detail_menu);
        detail_content = view.findViewById(R.id.review_detail_content);

        detail_review_rating = view.findViewById(R.id.review_detail_rating);

        detail_modify_btn = view.findViewById(R.id.review_detail_modify);
        detail_cancel_btn = view.findViewById(R.id.review_detail_cancel);

        reviewDatabase = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동
        databaseReference = reviewDatabase.getReference(); // DB 테이블 연결

        detail_modify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.main_frame, ReviewModifyFragment.newInstance(select_key)).commit();
            }
        });

        detail_cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.replaceFragment(new ReviewFragment());
            }
        });

        dataRead();

        return view;
    }

    public void dataRead(){
        databaseReference.child(CHILE_NAME).child(select_key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    ReviewVo reviewVo = dataSnapshot.getValue(ReviewVo.class); // 만들어뒀던 User 객체에 데이터를 담는다.
                    reviewVo.setReview_key(dataSnapshot.getKey());

                    detail_store_name.setText(reviewVo.getStore_name() + " - ");
                    detail_menu.setText(reviewVo.getMenu());
                    detail_content.setText(reviewVo.getReview_content());

                    detail_review_rating.setRating((float) reviewVo.getRating_num());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // 디비를 가져오던중 에러 발생 시
                Log.e("ReviewFragment", String.valueOf(databaseError.toException())); // 에러문 출력
            }
        });
    }
}
