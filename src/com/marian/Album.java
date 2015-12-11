package com.marian;

/**
 * Created by marian on 12/2/2015.
 */
public class Album {
    String Title;
    String Artist;
    String Album;
    String Category;
    double sellingPrice;

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public Album(String Title, String Artist, String Category, Double sellingPrice ){
        this.Title = Title;
        this.Artist = Artist;
        this.Category = Category;
        this.sellingPrice = sellingPrice;

    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getArtist() {
        return Artist;
    }

    public void setArtist(String artist) {
        Artist = artist;
    }

    public String getAlbum() {
        return Album;
    }

    public void setAlbum(String album) {
        Album = album;
    }
}
