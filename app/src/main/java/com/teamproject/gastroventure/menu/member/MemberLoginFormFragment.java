package com.teamproject.gastroventure.menu.member;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.teamproject.gastroventure.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MemberLoginFormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MemberLoginFormFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View view;

    TextView tv_find_id_pwd;
    EditText et_id, et_pwd;
    CheckBox cb_save_id, cb_auto_login;
    Button btn_login;



    public MemberLoginFormFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MemberLoginFormFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MemberLoginFormFragment newInstance(String param1, String param2) {
        MemberLoginFormFragment fragment = new MemberLoginFormFragment();
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
        view =inflater.inflate(R.layout.fragment_member_login_form, container, false);

        // Inflate the layout for this fragment
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

        return view;
    }
}
