package com.teamproject.gastroventure;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.teamproject.gastroventure.menu.BoardFragment;
import com.teamproject.gastroventure.menu.member.LogoutUserInfoFragment;
import com.teamproject.gastroventure.menu.review.ReviewFragment;
import com.teamproject.gastroventure.menu.SearchFragment;
import com.teamproject.gastroventure.menu.member.LoginUserInfoFragment;

public class MainActivity extends AppCompatActivity {

    // 바텀 네비게이션 뷰
    private BottomNavigationView bottomNavigationView;

    // 프래그먼트 관리
    private FragmentManager fm;
    private FragmentTransaction ft;

    // 각 프래그먼트 화면 선
    private ReviewFragment reviewFragment;
    private SearchFragment searchFragment;
    private BoardFragment boardFragment;
    private LogoutUserInfoFragment logoutUserInfoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //하단 네비게이션 바
        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_search:
                        setFrag(0);
                        break;
                    case R.id.action_review:
                        setFrag(1);
                        break;
                    case R.id.action_board:
                        setFrag(2);
                        break;
                    case R.id.action_user_info:
                        setFrag(3);
                        break;
                }
                return true;
            }
        });

        reviewFragment = new ReviewFragment();
        searchFragment = new SearchFragment();
        boardFragment = new BoardFragment();
        logoutUserInfoFragment = new LogoutUserInfoFragment();
        setFrag(0); // 첫 프래그먼트 화면을 무엇으로 지정해줄 것인지 선택.

    }

    //프래그먼트간 이동을 위해 사용하는 메소드
    public void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main_frame, fragment).commit();
    }

    // 프래그먼트 교체가 일어나는 실행문이다.
    private void setFrag(int n) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n) {
            case 0:
                ft.replace(R.id.main_frame, searchFragment);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.main_frame, reviewFragment);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.main_frame, boardFragment);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.main_frame, logoutUserInfoFragment);
                ft.commit();
                break;
        }
    }
}
