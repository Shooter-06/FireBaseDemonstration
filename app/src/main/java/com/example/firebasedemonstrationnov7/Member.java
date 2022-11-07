package com.example.firebasedemonstrationnov7;

public class Member {
    String name;
    Integer age;
    Long phone;
     float height;

    public Member() {
    }

    public Member(String name, Integer age, Long phone, float height) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phone=" + phone +
                ", height=" + height +
                '}';
    }
}
