package com.teamproject.gastroventure.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.teamproject.gastroventure.R;

public class BoardFragment extends Fragment {

    private View view;
    private RecyclerView board_list;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_board, container, false);

        //게시판(리사이클러뷰)
        board_list = view.findViewById(R.id.board_list);



        return view;
    }


}
