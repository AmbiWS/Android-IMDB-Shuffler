package com.ambiwsstudio.aliexpress_shuffler.model;

public class User {

    private String name;
    private int age;

    public User(int age) {

        this.name = "User";
        this.age = age;

    }

    public int getAge() {
        return age;
    }

    public boolean isAdult() {

        return age >= 18;

    }

}
