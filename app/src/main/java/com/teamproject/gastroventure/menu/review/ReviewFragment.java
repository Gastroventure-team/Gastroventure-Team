package com.teamproject.gastroventure.menu.review;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.teamproject.gastroventure.R;
import com.teamproject.gastroventure.adapter.ReviewAdapter;
import com.teamproject.gastroventure.vo.ReviewVo;

import java.util.ArrayList;

public class ReviewFragment extends Fragment {
    private final String TAG = "ReviewFrag Log";

    private View view;
    private FloatingActionButton insert_fbtn;
    private RecyclerView review_rcv_list;
    private RecyclerView.Adapter reviewAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ReviewVo> reviewList;
    private FirebaseDatabase reviewDatabase;
    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_review, container, false);

        review_rcv_list = view.findViewById(R.id.review_rcv_list); // 아디 연결
        review_rcv_list.setHasFixedSize(true); // 리사이클러뷰 기존성능 강화
        layoutManager = new LinearLayoutManager(getContext());
        review_rcv_list.setLayoutManager(layoutManager);
        reviewList = new ArrayList<ReviewVo>(); // User 객체를 담을 어레이 리스트 (어댑터쪽으로)

        reviewDatabase = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동
        databaseReference = reviewDatabase.getReference("Review"); // DB 테이블 연결

        FloatingActionButton fab = view.findViewById(R.id.review_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        dateRead();

        return view;
    }

    public void dateRead(){
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // 파이어베이스 데이터베이스의 데이터를 받아오는 곳
                reviewList.clear(); // 기존 배열리스트가 존재하지않게 초기화
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복문으로 데이터 List를 추출해냄
                    ReviewVo reviewVo = snapshot.getValue(ReviewVo.class); // 만들어뒀던 User 객체에 데이터를 담는다.
                    reviewList.add(reviewVo); // 담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비

                    Log.d(TAG, " 메뉴이름! " + reviewVo.getMenu());
                }
                Log.d(TAG, "리스트 사이즈 : " + reviewList.size());
                reviewAdapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // 디비를 가져오던중 에러 발생 시
                Log.e("ReviewFragment", String.valueOf(databaseError.toException())); // 에러문 출력
            }
        });

        reviewAdapter = new ReviewAdapter(reviewList, getContext());
        review_rcv_list.setAdapter(reviewAdapter); // 리사이클러뷰에 어댑터 연결
    }
}
