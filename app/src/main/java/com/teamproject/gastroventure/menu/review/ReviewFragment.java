package com.teamproject.gastroventure.menu.review;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.teamproject.gastroventure.MainActivity;
import com.teamproject.gastroventure.R;
import com.teamproject.gastroventure.adapter.ReviewAdapter;
import com.teamproject.gastroventure.datainterface.ReviewInterface;
import com.teamproject.gastroventure.util.DialogSampleUtil;
import com.teamproject.gastroventure.vo.ReviewVo;

import java.util.ArrayList;

public class ReviewFragment extends Fragment implements ReviewInterface {
    private final String TAG = "ReviewFrag Log";
    private final String CHILE_NAME = "Review";

    private View view;
    private FloatingActionButton insert_fbtn;
    private RecyclerView review_rcv_list;
    private RecyclerView.Adapter reviewAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ReviewVo> reviewList = new ArrayList<ReviewVo>();

    private FirebaseDatabase reviewDatabase;
    private DatabaseReference databaseReference;

    private MainActivity main;
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_review, container, false);

        main = (MainActivity)getActivity();

        review_rcv_list = view.findViewById(R.id.review_rcv_list); // 아디 연결
        review_rcv_list.setHasFixedSize(true); // 리사이클러뷰 기존성능 강화
        layoutManager = new LinearLayoutManager(getContext());
        review_rcv_list.setLayoutManager(layoutManager);

        reviewDatabase = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동
        databaseReference = reviewDatabase.getReference(); // DB 테이블 연결

        insert_fbtn = view.findViewById(R.id.review_fab);
        insert_fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main.replaceFragment(new ReviewInsertFragment());
            }
        });

        dataRead();

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        showProgress("로딩중...");
    }

    public void dataRead(){
        databaseReference.child(CHILE_NAME).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // 파이어베이스 데이터베이스의 데이터를 받아오는 곳
                reviewList.clear(); // 기존 배열리스트가 존재하지않게 초기화
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복문으로 데이터 List를 추출해냄
                    snapshot.getKey();
                    ReviewVo reviewVo = snapshot.getValue(ReviewVo.class); // 만들어뒀던 User 객체에 데이터를 담는다.
                    reviewVo.setReview_key(snapshot.getKey());
                    reviewList.add(reviewVo); // 담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비

                    Log.d(TAG, " 메뉴이름! " + reviewVo.getMenu());
                    Log.d(TAG, " 리뷰 키! " + reviewVo.getReview_key());
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

        reviewAdapter = new ReviewAdapter(reviewList, getContext(), this);
        review_rcv_list.setAdapter(reviewAdapter); // 리사이클러뷰에 어댑터 연결

        hideProgress();
    }

    @Override
    public void dataRemove(final String key) {
        //final String review_key = key;
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {//Yes
                    try {
                        Log.d("키 맞다고!!!!", key);
                        databaseReference.child(CHILE_NAME).child(key).removeValue();

                        dataRead();
                        //reviewAdapter.notifyDataSetChanged();
                    } catch (Exception e){
                        Log.d(TAG, e.getMessage());
                    }
                }
            }
        };

        DialogSampleUtil.showConfirmDialog(getContext(), "", "선택한 리뷰를 삭제 하시겠습니까?", handler);

    }

    @Override
    public void dataDetail(String key) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main_frame, ReviewDetailFragment.newInstance(key)).commit();
    }

    public void showProgress(String message) {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(message);
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        progressDialog.show();
    }

    public void hideProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
