package com.example.quizc;

public class Singleton {
    private static Singleton instance;
    public String s;
    public  void setData(String s){
        this.s=s;
    }
    public  String getData(){
        return s;
    }

    private Singleton(){

    }


    public static Singleton getInstance(){
        if (instance == null){
            instance = new Singleton();
        }
        return instance;
    }

}
