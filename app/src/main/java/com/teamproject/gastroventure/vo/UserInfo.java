package com.teamproject.gastroventure.vo;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hanman-yong on 2020/03/16.
 */
public class UserInfo {
    public int idx;
    public String id;
    public String pw;
    public String name;
    public String nickname;
    public String tel;

    public UserInfo() {
    }

    // 회원가입시
    public UserInfo(int idx,String id, String pw, String name, String nickname, String tel) {
        this.idx = idx;
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.nickname = nickname;
        this.tel = tel;
    }

    // 아이디 찾기
    public UserInfo(String name, String nickname) {
        this.name = name;
        this.nickname = nickname;
    }

    // 비밀번호 찾기
    public UserInfo(String id, String name, String nickname) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("pw", pw);
        result.put("name", name);
        result.put("nickname", nickname);
        result.put("phone", tel);

        return result;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
