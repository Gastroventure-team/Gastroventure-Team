package com.teamproject.gastroventure.vo;

/**
 * Created by 82108 on 2020-03-12.
 */
public class BoardVo {
    public String board_num;
    public String board_title;
    public String board_date;
    public String board_content;

    public BoardVo(){}

    public String getBoard_title() {
        return board_title;
    }

    public void setBoard_title(String board_title) {
        this.board_title = board_title;
    }

    public String getBoard_date() {
        return board_date;
    }

    public void setBoard_date(String board_date) {
        this.board_date = board_date;
    }

    public String getBoard_content() {
        return board_content;
    }

    public void setBoard_content(String board_content) {
        this.board_content = board_content;
    }

    public String getBoard_num() {
        return board_num;
    }

    public void setBoard_num(String board_num) {
        this.board_num = board_num;
    }
}
