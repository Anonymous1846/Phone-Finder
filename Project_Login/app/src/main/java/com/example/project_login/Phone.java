package com.example.project_login;

public class Phone {
    String make,phoneName,imageUrl,Ram,Rom,buy_link,rating;
    double price;

    public Phone(String make,String phoneName,String imageUrl,double price,String buy_link,String rating,String Ram,String Rom){
            this.imageUrl=imageUrl;
            this.make=make;
            this.phoneName=phoneName;
            this.Rom=Rom;
            this.Ram=Ram;
            this.rating=rating;
            this.buy_link=buy_link;
            this.price=price;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public String getMake() {
        return make;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public String getRam() {
        return Ram;
    }
    public String getRom() {
        return Rom;
    }

    public double getPrice() {
        return price;
    }

    public String getRating() {
        return rating;
    }

    public String getBuy_link() {
        return buy_link;
    }
}
