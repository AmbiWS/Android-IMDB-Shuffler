package com.ambiwsstudio.movie_shuffler.model;

public class User {

    private int age;

    public User(int age) {

        this.age = age;

    }

    public int getAge() {
        return age;
    }

    public boolean isAdult() {

        return age >= 18;

    }

}
