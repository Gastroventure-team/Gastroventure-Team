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

    Button btn_login, btn_register;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_logout_user_info, container, false);

        btn_login = (Button)view.findViewById(R.id.btn_login);
        btn_register =(Button)view.findViewById(R.id.btn_register);



        return view;
    }
}
