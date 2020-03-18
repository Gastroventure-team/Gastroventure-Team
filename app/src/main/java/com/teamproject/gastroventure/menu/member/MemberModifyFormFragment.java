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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MemberModifyFormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MemberModifyFormFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View view;
    EditText et_pwd, et_pwd_check, et_name, et_nickname, et_tel;
    Button btn_modify, btn_cancel;

    MainActivity main;

    LoginUserInfoFragment login_user_info_Frag;

    public MemberModifyFormFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MemberModifyFormFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MemberModifyFormFragment newInstance(String param1, String param2) {
        MemberModifyFormFragment fragment = new MemberModifyFormFragment();
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
        view = inflater.inflate(R.layout.fragment_member_modify_form, container, false);

        login_user_info_Frag =new LoginUserInfoFragment();

        main = (MainActivity)getActivity();

        et_pwd = view.findViewById(R.id.et_pwd);
        et_pwd_check  = view.findViewById(R.id.et_pwd_check);
        et_name = view.findViewById(R.id.et_name);
        et_nickname = view.findViewById(R.id.et_nickname);
        et_tel = view.findViewById(R.id.et_tel);

        btn_modify = view.findViewById(R.id.btn_modify);
        btn_cancel = view.findViewById(R.id.btn_modify);

        btn_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.replaceFragment(login_user_info_Frag);
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.replaceFragment(login_user_info_Frag);
            }
        });


        return view;
    }
}
