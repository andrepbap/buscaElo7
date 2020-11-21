package br.com.andrepbap.testebuscaelo7.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductCardModel implements Serializable {

    private ProductPriceModel price;
    private String picture;
    private String title;
    private String id;
    @SerializedName("_link")
    private String link;

    public ProductPriceModel getPrice() {
        return price;
    }

    public String getPicture() {
        return picture;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public String getLink() {
        return link;
    }
}
