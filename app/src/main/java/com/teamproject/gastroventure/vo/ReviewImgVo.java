package com.teamproject.gastroventure.vo;

/**
 * Created by hanman-yong on 2020/03/17.
 */
public class ReviewImgVo {
    public String review_key;
    public String review_insert_key;
    public String menu_image;

    public ReviewImgVo() {
    }

    public ReviewImgVo(String review_key, String menu_image) {
        this.review_key = review_key;
        this.menu_image = menu_image;
    }

    public String getReview_key() {
        return review_key;
    }

    public void setReview_key(String review_key) {
        this.review_key = review_key;
    }

    public String getReview_insert_key() {
        return review_insert_key;
    }

    public void setReview_insert_key(String review_insert_key) {
        this.review_insert_key = review_insert_key;
    }

    public String getMenu_image() {
        return menu_image;
    }

    public void setMenu_image(String menu_image) {
        this.menu_image = menu_image;
    }
}