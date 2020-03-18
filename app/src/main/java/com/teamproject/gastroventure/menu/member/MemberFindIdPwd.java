package com.teamproject.gastroventure.menu.member;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.teamproject.gastroventure.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MemberFindIdPwd#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MemberFindIdPwd extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View view;

    EditText et_find_id_name, et_find_id_tel, et_find_pwd_name, et_find_pwd_id, et_find_pwd_tel;
    Button btn_find_id, btn_find_pwd;

    String find_id_name, find_id_tel, find_pwd_name, find_pwd_id, find_pwd_tel;

    public MemberFindIdPwd() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MemberFindIdPwd.
     */
    // TODO: Rename and change types and number of parameters
    public static MemberFindIdPwd newInstance(String param1, String param2) {
        MemberFindIdPwd fragment = new MemberFindIdPwd();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_member_find_id_pwd, container, false);

        //아이디 찾기
        et_find_id_name = view.findViewById(R.id.et_find_id_name);
        et_find_id_tel =view.findViewById(R.id.et_find_id_tel);
        btn_find_id = view.findViewById(R.id.btn_find_id);

        //비밀번호찾기
        et_find_pwd_name = view.findViewById(R.id.et_find_pwd_name);
        et_find_pwd_id =view.findViewById(R.id.et_find_pwd_id);
        et_find_pwd_tel = view.findViewById(R.id.et_find_pwd_tel);
        btn_find_pwd = view.findViewById(R.id.btn_find_pwd);

        //아이디 찾기 입력값
        find_id_name = et_find_id_name.getText().toString().trim();
        find_id_tel = et_find_id_tel.getText().toString().trim();
        //비밀번호 찾기 입력값
        find_pwd_name = et_find_pwd_name.getText().toString().trim();
        find_pwd_id = et_find_pwd_id.getText().toString().trim();
        find_pwd_tel = et_find_pwd_tel.getText().toString().trim();

        btn_find_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DB에서 이름과 전화번호 검색 후 일치하는 아이디 다이얼로그로 띄워주기

            }
        });

        btn_find_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DB에서 아이디, 이름, 전화번호 검색 후 일치하는 계정 비밀번호 재설정 진행
            }
        });


        return view;
    }
}
