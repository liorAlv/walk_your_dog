package com.example.test_dog.models;

public class DogWalkerUser {
    String name;
    String email;
    String phone;
    String age;
    String city;
    String price;
    String TellMeAboutYourSelf;
    String LinkForInstagramOrFacebook;

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setTellMeAboutYourSelf(String tellMeAboutYourSelf) {
        TellMeAboutYourSelf = tellMeAboutYourSelf;
    }

    public void setLinkForInstagramOrFacebook(String linkForInstagramOrFacebook) {
        LinkForInstagramOrFacebook = linkForInstagramOrFacebook;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public String getPrice() {
        return price;
    }

    public String getTellMeAboutYourSelf() {
        return TellMeAboutYourSelf;
    }

    public String getLinkForInstagramOrFacebook() {
        return LinkForInstagramOrFacebook;
    }


    public DogWalkerUser() {

    }

    public DogWalkerUser(String name, String email, String phone, String age, String city, String price, String tellMeAboutYourSelf, String linkForInstagramOrFacebook) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.city = city;
        this.price = price;
        this.TellMeAboutYourSelf = tellMeAboutYourSelf;
        this.LinkForInstagramOrFacebook = linkForInstagramOrFacebook;
    }



}
