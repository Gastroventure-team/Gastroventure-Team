package com.teamproject.gastroventure.vo;

/**
 * Created by hanman-yong on 2020/03/16.
 */
public class UserInfo {
    public String id;
    public String pw;
    public String name;
    public String nickname;
    public String phone;

    public UserInfo() {
    }

    // 회원가입시
    public UserInfo(String id, String pw, String name, String nickname, String phone) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.nickname = nickname;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}