package com.github.why168.databinding.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.github.why168.databinding.BR;

public class Person extends BaseObservable {
    private String name;
    private int age;
    private String gender;
    private String hobbies;
    private String locality;
    private String profession;

    public Person(String name, int age, String gender, String hobbies, String locality, String profession) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.hobbies = hobbies;
        this.locality = locality;
        this.profession = profession;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        notifyPropertyChanged(BR.age);
    }

    @Bindable
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
        notifyPropertyChanged(BR.gender);
    }

    @Bindable
    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
        notifyPropertyChanged(BR.hobbies);
    }

    @Bindable
    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
        notifyPropertyChanged(BR.locality);
    }

    @Bindable
    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
        notifyPropertyChanged(BR.profession);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", hobbies='" + hobbies + '\'' +
                ", locality='" + locality + '\'' +
                ", profession='" + profession + '\'' +
                '}';
    }
}