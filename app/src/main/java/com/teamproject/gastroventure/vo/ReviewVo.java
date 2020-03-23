package com.teamproject.gastroventure.vo;

/**
 * Created by hanman-yong on 2020/03/17.
 */
public class ReviewVo {
    public int idx; // 필요하려나.
    public String store_name;
    public String menu;
    public int rating_num;
    public String menu_image;

    public ReviewVo() {
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public int getRating_num() {
        return rating_num;
    }

    public void setRating_num(int rating_num) {
        this.rating_num = rating_num;
    }

    public String getMenu_image() {
        return menu_image;
    }

    public void setMenu_image(String menu_image) {
        this.menu_image = menu_image;
    }
}
