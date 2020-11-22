package com.example.project_login;

public class Phone {
    String phoneName,imageUrl,Specs,price;

    public Phone(String phoneName,String imageUrl,String Specs,String price){
            this.imageUrl=imageUrl;
            this.phoneName=phoneName;
            this.Specs=Specs;
            this.price=price;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public String getSpecs() {
        return Specs;
    }

    public String getPrice() {
        return price;
    }
}
