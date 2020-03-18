package com.teamproject.gastroventure.menu.member;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.teamproject.gastroventure.MainActivity;
import com.teamproject.gastroventure.R;
import com.teamproject.gastroventure.util.DialogSampleUtil;
import com.teamproject.gastroventure.vo.UserInfo;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MemberRegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MemberRegisterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View view;

    //fragment_member_register 항목 변수 선언
    EditText et_id,et_pwd,et_pwd_check,et_name,et_tel,et_nickname;
    Button btn_submit,btn_cancel;

    MainActivity main;

    LogoutUserInfoFragment logoutFrag;
    MemberLoginFormFragment loginFrag;

    String id;
    String pwd;
    String pwd_check;
    String name;
    String nickname;
    String tel;

    public MemberRegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MemberRegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MemberRegisterFragment newInstance(String param1, String param2) {
        MemberRegisterFragment fragment = new MemberRegisterFragment();
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
        view = inflater.inflate(R.layout.fragment_member_register, container, false);

        loginFrag = new MemberLoginFormFragment();
        logoutFrag = new LogoutUserInfoFragment();
        main = (MainActivity)getActivity();

        et_id = (EditText)view.findViewById(R.id.et_id);
        et_pwd =(EditText)view.findViewById(R.id.et_pwd);
        et_pwd_check=(EditText)view.findViewById(R.id.et_pwd_check);
        et_name = (EditText)view.findViewById(R.id.et_name);
        et_nickname =(EditText)view.findViewById(R.id.et_nickname);
        et_tel = (EditText)view.findViewById(R.id.et_tel);

        btn_submit =(Button)view.findViewById(R.id.btn_submit);
        btn_cancel =(Button)view.findViewById(R.id.btn_cancel);

        //입력된 항목 값 읽어오기
        id = et_id.getText().toString().trim();
        pwd = et_pwd.getText().toString().trim();
        pwd_check = et_pwd_check.getText().toString().trim();
        name = et_name.getText().toString().trim();
        nickname = et_name.getText().toString().trim();
        tel = et_tel.getText().toString().trim();

        //프래그먼트 이동
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //비밀번호 확인
                if(!pwd_check.equals(pwd)){
                    //비밀번호 확인했을 때 같지않을 경우
                    DialogSampleUtil.showMessageDialog(getContext(),"","비밀번호가 일치하지 않습니다. 다시 확인해주세요");
                    et_pwd_check.setText("");
                    et_pwd_check.requestFocus();

                    return;
                }
                //DB로 인서트

                main.replaceFragment(loginFrag);
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.replaceFragment(logoutFrag);
            }
        });

        return view;
    }
}
