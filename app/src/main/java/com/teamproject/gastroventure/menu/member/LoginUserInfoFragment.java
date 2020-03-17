package com.teamproject.gastroventure.menu.member;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.teamproject.gastroventure.R;

public class LoginUserInfoFragment extends Fragment {

    private View view;

    ImageView civ_rank;
    TextView tv_show_nickname, tv_grade, tv_writen_review;
    Button btn_user_info, btn_dodge;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_login_user_info, container, false);

        //등급별 이미지뷰
        civ_rank = view.findViewById(R.id.civ_rank);
        //닉네임, 등급, 작성게시물수 텍스트뷰
        tv_show_nickname = view.findViewById(R.id.tv_show_nickname);
        tv_grade = view.findViewById(R.id.tv_grade);
        tv_writen_review = view.findViewById(R.id.tv_writen_review);
        // 정보수정, 회원탈퇴 버튼
        btn_user_info = view.findViewById(R.id.btn_user_info);
        btn_dodge = view.findViewById(R.id.btn_dodge);

        return view;
    }
}
