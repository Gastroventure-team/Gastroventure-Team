package com.teamproject.gastroventure.menu.member;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.teamproject.gastroventure.R;

public class LogoutUserInfoFragment extends Fragment {

    private View view;

    TextView tv_find_id_pwd;
    EditText et_id, et_pwd;
    CheckBox cb_save_id, cb_auto_login;
    Button btn_login, btn_register;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_logout_user_info, container, false);

        //아이디,비밀번호 찾기
        tv_find_id_pwd = (TextView)view.findViewById(R.id.tv_find_id_pwd);
        //아이디,비밀번호 입력
        et_id =(EditText)view.findViewById(R.id.et_id);
        et_pwd =(EditText)view.findViewById(R.id.et_pwd);
        //자동로그인, 아이디저장
        cb_save_id = (CheckBox)view.findViewById(R.id.cb_save_id);
        cb_auto_login = (CheckBox)view.findViewById(R.id.cb_auto_login);
        //로그인, 회원가입 버튼
        btn_login = (Button)view.findViewById(R.id.btn_login);
        btn_register = (Button)view.findViewById(R.id.btn_register);

        return view;
    }
}
