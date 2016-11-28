package com.mysoft;

/**
 * Created by www on 28.11.2016.
 */
public class Post {
    int id;
    String txt;

    public Post(int id, String txt){
        this.id=id;
        this.txt=txt;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getTxt(){
        return this.txt;
    }

    public void setTxt(String txt){
        this.txt=txt;
    }
}
